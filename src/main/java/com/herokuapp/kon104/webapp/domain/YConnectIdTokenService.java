package com.herokuapp.kon104.webapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.herokuapp.kon104.webapp.util.HttpRequestUtility;

/**
 * YConnect IdToken Service
 */
@Service
public class YConnectIdTokenService
{
	@Autowired
	private HttpRequestUtility hrUtil;

	public static final String urlPublicKeys = "https://auth.login.yahoo.co.jp/yconnect/v2/public-keys";

	public Map<String, Boolean> verify(String idtoken, String issuer, String clientId, String nonce, String access_token, int max_age)
	{
        Map<String, Boolean> results = new HashMap<>();
		int unixTime = (int) (System.currentTimeMillis() / 1000L);
		boolean result;

		// Step 2
		Map<String, String> idtokens = this.divideIdToken(idtoken);
		// Step 3
		String header = this.base64UrlDecodeString(idtokens.get("header"));
		String payload = this.base64UrlDecodeString(idtokens.get("payload"));
		byte[] signature = this.base64UrlDecodeByte(idtokens.get("signature"));
		JsonNode jsonHeader = this.convStr2Json(header);
		JsonNode jsonPayload = this.convStr2Json(payload);
		// Step 4
		RSAPublicKey publicKey = this.collectPublicKey(jsonHeader.get("kid").textValue());
		// Step 5
		String algorithm = this.selectAlgorithm(jsonHeader.get("alg").textValue());
		// Step 6
		String signTarget = idtokens.get("header") + "." + idtokens.get("payload");
		result = this.verifySignature(signTarget, signature, publicKey, algorithm);
		results.put("verify6", result);
		// Step 7
		result = this.compareIssuer(issuer, jsonPayload.get("iss").textValue());
		results.put("verify7", result);
		// Step 8
		result = this.checkIncludingClientId(clientId, jsonPayload.get("aud"));
		results.put("verify8", result);
		// Step 9
		result = this.compareNonce(nonce, jsonPayload.get("nonce").textValue());
		results.put("verify9", result);
		// Step 10 (Step 11)
		result = this.compareHalfAccessTokenHash(access_token, jsonHeader.get("alg").textValue(), jsonPayload.get("at_hash").textValue());
		results.put("verify10", result);
		// Step 12
		result = this.checkIdTokenExpire(unixTime, jsonPayload.get("exp").intValue());
		results.put("verify12", result);
		// Step 13
		result = this.checkIdTokenWithinPolicySeconds(unixTime, jsonPayload.get("iat").intValue());
		results.put("verify13", result);
		// Step 14
		result = this.checkYidLoginWithinMaxAge(unixTime, jsonPayload.get("auth_time").intValue(), max_age);
		results.put("verify14", result);


System.out.println(result);
System.out.println(algorithm);
System.out.println(idtokens.get("header"));
System.out.println(idtokens.get("moge"));
System.out.println(header);
System.out.println(payload);

		return results;
	}

	// {{{ private Map<String, String> divideIdToken(String idtoken)
	private Map<String, String> divideIdToken(String idtoken)
	{
		String[] idtokens = idtoken.split(Pattern.quote("."));
		Map<String, String> tokens = new HashMap<>();
		if (idtokens.length > 0) {
			tokens.put("header", idtokens[0]);
		}
		if (idtokens.length > 1) {
			tokens.put("payload", idtokens[1]);
		}
		if (idtokens.length > 2) {
			tokens.put("signature", idtokens[2]);
		}
//		for(Iterator<Map.Entry<String, String>> iterator = tokens.entrySet().iterator() ; iterator.hasNext() ;){
//			Map.Entry<String, String> entry = iterator.next();
//			System.out.println(entry.getKey() + " : " + entry.getValue());
//		}
		return tokens;
	}
	// }}}

	// {{{ private byte[] base64UrlDecodeByte(String data)
	private byte[] base64UrlDecodeByte(String data)
	{
		data = data
			.replaceAll("-", "+")
			.replaceAll("_", "/");

		int len = data.length();
		int mod = len % 4;
		if (mod > 0) {
			data = data + String.join("", Collections.nCopies((4 - mod), "="));
		}

		byte[] decoded = Base64.getDecoder().decode(data);

		return decoded;
	}
	// }}}

	// {{{ private String base64UrlDecodeString(String data)
	private String base64UrlDecodeString(String data)
	{
		byte[] decoded = this.base64UrlDecodeByte(data);
		String coverted = new String(decoded);
		return coverted;
	}
	// }}}

	// {{{ private JsonNode convStr2Json(String str)
	private JsonNode convStr2Json(String str)
	{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = null;
		try {
			json = mapper.readTree(str);
		} catch (Exception e) {
		}
		return json;
	}
	// }}}

	// {{{ private RSAPublicKey collectPublicKey(String kid)
	private RSAPublicKey collectPublicKey(String kid)
	{
		RestTemplate restTemplate = new RestTemplate();
		String resp = restTemplate.getForObject(this.urlPublicKeys, String.class);
		JsonNode json = this.convStr2Json(resp);

		String publicKeyPEM = json.get(kid).textValue();
		publicKeyPEM = publicKeyPEM
			.replace("-----BEGIN PUBLIC KEY-----", "")
			.replaceAll(System.lineSeparator(), "")
			.replace("-----END PUBLIC KEY-----", "");
		byte[] decodedPubKey = base64UrlDecodeByte(publicKeyPEM);

		RSAPublicKey publicKey = null;
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedPubKey);
			publicKey = (RSAPublicKey)keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
		}
 
		return publicKey;
	}
	// }}}

	// {{{ private String selectAlgorithm(String alg)
	private String selectAlgorithm(String alg)
	{
		Map<String, String> algs = new HashMap<>();
		algs.put("RS256", "SHA256withRSA");
		String result = algs.get(alg);
		return result;
	}
	// }}}

	// {{{ private boolean verifySignature(String data, byte[] signature, RSAPublicKey publicKey, String algorithm)
	private boolean verifySignature(String data, byte[] signature, RSAPublicKey publicKey, String algorithm)
	{
		boolean result = false;
		try {
			Signature verifier = Signature.getInstance(algorithm);
			verifier.initVerify(publicKey);
			verifier.update(data.getBytes("UTF-8"));
			result = verifier.verify(signature);
		} catch (Exception e) {
		}
		return result;
	}
	// }}}

	// {{{ private boolean compareIssuer(String issuer, String iss)
	private boolean compareIssuer(String issuer, String iss)
	{
		boolean result = issuer.equals(iss);
		return result;
	}
	// }}}

	// {{{ private boolean checkIncludingClientId(String clientId, JsonNode aud)
	private boolean checkIncludingClientId(String clientId, JsonNode aud)
	{
		boolean result = false;

		List<String> lists =
			StreamSupport.stream(aud.spliterator(), false)
				.map(
					e -> {
					return e.asText();
				})
				.collect(Collectors.toList());

		for(String line : lists){
			System.out.println(">>>[" + line + "]");
			result = clientId.equals(line);
			if (result == true) {
				break;
			}
		}

		return result;
	}
	// }}}

	// {{{ private boolean compareNonce(String nonce, String payloadNonce)
	private boolean compareNonce(String nonce, String payloadNonce)
	{
		boolean result = nonce.equals(payloadNonce);
		return result;
	}
	// }}}

	// {{{ private boolean compareHalfAccessTokenHash(String access_token, String alg, String at_hash)
	private boolean compareHalfAccessTokenHash(String access_token, String alg, String at_hash)
	{
		boolean result = false;

		Map<String, String> algs = new HashMap<>();
		algs.put("RS256", "SHA-256");
		String algorithm = algs.get(alg);

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
		}
		byte[] byteHash = md.digest(access_token.getBytes());

		int lenHalf = byteHash.length / 2;
		byte[] byteHalf = new byte[lenHalf];
		System.arraycopy(byteHash, 0, byteHalf, 0, lenHalf);
		byte[] encoded = Base64.getEncoder().encode(byteHalf);
		String my_hash = new String(encoded, StandardCharsets.UTF_8); 
		my_hash = my_hash
			.replaceAll(Pattern.quote("+"), "-")
			.replaceAll("/", "_")
			.replaceAll("=", "");

		result = at_hash.equals(my_hash);

		return result;
	}
	// }}}

	// {{{ private boolean checkIdTokenExpire(int unixTime, int exp)
	private boolean checkIdTokenExpire(int unixTime, int exp)
	{
		boolean result = false;
		result = (exp > unixTime);

		return result;
	}
	// }}}

	// {{{ private boolean checkIdTokenWithinPolicySeconds(int unixTime, int iat)
	private boolean checkIdTokenWithinPolicySeconds(int unixTime, int iat)
	{
		boolean result = false;
		int policySec = 600;
		result = (iat > (unixTime - policySec));

		return result;
	}
	// }}}

	// {{{ private boolean checkYidLoginWithinMaxAge(int unixTime, int auth_time, int max_age)
	private boolean checkYidLoginWithinMaxAge(int unixTime, int auth_time, int max_age)
	{
		boolean result = false;
		result = ((auth_time + max_age) > unixTime);
		return result;
	}
	// }}}

}

package com.herokuapp.kon104.webapp.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StudyEnglishService
{

	@Value("${apps.private.study.english.url}")
	private String sheetUrl = null;

    // {{{ public List<SentenceJpn2Eng> main()
    public List<SentenceJpn2Eng> main()
	{
		String responseBody = this.requestHttpGET(this.sheetUrl);
		List<SentenceJpn2Eng> beanList = this.replaceGssJson2Bean(responseBody);

		return beanList;
	}
	// }}}

    // {{{ private String requestHttpGET(String url)
    private String requestHttpGET(String url)
	{
		String responseBody = null;

		try{
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			responseBody = response.toString();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return responseBody;
	}
	// }}}

    // {{{ private List<SentenceJpn2Eng> replaceGssJson2Bean(String json)
    private List<SentenceJpn2Eng> replaceGssJson2Bean(String json)
	{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try{
			node = mapper.readTree(json);
		} catch(JsonProcessingException e) {
			e.printStackTrace();
		}

		List<SentenceJpn2Eng> beanList = new ArrayList<SentenceJpn2Eng>();
		for (JsonNode n : node.get("feed").get("entry")) {
			SentenceJpn2Eng sentence = new SentenceJpn2Eng();
			String colSrc = n.get("gsx$source").get("$t").asText();
			if ((colSrc != null) && (colSrc != "")) {
				sentence.setSource(colSrc);
			}
			String colJpn = n.get("gsx$japanese").get("$t").asText();
			if ((colJpn != null) && (colJpn != "")) {
				sentence.setJapanese(colJpn);
			}
			String colEng = n.get("gsx$english").get("$t").asText();
			if ((colEng != null) && (colEng != "")) {
				sentence.setEnglish(colEng);
			}
			beanList.add(sentence);
		}

		return beanList;
	}
	// }}}

}

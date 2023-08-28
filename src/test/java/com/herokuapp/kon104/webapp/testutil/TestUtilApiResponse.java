package com.herokuapp.kon104.webapp.testutil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtilApiResponse
{
	private static String FILE_PATH = "src/test/resources/file/apiresponse";

	// {{{ public static String getString(String filename)
	public static String getString(String filename)
	{
		Path file = Paths.get(FILE_PATH, filename);
		String text = null;
		try {
			text = Files.readString(file);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return text;
	}
	// }}}

	// {{{ public static JsonNode getJsonNode(String filename)
	public static JsonNode getJsonNode(String filename)
	{
		String text = TestUtilApiResponse.getString(filename);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = null;
		try {
			json = mapper.readTree(text);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	// }}}

}

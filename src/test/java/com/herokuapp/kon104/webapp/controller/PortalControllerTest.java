package com.herokuapp.kon104.webapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PortalControllerTest
{
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void indexTest() throws Exception
	{
		this.mockMvc.perform(get("/"))
			.andExpect(status().isOk());
	}

/*
	@Test
	public void sitemapTest() throws Exception
	{
		this.mockMvc.perform(get("/sitemap.xml"))
			.andExpect(status().isOk());
	}

	@Test
	public void robotsTest() throws Exception
	{
		this.mockMvc.perform(get("/robots.txt"))
			.andExpect(status().isOk());
	}
*/
}

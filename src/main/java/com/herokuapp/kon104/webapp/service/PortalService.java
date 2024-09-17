package com.herokuapp.kon104.webapp.service;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Portal Service Interface
 */
public interface PortalService
{
	public String getDomain(HttpServletRequest request);
	public String getCopyrightYear();
}

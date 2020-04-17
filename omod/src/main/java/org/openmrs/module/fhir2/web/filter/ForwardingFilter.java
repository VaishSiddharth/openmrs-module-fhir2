/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.fhir2.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForwardingFilter implements Filter {
	
	private static final Logger log = LoggerFactory.getLogger(ForwardingFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) {
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String requestURI = request.getRequestURI();
		
		String contextPath = ((HttpServletRequest) req).getContextPath();
		String prefix = contextPath + "/ws/fhir2/";
		if (requestURI.startsWith(prefix)) {
			String[] requestPath = requestURI.split(prefix, 1);
			String maybeVersion = requestPath[1].split("/", 1)[0];
			
			String replacement;
			switch (maybeVersion) {
				case "R3":
					prefix += "/" + maybeVersion + "/";
					replacement = "/ms/fhir2Dstu3Servlet";
					break;
				case "R4":
					prefix += "/" + maybeVersion + "/";
					replacement = "/ms/fhir2Servlet";
					break;
				default:
					replacement = "/ms/fhir2Servlet";
					break;
			}
			
			String newURI = requestURI.replace(prefix, replacement);
			req.getRequestDispatcher(newURI).forward(req, res);
		} else {
			chain.doFilter(req, res);
		}
	}
	
	@Override
	public void destroy() {
	}
}

package org.mycontrib.generic.security.rest.jaxrs;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

public class MySecurityContext implements SecurityContext {

	private static final String AUTHENTICATION_SCHEME = null;

	@Override
	public Principal getUserPrincipal() {
		// return (Principal)"username";
		return null;
	}

	@Override
	public boolean isUserInRole(String role) {
		return role.equals("MEMBER2");
	}

	@Override
	public boolean isSecure() {
		return true;
	}

	@Override
	public String getAuthenticationScheme() {
		return AUTHENTICATION_SCHEME;
	}

}

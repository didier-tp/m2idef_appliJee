package org.mycontrib.generic.security.rest.jaxrs;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.mycontrib.generic.security.jwt.JwtConstant;
import org.mycontrib.generic.security.jwt.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@JwtTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JwtTokenNeededFilter implements ContainerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenNeededFilter.class);

	@Context
	private ResourceInfo resourceInfo;

	/*
	 * @Inject private KeyGenerator keyGenerator; //or
	 * JwtConstant.DEFAULT_SECRET_KEY
	 */

	private String extractJwtFromAuthorizationHeader(ContainerRequestContext requestContext) {
		String jwt = null;
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		// logger.info("authorizationHeader:" + authorizationHeader);
		if (authorizationHeader != null) {
			// Extract the token from the HTTP Authorization header String:
			if (authorizationHeader.startsWith("Bearer")) {
				jwt = authorizationHeader.substring("Bearer".length()).trim();
			}
		}
		return jwt;
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.info("JwtTokenNeededFilter.filter() was called");

		Method method = resourceInfo.getResourceMethod();

		if (method != null) {
			JwtTokenNeeded jwtTokenNeededAnnotation = method.getAnnotation(JwtTokenNeeded.class);
			logger.info("jwtTokenNeededAnnotation found :" + jwtTokenNeededAnnotation);
		}

		String jwt = null;
		// extract Jwt bear token From AuthorizationHeader of Http request:
		jwt = extractJwtFromAuthorizationHeader(requestContext);// may be null

		if (JwtUtil.validateToken(jwt, JwtConstant.DEFAULT_SECRET_KEY)) {
			logger.info("#### valid token : " + jwt + " with claims = "
					+ JwtUtil.extractClaimsFromJWT(jwt, JwtConstant.DEFAULT_SECRET_KEY));
			requestContext.setSecurityContext(new MySecurityContext());
		} else {
			logger.error("#### invalid token : " + jwt);
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}
}

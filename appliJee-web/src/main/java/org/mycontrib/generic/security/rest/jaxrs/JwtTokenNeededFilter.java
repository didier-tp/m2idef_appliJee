package org.mycontrib.generic.security.rest.jaxrs;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@JwtTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JwtTokenNeededFilter implements ContainerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenNeededFilter.class);

	/*
	 * @Inject private KeyGenerator keyGenerator;
	 */

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Get the HTTP Authorization header from the request String
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		String jwt = authorizationHeader.substring("Bearer".length()).trim();
		/*
		 * 
		 * // Extract the token from the HTTP Authorization header String token =
		 * 
		 * 
		 * try {
		 * 
		 * // Validate the token Key key = keyGenerator.generateKey();
		 * Jwts.parser().setSigningKey(key).parseClaimsJws(token);
		 * logger.info("#### valid token : " + token);
		 * 
		 * } catch (Exception e) { logger.severe("#### invalid token : " + token);
		 * requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build(
		 * )); }
		 */

		// Test temporaire:
		System.out.println("JWTTokenNeededFilter.filter() was called");

	}
}

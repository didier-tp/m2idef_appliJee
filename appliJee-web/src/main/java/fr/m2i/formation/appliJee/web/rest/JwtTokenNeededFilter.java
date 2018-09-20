package fr.m2i.formation.appliJee.web.rest;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.mycontrib.generic.security.jwt.JwtUtil;
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
		/*
		 * // Get the HTTP Authorization header from the request String
		 * authorizationHeader =
		 * requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		 * 
		 * // Extract the token from the HTTP Authorization header String token =
		 * authorizationHeader.substring("Bearer".length()).trim();
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
		long jwtExpirationInMs = 60 * 15 * 1000; // = 900000ms pour 15minutes
		String username = "user1";
		String secretKey = "mySecretKey";
		Set<String> roles = new HashSet<String>();
		roles.add("USER");
		roles.add("MEMBER");
		String myJwt = JwtUtil.buildToken(username, jwtExpirationInMs, secretKey, roles);
		logger.info("myJwt=" + myJwt);
		logger.info("claims in jwt=" + JwtUtil.extractClaimsFromJWT(myJwt, secretKey).toString());

	}
}

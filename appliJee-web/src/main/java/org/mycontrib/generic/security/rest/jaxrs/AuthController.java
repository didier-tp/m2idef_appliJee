package org.mycontrib.generic.security.rest.jaxrs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.mycontrib.generic.security.jwt.JwtConstant;
import org.mycontrib.generic.security.jwt.JwtUtil;
import org.mycontrib.generic.security.rest.payload.AuthRequest;
import org.mycontrib.generic.security.rest.payload.AuthResponse;
import org.mycontrib.generic.security.rest.payload.NewUser;
import org.mycontrib.generic.security.rest.payload.RegisterUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// sign up = subscribe/register = s'inscrire

// sign in = login = se connecter

@Path("auth")
@Produces("application/json") // en retour Java --> JSON
@Consumes("application/json") // en entree (POST) JSON --> Java
public class AuthController /* extends AbstractRestAuthWS */ {

	private static Logger logger = LoggerFactory.getLogger(AuthController.class);

	@POST
	@Path("/login")
	// URL= http://localhost:8080/appliJee-web/rest/auth/login
	public Response authenticateUser(AuthRequest loginRequest) {

		logger.info("/login , loginRequest:" + loginRequest);

		AuthResponse authResponse = new AuthResponse();
		try {

			long jwtExpirationInMs = 60 * 15 * 1000; // = 900000ms pour 15minutes
			String username = loginRequest.getUsername();
			String secretKey = JwtConstant.DEFAULT_SECRET_KEY;
			Set<String> roles = new HashSet<String>();
			roles.add("USER");
			roles.add("MEMBER");
			String myJwt = JwtUtil.buildToken(username, jwtExpirationInMs, secretKey, roles);
			logger.info("myJwt=" + myJwt);
			logger.info("claims in jwt=" + JwtUtil.extractClaimsFromJWT(myJwt, secretKey).toString());

			authResponse.setAuthToken(myJwt);
			authResponse.setAuthOk(true);
			authResponse.setMessage("login successful");

			logger.info("/login authResponse:" + authResponse.toString());
			return Response.ok(authResponse).build();
		} catch (Exception e) {
			logger.info("echec authentification:" + e.getMessage()); // for log
			authResponse.setAuthOk(false);
			authResponse.setMessage("echec authentification");
			return Response.status(Response.Status.UNAUTHORIZED).entity(authResponse).build();

		}

	}

	@POST
	@Path("/registerUser")
	// URL= http://localhost:8080/appliJee-web/rest/auth/login
	public Response registerUser(NewUser newUser) {

		logger.info("registerUser() called with newUser=" + newUser.toString());

		/*
		 * // Creating user's account LoginAccount userAccount = new
		 * LoginAccount(newUser.getUsername(), newUser.getPassword());
		 * 
		 * if (newUser.getEmail() != null || newUser.getPhoneNumber() != null ||
		 * newUser.getFirstName() != null || newUser.getLastName() != null) {
		 * LoginAccountDetails userAccountDetails = new
		 * LoginAccountDetails(newUser.getEmail(), newUser.getPhoneNumber(),
		 * newUser.getFirstName(), newUser.getLastName());
		 * userAccount.setDetail(userAccountDetails.toJsonString()); }
		 * 
		 * userAccount = defaultUserAccountService.createAccountInGroup(userAccount,
		 * LoginAccountService.DEFAULT_FULL_CONTEXT_NAME);
		 * 
		 */

		return Response.ok(new RegisterUserResponse(true, "User registered successfully", newUser,
				null/* userAccount.getLoginId() */)).build();
	}
}

package org.mycontrib.generic.security.rest.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * NewUser as "RegisterUserRequest" and subpart of RegisterUserResponse
 */

@Getter @Setter
@ToString
@NoArgsConstructor
public class NewUser {
	//@NotBlank
    //@Size(min = 3, max = 32)
	private String username;// (unique)
	//@NotBlank
    //@Size(min = 4, max = 64)
	private String password;
	
	private String email;
	
	private String userType; // "member"/"MEMBER" or "user"/"USER" or "admin" or ...
	                         // if null --> "member" by default
	
	private String firstName; //may be null
	private String lastName; //may be null
	private String phoneNumber; //may be null
}

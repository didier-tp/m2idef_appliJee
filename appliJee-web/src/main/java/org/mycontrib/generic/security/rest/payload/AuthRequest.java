package org.mycontrib.generic.security.rest.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class AuthRequest {
	private String username;//as userNameOrId (or email) ; //as id (unique)
	private String password;
	
	//private String claimRole; //may be null/undefined
	//private String organization; //may be null/undefined
}

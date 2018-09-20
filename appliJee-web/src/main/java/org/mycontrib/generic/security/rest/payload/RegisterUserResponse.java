package org.mycontrib.generic.security.rest.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class RegisterUserResponse {

	private Boolean ok;
	private String message;
	
	private NewUser newUser;

	private Long userId;//not null in successful response
	
	

	public RegisterUserResponse(Boolean ok, String message, NewUser newUser , Long userId) {
		super();
		this.ok = ok;
		this.message = message;
		this.newUser = newUser;
		this.userId = userId;
	}
	
	public RegisterUserResponse(Boolean ok, String message, NewUser newUser) {
		this(ok,message,newUser,null);
	}

}

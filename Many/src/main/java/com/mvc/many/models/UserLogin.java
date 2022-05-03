package com.mvc.many.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserLogin {
	@NotEmpty(message = "Please enter a email address.")
	@Email
	private String email;
	
	@NotEmpty(message = "Please enter password.")
	private String password;
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserLogin(){}

	public UserLogin(@NotEmpty(message = "Please enter a email address.") @Email String email,
			@NotEmpty(message = "Please enter password.") String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
}

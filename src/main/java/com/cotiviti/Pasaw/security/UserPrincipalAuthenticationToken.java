package com.cotiviti.Pasaw.security;



import org.springframework.security.authentication.AbstractAuthenticationToken;


public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {

    private final UserPrincipal userPrincipal;

	public UserPrincipalAuthenticationToken(UserPrincipal userPrincipal) {
        super(userPrincipal.getAuthorities());
		this.userPrincipal = userPrincipal;
	}

	@Override
	public Object getCredentials() {
		throw new UnsupportedOperationException("Unimplemented method 'getCredentials'");
	}

	@Override
	public UserPrincipal getPrincipal() {
		return userPrincipal;
	}
    
}

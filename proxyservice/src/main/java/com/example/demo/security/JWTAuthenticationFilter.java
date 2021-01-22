package com.example.demo.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.beans.UserBean;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
private AuthenticationManager authenticationManager;

public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
	super();
	this.authenticationManager = authenticationManager;
}



//public Authentication attepmtAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException {
//	System.out.println("user:");
//
//	AppUser user=null;
//	try {
//	user=new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
//	System.out.println("user:"+user.toString());
//
//	}catch(Exception e) {
//		System.out.println("user:"+user.toString());
//
//		throw new RuntimeException(e);
//	}
//	
//	return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//			user.getUsername(), user.getPassword()));
//}
//public void successfullAthentication(HttpServletRequest request,HttpServletResponse response,
//		FilterChain chain,Authentication authResult)throws IOException,ServletException {
//	User springUser=(User)authResult.getPrincipal();
//	String jwtToken=Jwts.builder()
//			.setSubject(springUser.getUsername())
//			.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
//			.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
//			.claim("roles", springUser.getAuthorities())
//			.compact();
//	response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwtToken);
//			
//			
//	
//}

@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
	UserBean user=null;
	try {
	user=new ObjectMapper().readValue(request.getInputStream(), UserBean.class);
	System.out.println("user:"+user.toString());

	}catch(Exception e) {
		System.out.println("user:"+user.toString());

		throw new RuntimeException(e);
	}
	
	return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
			user.getEmail(), user.getPassword()));
}
		
@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
	User springUser=(User)authResult.getPrincipal();
	String jwtToken=Jwts.builder()
			.setSubject(springUser.getUsername())
			.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
			.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
			.claim("roles", springUser.getAuthorities())
			.compact();
	response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwtToken);
	}
}

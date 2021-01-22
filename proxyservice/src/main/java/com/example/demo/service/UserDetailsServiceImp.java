package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.beans.UserBean;
import com.example.demo.proxies.MemberProxy;



@Service
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private MemberProxy accountService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserBean user = accountService.findUserByEmail(email);
		if (user == null)
			throw new UsernameNotFoundException(email);
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		user.getRoles().forEach(r -> {
			authorities.add(new SimpleGrantedAuthority(r.getRole()));
		});
		return new User(user.getEmail(),user.getPassword(),authorities);
	}

}

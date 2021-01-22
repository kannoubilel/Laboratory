package com.example.demo.beans;

import java.util.Collection;
import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBean {

	private Long id;
	
	@NonNull
	private String email;
	@NonNull
	private String password;
	
	@OneToMany(fetch = FetchType.EAGER)
	Collection<Role> roles;
}

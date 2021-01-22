package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.NoOp;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserDetailsService userDetailsService;

	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		
		  final BCryptPasswordEncoder bcpe=getBCPE();
		  System.out.println("password"+bcpe.encode("123"));
		 

//		auth.inMemoryAuthentication().withUser("admin").password(bcpe.encode("123")).roles("admin","user");
//		auth.inMemoryAuthentication().withUser("user").password(bcpe.encode("123")).roles("user");
//		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());
		/*
		 * auth.jdbcAuthentication() .dataSource(dataSource)
		 * .usersByUsernameQuery("select username as principal,password as credentials,enabled from utilisateur where username=?"
		 * )
		 * .authoritiesByUsernameQuery("select utilisateur_username as principal ,role_role as role from utilisateur_role where  utilisateur_username=?"
		 * ) //.
		 * usersByUsernameQuery("select username as principal,role as role from user_role where  username=?"
		 * )
		 * 
		 * .rolePrefix("ROLE_").passwordEncoder(getBCPE());
		 */
    auth.userDetailsService(userDetailsService).passwordEncoder(getBCPE());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	//http.formLogin();
//		http.authorizeRequests().antMatchers("/user/*").hasAnyRole("user", "admin");
//		http.authorizeRequests().antMatchers("/admin/*").hasRole("admin");
//		http.exceptionHandling().accessDeniedPage("/403");

		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/login/**").permitAll();
		//http.authorizeRequests().antMatchers(HttpMethod.POST,"/tasks/**").hasAuthority("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	@Bean
	BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}

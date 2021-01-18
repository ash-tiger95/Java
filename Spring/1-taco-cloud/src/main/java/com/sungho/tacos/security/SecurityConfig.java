package com.sungho.tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception { // HTTP 보안을 구성하는 메서드
		http
		.authorizeRequests()
			.antMatchers("/design", "/orders")
				.access("hasRole('ROLE_USER')")
			.antMatchers("/", "/**")
				.access("permitAll")
		.and()
			.httpBasic();
	}
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder());
	}

	// 1. 인증을 하기 위해 사용자를 찾는 방법을 지정하는 코드를 작성해야 한다.
	
	// 2. 방법1) 인메모리 사용자 스토어 -> 사용자 정보를 직접 지정
	/*
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception { // 사용자 인증 정보를 구성하는 메서드
		auth
			.inMemoryAuthentication()
			.withUser("user1")
			.password("{noop}password1")
			.authorities("ROLE_USER") // .roles("ADMIN")과 동일함
			.and()
			.withUser("user2")
			.password("{noop}password2")
			.authorities("ROLE_USER"); // .roles("ADMIN");과 동일함
	}
	*/
	
	// 2. 방법2) JDBC 기반의 사용자 스토어
	/*
	@Autowired
    DataSource dataSource;
	
    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
        throws Exception {
          auth
	          .jdbcAuthentication()
	          .dataSource(dataSource)
	          .usersByUsernameQuery("select username, password, enabled from users where username=?")
	          .authoritiesByUsernameQuery("select username, authority from authorities where username=?")
	          .passwordEncoder(new NoEncodingPasswordEncoder()); // 비밀번호 암호화 필수!
	}
	*/
	
	// 2. 방법3) LDAP 기반의 사용자 스토어
	/*
	@Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
        throws Exception {
		auth
			.ldapAuthentication()
			.userSearchBase("ou=people")
			.userSearchFilter("(uid={0})")
			.groupSearchBase("ou=groups")
			.groupSearchFilter("(member={0})")
			.contextSource()
			.root("dc=tacocloud,dc=com")
			.ldif("classpath:users.ldif")
			.and()
			.passwordCompare()
			.passwordEncoder(new BCryptPasswordEncoder())
			.passwordAttribute("userPasscode");
    }
    */

}
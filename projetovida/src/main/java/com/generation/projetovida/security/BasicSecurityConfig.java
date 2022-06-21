package com.generation.projetovida.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//Serve para comparar os dados digitados com os dados salvos no banco de dados
	@Autowired
	private UserDetailsService userDetailsService;
	
	//Usuario em memoria PARA TESTE
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
		
		auth.userDetailsService(userDetailsService);
		auth.inMemoryAuthentication()
		.withUser("root")
		.password(passwordEncoder().encode("root"))
		.authorities("ROLE_USER");
		
	}
	
	//notação que deixa uma função acessivel globalmente(em toda a minha aplicação)
	@Bean	
	//Função de criptografa a senha digitada
	public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/**").permitAll()
			.antMatchers("/usuario/logar").permitAll()
			.antMatchers("/usuario/cadastrar").permitAll()
			.antMatchers("/produto").permitAll()
			.antMatchers("/categoria").permitAll() 
			.antMatchers("/atualizar").permitAll() 
	     	.antMatchers(HttpMethod.OPTIONS).permitAll()// Vai mostar as opcoes de métooos diponieis na sua api
			.anyRequest().authenticated()
			.and().httpBasic()
			.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().cors()// Liberar o acesso do front-end pro back-end
			.and().csrf().disable();
		
	}

}
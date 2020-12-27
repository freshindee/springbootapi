package com.fitscorp.j2eemobileapi.restservices.restservices.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fitscorp.j2eemobileapi.restservices.restservices.filters.JwtRequestFilter;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.JwtUserDetailService;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtUserDetailService userDetailService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	    auth.parentAuthenticationManager(authenticationManagerBean());
	    auth.userDetailsService(userDetailService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().ignoringAntMatchers("/users/*").and()
		http.csrf().disable().authorizeRequests().antMatchers("/users/*").permitAll()
			.antMatchers("/-1/**").permitAll()
			.antMatchers("/images/**").permitAll()
			.antMatchers("/{0-9}/products/**").permitAll()
			.antMatchers("/{0-9}/sub/**").permitAll()
			.antMatchers("/promotions/**").permitAll()
			.antMatchers("/usersregistrations/").permitAll()
			.anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint();
    }
	
	@Bean
	PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}

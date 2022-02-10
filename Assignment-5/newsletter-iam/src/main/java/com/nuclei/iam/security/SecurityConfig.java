package com.nuclei.iam.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The type Security config.
 */
@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  /**
   * The User details service.
   */
  private final UserDetailsService userDetailsService;
  /**
   * The Crypt password encoder.
   */
  private final BCryptPasswordEncoder cryptPasswordEncoder;
  /**
   * The Un authorized user authentication entry point.
   */
  private final UnAuthorizedUserAuthenticationEntryPoint unAuthorizedUserAuthenticationEntryPoint;
  /**
   * The Security filter.
   */
  private final SecurityFilter securityFilter;
  
  /**
   * Authentication manager authentication manager.
   *
   * @return the authentication manager
   *
   * @throws Exception the exception
   */
  @Override
  @Bean
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }
  
  /**
   * Configure.
   *
   * @param auth the auth
   *
   * @throws Exception the exception
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(cryptPasswordEncoder);
  }
  
  /**
   * Configure.
   *
   * @param http the http
   *
   * @throws Exception the exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()    //Disabling CSRF as not using form based login
        .authorizeRequests()
        .antMatchers("/auth/logout").authenticated()
        .antMatchers(
            "/auth/**",
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**"
        ).permitAll()
        .anyRequest().authenticated()
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(unAuthorizedUserAuthenticationEntryPoint)
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
    ;
  }
}


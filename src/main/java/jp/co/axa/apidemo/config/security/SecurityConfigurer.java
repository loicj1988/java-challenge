package jp.co.axa.apidemo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import jp.co.axa.apidemo.filters.JwtRequestFilter;

/**
 * Spring security configuration class
 * 
 * Declare custom security configuration
 * 
 * @author Loic
 * @version 0.0.1
 */
@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

  @Autowired
  private EmployeeUserDetailsService employeeUserDetailsService;

  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(employeeUserDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // Setup security
    // Disabled for "/authenticate"
    // Stateless
    http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().authorizeRequests().antMatchers("/api/v1/authenticate").permitAll().anyRequest()
        .authenticated();

    // Add jwt filter
    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    // Creating Authentication manager bean
    return super.authenticationManager();
  }

  /**
   * Temporary bean to prevent password encryption for sample application.
   * 
   * @return The password encoder instance.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}


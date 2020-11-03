package jp.co.axa.apidemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jp.co.axa.apidemo.models.requests.AuthenticationRequest;
import jp.co.axa.apidemo.models.responses.AuthenticationResponse;
import jp.co.axa.apidemo.utils.JwtUtil;

/**
 * Controller used to authenticate user to the application
 * 
 * Provide end point to authenticate client
 * 
 * @author Loic
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtils;

  /**
   * Generate authentication token for employee API
   * 
   * @param request The body containing username and password
   * @return The jwt tokens to access other API
   */
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
      @RequestBody AuthenticationRequest request) {

    // Authenticate user
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

    // Generate token
    final String jwt = jwtUtils.generateToken(request.getUsername());

    // Return response
    return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(jwt),
        HttpStatus.OK);


  }
}

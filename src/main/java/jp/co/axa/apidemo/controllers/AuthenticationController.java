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

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtils;

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

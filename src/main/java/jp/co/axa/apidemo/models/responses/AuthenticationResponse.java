package jp.co.axa.apidemo.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class AuthenticationResponse {

  @Getter
  private final String jwt;

}

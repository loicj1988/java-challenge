package jp.co.axa.apidemo.models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

  @Getter
  @Setter
  private String username;

  @Getter
  @Setter
  private String password;
}

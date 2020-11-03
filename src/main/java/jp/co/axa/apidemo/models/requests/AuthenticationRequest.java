package jp.co.axa.apidemo.models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Loic
 * @version 0.0.1
 *
 */
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

  /**
   * The username
   */
  @Getter
  @Setter
  private String username;

  /**
   * The user password
   */
  @Getter
  @Setter
  private String password;
}

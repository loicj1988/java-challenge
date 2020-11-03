package jp.co.axa.apidemo.exceptions;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Default exception response
 * 
 * @author Loic
 * @version 0.0.1
 *
 */
@AllArgsConstructor
public class ExceptionResponse {

  @Getter
  @Setter
  private Date timestamp;

  @Getter
  @Setter
  private String message;

  @Getter
  @Setter
  private String details;

}

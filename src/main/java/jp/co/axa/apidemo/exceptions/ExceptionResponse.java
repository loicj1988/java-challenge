package jp.co.axa.apidemo.exceptions;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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

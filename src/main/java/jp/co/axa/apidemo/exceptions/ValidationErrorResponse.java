package jp.co.axa.apidemo.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ValidationErrorResponse {

  @Getter
  @Setter
  private Date timestamp;

  @Getter
  @Setter
  private String message;

  @Getter
  @Setter
  private List<String> fieldErrors = new ArrayList<>();
}

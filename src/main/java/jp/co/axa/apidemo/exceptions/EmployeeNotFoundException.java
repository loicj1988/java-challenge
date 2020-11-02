package jp.co.axa.apidemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {

  /**
   * serial version ID
   */
  private static final long serialVersionUID = 1L;

  public EmployeeNotFoundException(Long id) {
    super(String.format("Employee with id %s not found", id));
  }
}

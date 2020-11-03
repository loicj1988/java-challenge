package jp.co.axa.apidemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when employee already exists in database
 * 
 * @author Loic
 * @version 0.0.1
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeAlreadyExistsException extends RuntimeException {

  /**
   * serial version ID
   */
  private static final long serialVersionUID = 1L;

  public EmployeeAlreadyExistsException(Long id) {
    super(String.format("Employee with id %s already exists", id));
  }
}

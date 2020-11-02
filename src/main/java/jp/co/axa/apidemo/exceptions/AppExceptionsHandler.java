package jp.co.axa.apidemo.exceptions;

import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.fasterxml.jackson.databind.JsonMappingException;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {

    ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<Object>(exceptionResponse, new HttpHeaders(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), "");
    return new ResponseEntity<Object>(exceptionResponse, headers, status);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
    validationErrorResponse.setTimestamp(new Date());
    validationErrorResponse.setMessage("Validation error");
    validationErrorResponse.setFieldErrors(ex.getBindingResult().getFieldErrors().stream()
        .map((fe) -> fe.getDefaultMessage()).collect(Collectors.toList()));

    return new ResponseEntity<Object>(validationErrorResponse, headers, status);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> handleJsonMappingException(JsonMappingException ex) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), ex.getMessage(), ex.getOriginalMessage());
    return new ResponseEntity<Object>(exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> handleConverterErrors(MethodArgumentTypeMismatchException ex) {

    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), "");
    return new ResponseEntity<Object>(exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);

  }

  @ExceptionHandler(value = {EmployeeNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex,
      WebRequest request) {

    ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<Object>(exceptionResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);

  }

}

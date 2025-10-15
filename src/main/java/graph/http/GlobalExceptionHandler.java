package graph.http;

import graph.exception.InvalidGraphInputException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InvalidGraphInputException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleInvalidGraphInput(InvalidGraphInputException ex) {
    return new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        ex.getMessage(),
        LocalDateTime.now()
    );
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleIllegalArgument(IllegalArgumentException ex) {
    return new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        ex.getMessage(),
        LocalDateTime.now()
    );
  }
}
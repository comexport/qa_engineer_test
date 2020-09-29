
package br.com.comexport.exception;

import static java.util.Collections.singletonList;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

     @ExceptionHandler(QATestException.class)
     public ResponseEntity<ErroInfo> handleCustomException(HttpServletRequest request, QATestException ex) {

          String message = ex.getMessage();
          HttpStatus httpStatus = ex.getStatus();
          return new ResponseEntity<>(buildErrorInfo(request, ex, singletonList(message)), httpStatus);
     }

     private ErroInfo buildErrorInfo(HttpServletRequest request, Exception qaTestexception, List<String> messages) {

          return ErroInfo.builder().timestamp(LocalDateTime.now()).messages(messages).exception(qaTestexception.getClass().getSimpleName()).path(request.getRequestURI()).build();
     }

     @ResponseStatus(HttpStatus.BAD_REQUEST)
     @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
     public @ResponseBody ErroInfo handleNumberFormatException(HttpServletRequest request, QATestException ex) {

          return buildErrorInfo(request, ex, singletonList(ex.getMessage()));
     }
     
     @ResponseStatus(HttpStatus.NOT_FOUND)
     @ExceptionHandler({ NotFound.class })
     public @ResponseBody ErroInfo handleNotFoundCustom(HttpServletRequest request, QATestException ex) {

          return buildErrorInfo(request, ex, singletonList(ex.getMessage()));
     }

     
}
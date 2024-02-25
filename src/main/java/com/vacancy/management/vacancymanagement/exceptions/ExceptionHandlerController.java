package com.vacancy.management.vacancymanagement.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class ExceptionHandlerController {
  private MessageSource messageSource;

  public ExceptionHandlerController(MessageSource message) {
    this.messageSource = message;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorMessageDTO>> handleArgumentNotValidException(MethodArgumentNotValidException e){
    List<ErrorMessageDTO> dto = new ArrayList<>();
    e.getBindingResult().getFieldErrors().forEach(error -> {
      String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
      ErrorMessageDTO err = new ErrorMessageDTO(message, error.getField());
      dto.add(err);
    });

    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
  } 
}

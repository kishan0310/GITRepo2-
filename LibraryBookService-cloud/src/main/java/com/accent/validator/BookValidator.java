package com.accent.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.accent.service.dto.library.Book;

@Component
public class BookValidator implements Validator {

  private  static Logger logger = LoggerFactory.getLogger(BookValidator.class);

  @Override
  public boolean supports(Class<?> clas) {
    logger.debug("Inside supports");
    return Book.class.equals(clas);
  }

  @Override
  public void validate(Object obj, Errors errors) {
    logger.debug("Inside validate");
    ValidationUtils.rejectIfEmpty(errors, "bookISBN", "bookISBN.error");
  }
}

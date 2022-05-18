package task.project.loancalculator.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import task.project.loancalculator.exception.LoanCalculatorException;

@ControllerAdvice
public class ApplicationExceptionHandler {

  @ExceptionHandler(NumberFormatException.class)
  public ModelAndView handleNumberFormatException(NumberFormatException exception) {

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("message", "Invalid loan amount or loan term value.");
    modelAndView.setStatus(HttpStatus.BAD_REQUEST);
    modelAndView.setViewName("index");

    return modelAndView;
  }

  @ExceptionHandler(LoanCalculatorException.class)
  public ModelAndView handleLoanCalculatorException(LoanCalculatorException exception) {

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("message", exception.getLocalizedMessage());
    modelAndView.setStatus(HttpStatus.BAD_REQUEST);
    modelAndView.setViewName("index");

    return modelAndView;
  }
}

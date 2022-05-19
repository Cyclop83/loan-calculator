package task.project.loancalculator.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import task.project.loancalculator.service.LoanCalculatorService;

/**
 * A controller class, which provides communication with view pages.
 */
@Controller
@RequestMapping("/")
public class LoanCalculatorController {

  /**
   * Interest rate, which is provided via properties file.
   */
  private Double interestRate;

  /**
   * LoanCalculatorService object to access business logic.
   */
  private LoanCalculatorService service;

  public LoanCalculatorController(@Value("${interestRate}") Double interestRate, LoanCalculatorService service) {
    this.interestRate = interestRate;
    this.service = service;
  }

  /**
   * This controller provides access to the application's main page, and sends interest rate value in response.
   *
   * @return
   */
  @GetMapping("/")
  public ModelAndView setInterestRate() {
    ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("interestRate", interestRate);
    return modelAndView;
  }

  /**
   * This controller takes loan amount and loan term as request parameters and returns a list of
   * LoanPaymentMonthlyDetails object, which represents the complete loan payment schedule.
   *
   * @param loanAmount
   * @param loanTerm
   * @return
   */
  @PostMapping("/calculate")
  public ModelAndView resultTableController(@RequestParam Integer loanAmount, @RequestParam Integer loanTerm) {
    ModelAndView modelAndView = new ModelAndView("loan_calculation");
    modelAndView.addObject("paymentSchedule",
        service.calculateLoanPaymentsSchedule(loanAmount, loanTerm, interestRate));
    return modelAndView;
  }

}

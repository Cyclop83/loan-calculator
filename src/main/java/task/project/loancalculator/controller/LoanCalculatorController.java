package task.project.loancalculator.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import task.project.loancalculator.service.LoanCalculatorService;

@Controller
@RequestMapping("/")
public class LoanCalculatorController {

  private Double interestRate;

  private LoanCalculatorService service;

  public LoanCalculatorController(@Value("${interestRate}") Double interestRate, LoanCalculatorService service) {
    this.interestRate = interestRate;
    this.service = service;
  }

  @GetMapping("/")
  public ModelAndView setInterestRate() {
    ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("interestRate", interestRate);
    return modelAndView;
  }

  @PostMapping("/calculate")
  public ModelAndView resultTableController(@RequestParam Integer loanAmount, @RequestParam Integer loanTerm) {
    ModelAndView modelAndView = new ModelAndView("loan_calculation");
    modelAndView.addObject("paymentSchedule", service.calculateMonthlyPercentPayment(loanAmount, loanTerm, interestRate));
    return modelAndView;
  }

}

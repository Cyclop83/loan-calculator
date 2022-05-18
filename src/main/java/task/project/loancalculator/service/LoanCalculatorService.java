package task.project.loancalculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import task.project.loancalculator.exception.LoanCalculatorException;
import task.project.loancalculator.model.LoanPaymentMonthlyDetails;

@Service
public class LoanCalculatorService {

  private Integer loanAmountLowerLimit;

  private Integer loanAmountUpperLimit;

  private Integer loanTermLowerLimit;

  private Integer loanTermUpperLimit;

  public LoanCalculatorService(@Value("${loanAmountLowerLimit}") Integer loanAmountLowerLimit,
      @Value("${loanAmountUpperLimit}") Integer loanAmountUpperLimit,
      @Value("${loanTermLowerLimit}") Integer loanTermLowerLimit,
      @Value("${loanTermUpperLimit}") Integer loanTermUpperLimit) {
    this.loanAmountLowerLimit = loanAmountLowerLimit;
    this.loanAmountUpperLimit = loanAmountUpperLimit;
    this.loanTermLowerLimit = loanTermLowerLimit;
    this.loanTermUpperLimit = loanTermUpperLimit;
  }

  public List<LoanPaymentMonthlyDetails> calculateMonthlyPercentPayment(final Integer loanAmount,
      final Integer loanTerm, final Double loanInterestRate) {

    validateLoanAmountRange(loanAmount);
    validateLoanTermRange(loanTerm);

    final List<LoanPaymentMonthlyDetails> result = new ArrayList<>();
    final List<String> paymentSchedule = populateLoanPaymentSchedule(loanTerm);

    BigDecimal decimalLoanTotal = new BigDecimal(loanAmount);
    BigDecimal monthlyInterestPayment = new BigDecimal("0.00");
    BigDecimal monthlyPrincipalPayment = new BigDecimal("0.00");
    BigDecimal monthlyTotalPayment = calculateMonthlyPayment(loanAmount, loanTerm, loanInterestRate);
    Double interestPerMonth = loanInterestRate / (100.0 * 12);

    for (int i = 1; i <= loanTerm; i++) {
      monthlyInterestPayment = decimalLoanTotal.multiply(new BigDecimal(interestPerMonth));
      monthlyPrincipalPayment = monthlyTotalPayment.subtract(monthlyInterestPayment);
      decimalLoanTotal = decimalLoanTotal.subtract(monthlyPrincipalPayment);
      result.add(
          new LoanPaymentMonthlyDetails(i, paymentSchedule.get(i - 1),
              monthlyPrincipalPayment.setScale(2, RoundingMode.HALF_EVEN),
              monthlyInterestPayment.setScale(2, RoundingMode.HALF_EVEN),
              decimalLoanTotal.setScale(2, RoundingMode.HALF_EVEN),
              monthlyTotalPayment.setScale(2, RoundingMode.HALF_EVEN)));
    }
    return result;
  }

  private BigDecimal calculateMonthlyPayment(final Integer loanAmount, final Integer loanTerm,
      final Double loanInterestRate) {

    final Integer minusLoanTerm = -loanTerm;
    final Double interestPerMonth = loanInterestRate / (100.0 * 12);
    final Double incrementedLoanInterest = 1 + interestPerMonth;
    final Double multiplicand = (interestPerMonth / (1 - Math.pow(incrementedLoanInterest, minusLoanTerm)));

    return new BigDecimal(loanAmount).multiply(new BigDecimal(multiplicand));
  }

  private List<String> populateLoanPaymentSchedule(final Integer loanTerm) {

    final List<String> result = new ArrayList<>();

    String loanPaymentScheduleInput = null;
    Integer yearsCounter = 1;
    Integer monthsInAYearCounter = 1;

    for (int i = 1; i <= loanTerm; i++) {
      loanPaymentScheduleInput = String.format("Year %s, Month %s", yearsCounter, monthsInAYearCounter);
      result.add(loanPaymentScheduleInput);
      if (monthsInAYearCounter % 12 == 0) {
        monthsInAYearCounter = 1;
        yearsCounter = ++yearsCounter;
      } else {
        ++monthsInAYearCounter;
      }
    }
    return result;
  }

  private void validateLoanAmountRange(Integer loanAmount) {
    Optional.ofNullable(loanAmount)
        .filter(Objects::nonNull)
        .filter(amount -> amount >= loanAmountLowerLimit && amount <= loanAmountUpperLimit)
        .orElseThrow(() -> new LoanCalculatorException("Loan amount is out of range."));
  }

  private void validateLoanTermRange(Integer loanTerm) {
    Optional.ofNullable(loanTerm)
        .filter(Objects::nonNull)
        .filter(term -> term >= loanTermLowerLimit && term <= loanTermUpperLimit)
        .orElseThrow(() -> new LoanCalculatorException("Loan term is out of range."));
  }
}

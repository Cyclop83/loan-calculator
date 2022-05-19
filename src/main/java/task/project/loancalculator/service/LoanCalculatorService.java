package task.project.loancalculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import task.project.loancalculator.exception.LoanCalculatorException;
import task.project.loancalculator.model.LoanPaymentMonthlyDetails;
import task.project.loancalculator.model.LoanPaymentMonthlyDetails.LoanPaymentMonthlyDetailsBuilder;

/**
 * A service class to provide calculations of LoanPaymentMonthlyDetails parameters based on request.
 */

@Service
public class LoanCalculatorService {

  /**
   * A lower limit of loan amount, provided via properties file.
   */
  private Integer loanAmountLowerLimit;

  /**
   * An upper limit of loan amount, provided via properties file.
   */
  private Integer loanAmountUpperLimit;

  /**
   * A lower limit of loan term, provided via properties file.
   */
  private Integer loanTermLowerLimit;

  /**
   * An upper limit of loan term, provided via properties file.
   */
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

  /**
   * This method calculates a complete loan payments schedule in a form of LoanPaymentMonthlyDetails objects list.
   * Calculations are based on loan amount, loan term and loan interest rate parameters. First two are provided by the
   * client via request, the latter is taken from property file.
   *
   * @param loanAmount
   * @param loanTerm
   * @param loanInterestRate
   * @return
   */
  public List<LoanPaymentMonthlyDetails> calculateLoanPaymentsSchedule(final Integer loanAmount,
      final Integer loanTerm, final Double loanInterestRate) {

    validateLoanAmountRange(loanAmount);
    validateLoanTermRange(loanTerm);

    final List<LoanPaymentMonthlyDetails> result = new ArrayList<>();
    final List<String> paymentYearAndMonth = populatePaymentYearAndMonth(loanTerm);

    BigDecimal decimalLoanAmount = new BigDecimal(loanAmount);
    BigDecimal monthlyInterestPayment = new BigDecimal("0.00");
    BigDecimal monthlyPrincipalPayment = new BigDecimal("0.00");
    BigDecimal monthlyTotalPayment = calculateMonthlyPayment(loanAmount, loanTerm, loanInterestRate);
    Double interestPerMonth = loanInterestRate / (100.0 * 12);

    for (int i = 1; i <= loanTerm; i++) {
      monthlyInterestPayment = decimalLoanAmount.multiply(new BigDecimal(interestPerMonth));
      monthlyPrincipalPayment = monthlyTotalPayment.subtract(monthlyInterestPayment);
      decimalLoanAmount = decimalLoanAmount.subtract(monthlyPrincipalPayment);
      result.add(new LoanPaymentMonthlyDetailsBuilder()
          .paymentNumber(i)
          .paymentYearAndMonth(paymentYearAndMonth.get(i - 1))
          .principalPayment(monthlyPrincipalPayment.setScale(2, RoundingMode.HALF_EVEN))
          .interestPayment(monthlyInterestPayment.setScale(2, RoundingMode.HALF_EVEN))
          .principalBalance(decimalLoanAmount.setScale(2, RoundingMode.HALF_EVEN))
          .totalMonthlyPayment(monthlyTotalPayment.setScale(2, RoundingMode.HALF_EVEN))
          .build());
    }
    return result;
  }

  /**
   * This method calculates a total value of monthly payment to cover both principal and interest debts.
   *
   * @param loanAmount
   * @param loanTerm
   * @param loanInterestRate
   * @return
   */
  private BigDecimal calculateMonthlyPayment(final Integer loanAmount, final Integer loanTerm,
      final Double loanInterestRate) {

    final Integer minusLoanTerm = -loanTerm;
    final Double interestPerMonth = loanInterestRate / (100.0 * 12);
    final Double incrementedLoanInterest = 1 + interestPerMonth;
    final Double multiplicand = (interestPerMonth / (1 - Math.pow(incrementedLoanInterest, minusLoanTerm)));

    return new BigDecimal(loanAmount).multiply(new BigDecimal(multiplicand));
  }

  /**
   * A method to populate a list of strings, which represent abstract year and month information in loan schedule table.
   * The length of the list is equal to loanTerm.
   *
   * @param loanTerm
   * @return
   */
  private List<String> populatePaymentYearAndMonth(final Integer loanTerm) {

    final List<String> result = new ArrayList<>();

    String yearAndMonthRecord = null;
    Integer yearsCounter = 1;
    Integer monthsInAYearCounter = 1;

    for (int i = 1; i <= loanTerm; i++) {
      yearAndMonthRecord = String.format("Year %s, Month %s", yearsCounter, monthsInAYearCounter);
      result.add(yearAndMonthRecord);
      if (monthsInAYearCounter % 12 == 0) {
        monthsInAYearCounter = 1;
        yearsCounter = ++yearsCounter;
      } else {
        ++monthsInAYearCounter;
      }
    }
    return result;
  }

  /**
   * A method to validate a range of loanAmount.
   *
   * @param loanAmount
   */
  private void validateLoanAmountRange(Integer loanAmount) {
    Optional.of(loanAmount)
        .filter(amount -> amount >= loanAmountLowerLimit && amount <= loanAmountUpperLimit)
        .orElseThrow(() -> new LoanCalculatorException("Loan amount is out of range."));
  }

  /**
   * A method to validate a range of LoanTerm.
   *
   * @param loanTerm
   */
  private void validateLoanTermRange(Integer loanTerm) {
    Optional.of(loanTerm)
        .filter(term -> term >= loanTermLowerLimit && term <= loanTermUpperLimit)
        .orElseThrow(() -> new LoanCalculatorException("Loan term is out of range."));
  }
}

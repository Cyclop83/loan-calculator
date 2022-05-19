package task.project.loancalculator.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * This class represents a record in a loan payments schedule table which will be provided to the client.
 */

public class LoanPaymentMonthlyDetails {

  /**
   * A serial number of payment in payment schedule.
   */
  private Integer paymentNumber;

  /**
   * Abstract year and month information in payment schedule.
   */
  private String paymentYearAndMonth;

  /**
   * A payment toward the original amount of a loan.
   */
  private BigDecimal principalPayment;

  /**
   * A payment toward the interest.
   */
  private BigDecimal interestPayment;

  /**
   * A remaining amount of principal debt, which must be repaid after the current payment.
   */
  private BigDecimal principalBalance;

  /**
   * A total amount which must be paid every month to repay both principal and interest debts.
   */
  private BigDecimal totalMonthlyPayment;

  public LoanPaymentMonthlyDetails() {
  }

  private LoanPaymentMonthlyDetails(LoanPaymentMonthlyDetailsBuilder builder) {
    this.paymentNumber = builder.paymentNumber;
    this.paymentYearAndMonth = builder.paymentYearAndMonth;
    this.principalPayment = builder.principalPayment;
    this.interestPayment = builder.interestPayment;
    this.principalBalance = builder.principalBalance;
    this.totalMonthlyPayment = builder.totalMonthlyPayment;
  }

  public Integer getPaymentNumber() {
    return paymentNumber;
  }

  public void setPaymentNumber(Integer paymentNumber) {
    this.paymentNumber = paymentNumber;
  }

  public String getPaymentYearAndMonth() {
    return paymentYearAndMonth;
  }

  public void setPaymentYearAndMonth(String paymentYearAndMonth) {
    this.paymentYearAndMonth = paymentYearAndMonth;
  }

  public BigDecimal getPrincipalPayment() {
    return principalPayment;
  }

  public void setPrincipalPayment(BigDecimal principalPayment) {
    this.principalPayment = principalPayment;
  }

  public BigDecimal getInterestPayment() {
    return interestPayment;
  }

  public void setInterestPayment(BigDecimal interestPayment) {
    this.interestPayment = interestPayment;
  }

  public BigDecimal getPrincipalBalance() {
    return principalBalance;
  }

  public void setPrincipalBalance(BigDecimal principalBalance) {
    this.principalBalance = principalBalance;
  }

  public BigDecimal getTotalMonthlyPayment() {
    return totalMonthlyPayment;
  }

  public void setTotalMonthlyPayment(BigDecimal totalMonthlyPayment) {
    this.totalMonthlyPayment = totalMonthlyPayment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoanPaymentMonthlyDetails that = (LoanPaymentMonthlyDetails) o;
    return Objects.equals(paymentNumber, that.paymentNumber) && Objects.equals(paymentYearAndMonth,
        that.paymentYearAndMonth) && Objects.equals(principalPayment, that.principalPayment)
        && Objects.equals(interestPayment, that.interestPayment) && Objects.equals(principalBalance,
        that.principalBalance) && Objects.equals(totalMonthlyPayment, that.totalMonthlyPayment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentNumber, paymentYearAndMonth, principalPayment, interestPayment, principalBalance,
        totalMonthlyPayment);
  }

  @Override
  public String toString() {
    return "LoanPaymentMonthlyDetails{" +
        "paymentNumber=" + paymentNumber +
        ", paymentYearAndMonth='" + paymentYearAndMonth + '\'' +
        ", principalPayment=" + principalPayment +
        ", interestPayment=" + interestPayment +
        ", principalBalance=" + principalBalance +
        ", totalMonthlyPayment=" + totalMonthlyPayment +
        '}';
  }

  /**
   * A builder to initialize LoanPaymentMonthlyDetails object
   */
  public static class LoanPaymentMonthlyDetailsBuilder {

    private Integer paymentNumber;

    private String paymentYearAndMonth;

    private BigDecimal principalPayment;

    private BigDecimal interestPayment;

    private BigDecimal principalBalance;

    private BigDecimal totalMonthlyPayment;

    public LoanPaymentMonthlyDetailsBuilder paymentNumber(Integer paymentNumber) {
      this.paymentNumber = paymentNumber;
      return this;
    }

    public LoanPaymentMonthlyDetailsBuilder paymentYearAndMonth(String paymentYearAndMonth) {
      this.paymentYearAndMonth = paymentYearAndMonth;
      return this;
    }

    public LoanPaymentMonthlyDetailsBuilder principalPayment(BigDecimal principalPayment) {
      this.principalPayment = principalPayment;
      return this;
    }

    public LoanPaymentMonthlyDetailsBuilder interestPayment(BigDecimal interestPayment) {
      this.interestPayment = interestPayment;
      return this;
    }

    public LoanPaymentMonthlyDetailsBuilder principalBalance(BigDecimal principalBalance) {
      this.principalBalance = principalBalance;
      return this;
    }

    public LoanPaymentMonthlyDetailsBuilder totalMonthlyPayment(BigDecimal totalMonthlyPayment) {
      this.totalMonthlyPayment = totalMonthlyPayment;
      return this;
    }

    public LoanPaymentMonthlyDetails build() {
      return new LoanPaymentMonthlyDetails(this);
    }
  }
}

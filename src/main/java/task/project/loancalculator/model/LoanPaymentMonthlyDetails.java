package task.project.loancalculator.model;

import java.math.BigDecimal;

public class LoanPaymentMonthlyDetails {

  private Integer paymentNumber;

  private String paymentYearAndMonth;

  private BigDecimal principalPayment;

  private BigDecimal interestPayment;

  private BigDecimal principalBalance;

  private BigDecimal totalMonthlyPayment;

  public LoanPaymentMonthlyDetails() {
  }

  public LoanPaymentMonthlyDetails(Integer paymentNumber, String paymentYearAndMonth,
      BigDecimal principalPayment, BigDecimal interestPayment, BigDecimal principalBalance,
      BigDecimal totalMonthlyPayment) {
    this.paymentNumber = paymentNumber;
    this.paymentYearAndMonth = paymentYearAndMonth;
    this.principalPayment = principalPayment;
    this.interestPayment = interestPayment;
    this.principalBalance = principalBalance;
    this.totalMonthlyPayment = totalMonthlyPayment;
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

}

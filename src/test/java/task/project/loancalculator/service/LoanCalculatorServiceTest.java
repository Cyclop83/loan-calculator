package task.project.loancalculator.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.project.loancalculator.exception.LoanCalculatorException;
import task.project.loancalculator.model.LoanPaymentMonthlyDetails;
import task.project.loancalculator.model.LoanPaymentMonthlyDetails.LoanPaymentMonthlyDetailsBuilder;

public class LoanCalculatorServiceTest {

  private LoanCalculatorService service;

  @BeforeEach
  void initializeLoanCalculatorService() {
    service = new LoanCalculatorService(100_000, 5_000_000, 12, 60);
  }

  @Test
  void calculateLoanPaymentsSchedule_ShouldReturnList() {
    Assertions.assertEquals(service.calculateLoanPaymentsSchedule(200_000, 12, 15.2), populateTestList());
  }

  @Test
  void calculateLoanPaymentsSchedule_ShouldThrowLoanCalculatorException_WhenIncorrectLoanAmount() {
    Assertions.assertThrows(LoanCalculatorException.class,
        () -> service.calculateLoanPaymentsSchedule(10_000, 12, 15.2));
  }

  @Test
  void calculateLoanPaymentsSchedule_ShouldThrowLoanCalculatorException_WhenIncorrectLoanTerm() {
    Assertions.assertThrows(LoanCalculatorException.class,
        () -> service.calculateLoanPaymentsSchedule(100_000, 5, 15.2));
  }

  private List<LoanPaymentMonthlyDetails> populateTestList() {

    List<LoanPaymentMonthlyDetails> testList = new ArrayList<>();

    testList.add(new LoanPaymentMonthlyDetailsBuilder()
        .paymentNumber(1)
        .paymentYearAndMonth("Year 1, Month 1")
        .principalPayment(new BigDecimal("15537.21"))
        .interestPayment(new BigDecimal("2533.33"))
        .principalBalance(new BigDecimal("184462.79"))
        .totalMonthlyPayment(new BigDecimal("18070.54"))
        .build());

    testList.add(new LoanPaymentMonthlyDetailsBuilder()
        .paymentNumber(2)
        .paymentYearAndMonth("Year 1, Month 2")
        .principalPayment(new BigDecimal("15734.01"))
        .interestPayment(new BigDecimal("2336.53"))
        .principalBalance(new BigDecimal("168728.78"))
        .totalMonthlyPayment(new BigDecimal("18070.54"))
        .build());

    testList.add(new LoanPaymentMonthlyDetailsBuilder()
        .paymentNumber(3)
        .paymentYearAndMonth("Year 1, Month 3")
        .principalPayment(new BigDecimal("15933.31"))
        .interestPayment(new BigDecimal("2137.23"))
        .principalBalance(new BigDecimal("152795.47"))
        .totalMonthlyPayment(new BigDecimal("18070.54"))
        .build());

    testList.add(new LoanPaymentMonthlyDetailsBuilder()
        .paymentNumber(4)
        .paymentYearAndMonth("Year 1, Month 4")
        .principalPayment(new BigDecimal("16135.13"))
        .interestPayment(new BigDecimal("1935.41"))
        .principalBalance(new BigDecimal("136660.33"))
        .totalMonthlyPayment(new BigDecimal("18070.54"))
        .build());

    testList.add(new LoanPaymentMonthlyDetailsBuilder()
        .paymentNumber(5)
        .paymentYearAndMonth("Year 1, Month 5")
        .principalPayment(new BigDecimal("16339.51"))
        .interestPayment(new BigDecimal("1731.03"))
        .principalBalance(new BigDecimal("120320.82"))
        .totalMonthlyPayment(new BigDecimal("18070.54"))
        .build());

    testList.add(new LoanPaymentMonthlyDetailsBuilder()
        .paymentNumber(6)
        .paymentYearAndMonth("Year 1, Month 6")
        .principalPayment(new BigDecimal("16546.48"))
        .interestPayment(new BigDecimal("1524.06"))
        .principalBalance(new BigDecimal("103774.34"))
        .totalMonthlyPayment(new BigDecimal("18070.54"))
        .build());

    testList.add(new LoanPaymentMonthlyDetailsBuilder()
        .paymentNumber(7)
        .paymentYearAndMonth("Year 1, Month 7")
        .principalPayment(new BigDecimal("16756.07"))
        .interestPayment(new BigDecimal("1314.47"))
        .principalBalance(new BigDecimal("87018.27"))
        .totalMonthlyPayment(new BigDecimal("18070.54"))
        .build());

    testList.add(new LoanPaymentMonthlyDetailsBuilder()
        .paymentNumber(8)
        .paymentYearAndMonth("Year 1, Month 8")
        .principalPayment(new BigDecimal("16968.31"))
        .interestPayment(new BigDecimal("1102.23"))
        .principalBalance(new BigDecimal("70049.96"))
        .totalMonthlyPayment(new BigDecimal("18070.54"))
        .build());

    testList.add(new LoanPaymentMonthlyDetailsBuilder()
        .paymentNumber(9)
        .paymentYearAndMonth("Year 1, Month 9")
        .principalPayment(new BigDecimal("17183.24"))
        .interestPayment(new BigDecimal("887.30"))
        .principalBalance(new BigDecimal("52866.72"))
        .totalMonthlyPayment(new BigDecimal("18070.54"))
        .build());

    testList.add(new LoanPaymentMonthlyDetailsBuilder()
        .paymentNumber(10)
        .paymentYearAndMonth("Year 1, Month 10")
        .principalPayment(new BigDecimal("17400.90"))
        .interestPayment(new BigDecimal("669.65"))
        .principalBalance(new BigDecimal("35465.82"))
        .totalMonthlyPayment(new BigDecimal("18070.54"))
        .build());

    testList.add(new LoanPaymentMonthlyDetailsBuilder()
        .paymentNumber(11)
        .paymentYearAndMonth("Year 1, Month 11")
        .principalPayment(new BigDecimal("17621.31"))
        .interestPayment(new BigDecimal("449.23"))
        .principalBalance(new BigDecimal("17844.51"))
        .totalMonthlyPayment(new BigDecimal("18070.54"))
        .build());

    testList.add(new LoanPaymentMonthlyDetailsBuilder()
        .paymentNumber(12)
        .paymentYearAndMonth("Year 1, Month 12")
        .principalPayment(new BigDecimal("17844.51"))
        .interestPayment(new BigDecimal("226.03"))
        .principalBalance(new BigDecimal("0.00"))
        .totalMonthlyPayment(new BigDecimal("18070.54"))
        .build());

    return testList;
  }
}

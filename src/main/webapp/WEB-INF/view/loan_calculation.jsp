<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loan Calculation Result</title>
</head>
<h1 align="center">Loan Calculation Table.</h1>
<body>

    <div align="center">
      <table border="1" cellpadding="5">
        <tr>
             <th>PaymentNumber</th>
             <th>PaymentYearAndMonth</th>
             <th>PrincipalPayment</th>
             <th>InterestPayment</th>
             <th>PrincipalBalance</th>
             <th>TotalMonthlyPayment</th>
        </tr>
        <c:forEach items="${paymentSchedule}" var="payment">
        <tr>
             <td>${payment.paymentNumber}</td>
             <td>${payment.paymentYearAndMonth}</td>
             <td>${payment.principalPayment}</td>
             <td>${payment.interestPayment}</td>
             <td>${payment.principalBalance}</td>
             <td>${payment.totalMonthlyPayment}</td>
        </tr>
        </c:forEach>
      </table>
    </div>
    <br>
    <br>
    <p align="left"><a href="/">Back</a><p>
</body>
</html>
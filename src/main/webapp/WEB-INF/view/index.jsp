<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loan Calculator</title>
</head>
<body>
      <h1 align="center">Welcome to the Loan Calculator</h1>
      <p align="left">
      <h3>Please input amount of money you want to loan and desired loan term.<br>
      Take note, that available loan amount range is from 100 000 to 5 000 000.<br>
      And possible loan terms are from 12 to 60 months.
      Your interest rate is shown below.</h2>
      <p>
      <form action="/calculate" method="post">
      	Loan Amount : <input type="text" name="loanAmount" required> <br>
      	Last Term : <input type="text" name="loanTerm" required> <br>
      	Interest Rate : <input type="text" name="interest_rate" value=${interestRate} disabled="disabled" required> <br>
      	<input type="submit" value="Calculate"> <br>
      </form>
      <p align="center">${message}</p>
</body>
</html>

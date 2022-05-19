<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loan Calculator</title>
<style>
      div {
        margin-bottom: 10px;
      }
      label {
        display: inline-block;
        width: 150px;
        text-align: right;
      }
</style>
</head>
<body>
      <h1 align="center">Welcome to the Loan Calculator</h1>
      <p align="left">
      <h3>Please input amount of money you want to loan and desired loan term.<br>
      Take note, that available loan amount range is from 100 000 to 5 000 000.<br>
      And possible loan term is in range from 12 to 60 months.<br>
      Your interest rate is : ${interestRate}%.</h2>
      <p>
      <form action="/calculate" method="post">
       	<div>
              <label>Loan Amount</label>
              <input type="text" name="loanAmount" required>
        </div>
      	<div>
              <label>Loan Term</label>
              <input type="text" name="loanTerm" required>
        </div>
        <div>
              <label></label>
              <input type="submit" name="Calculate" required>
        </div>
      </form>
      <p align="center">${message}</p>
</body>
</html>

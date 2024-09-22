# Banking-Transactions-API-Task

This is a simple Banking API built with Spring Boot that allows users to create accounts, transfer funds between accounts, and retrieve transaction history.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17 or higher
- Maven
- Postman or cURL for testing the API

## Build the project using Maven:
mvn clean install

## Run the Spring Boot application:
mvn spring-boot:run

## Testing the API
Example cURL Commands

Create an account:
curl -X POST "http://localhost:8080/api/accounts/create?accountId=1&initialBalance=500.00"

Transfer funds:
curl -X POST "http://localhost:8080/api/accounts/transfer" -H "Content-Type: application/json" -d '{"fromAccountId":1,"toAccountId":2,"amount":100.00}'

Get transaction history:
curl -X GET "http://localhost:8080/api/accounts/1/transactions"

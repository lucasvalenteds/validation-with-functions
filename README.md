# Validation with Functions

This experiment is intended to explore concepts of validation of data using Java 8 features and APIs, like `Optional`, `Streams` and `Lambdas`. The main goal is to develop an alternative Strategy pattern implementation using functions instead of the classical GOF approach targeting Object-Oriented Programming paradigm. Also the code was written as a TDD practice.

The code is a code to validate a POJO named `User` that has to attributes: `code` and `password`. The validator participant is designed to receive as constructor parameter a set o rules, which makes the validator only responsible for execute the validation process instead of know the rules themselves.

## How to run

| Description | Command |
| :--- | :--- |
| Run unit tests | `./gradlew test` |
| Run main method | `./gradlew run --args="<code> <password>"`

## Sample code

```java
var codeValidator = new UserCodeValidator(List.of(
    UserCodeValidator.isCodeSignPositive,
    UserCodeValidator.isCodeLengthThree));

var passwordValidator = new UserPasswordValidator(List.of(
    UserPasswordValidator.isPasswordEightCharsLength,
    UserPasswordValidator.containsAtLeastOneHashbang));

var userInstance = new UserBuilder()
    .withCode(101).validatedBy(codeValidator)
    .withPassword("s3cr3t#!").validatedBy(passwordValidator)
    .create();

userInstance.ifPresentOrElse(
    System.out::println, // User{code=101, password='s3cr3t#!'}
    () -> System.out.println("Invalid code or password"));
```

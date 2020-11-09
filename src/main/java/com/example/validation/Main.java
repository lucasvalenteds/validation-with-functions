package com.example.validation;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var codeValidator = new UserCodeValidator(List.of(
            UserCodeValidator.isCodeSignPositive,
            UserCodeValidator.isCodeLengthThree));

        var passwordValidator = new UserPasswordValidator(List.of(
            UserPasswordValidator.isPasswordEightCharsLength,
            UserPasswordValidator.containsAtLeastOneHashbang));

        var userInstance = new UserBuilder()
            .withCode(-10).validatedBy(codeValidator)
            .withPassword("s3cr3t#!").validatedBy(passwordValidator)
            .create();

        userInstance.ifPresentOrElse(
            (user) -> System.out.printf("code=%d%npassword=%s%n", user.getCode(), user.getPassword()),
            () -> System.err.println("Invalid code or password")
        );
    }
}

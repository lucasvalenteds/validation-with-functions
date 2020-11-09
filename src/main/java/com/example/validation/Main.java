package com.example.validation;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var code = Integer.parseInt(args[0]);
        var password = args[1];

        var userInstance = new UserBuilder()
            .withCode(code).validatedBy(new UserCodeValidator(List.of(
                UserCodeValidator.isCodeSignPositive,
                UserCodeValidator.isCodeLengthThree)))
            .withPassword(password).validatedBy(new UserPasswordValidator(List.of(
                UserPasswordValidator.isPasswordEightCharsLength,
                UserPasswordValidator.containsAtLeastOneHashbang)))
            .create();

        userInstance.ifPresentOrElse(
            (user) -> System.out.printf("code=%d%npassword=%s%n", user.getCode(), user.getPassword()),
            () -> System.err.println("Invalid code or password")
        );
    }
}

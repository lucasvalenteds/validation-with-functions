package com.example.validation;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> arguments = Arrays.asList(args);
        var code = Integer.parseInt(arguments.get(0));
        var password = arguments.get(1);

        var codeValidator = new UserCodeValidator(List.of(
            UserCodeValidator.isCodeSignPositive,
            UserCodeValidator.isCodeLengthThree));

        var passwordValidator = new UserPasswordValidator(List.of(
            UserPasswordValidator.isPasswordEightCharsLength,
            UserPasswordValidator.containsAtLeastOneHashbang));

        var userInstance = new UserBuilder()
            .withCode(code).validatedBy(codeValidator)
            .withPassword(password).validatedBy(passwordValidator)
            .create();

        userInstance.ifPresentOrElse(
            (user) -> System.out.printf("code=%d%npassword=%s%n", user.getCode(), user.getPassword()),
            () -> System.err.println("Invalid code or password")
        );
    }
}

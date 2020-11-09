package com.example.validation;

import java.util.List;
import java.util.Optional;

public final class UserBuilder {

    private int code;
    private String password;
    private UserPropertyValidator<Integer> codeValidator;
    private UserPropertyValidator<String> passwordValidator;

    public UserBuilder validatedBy(final UserCodeValidator validator) {
        this.codeValidator = Optional
            .ofNullable(validator)
            .orElse(new UserCodeValidator(List.of()));

        return this;
    }

    public UserBuilder withCode(final int code) {
        this.code = code;
        return this;
    }

    public UserBuilder validatedBy(final UserPasswordValidator validator) {
        this.passwordValidator = Optional
            .ofNullable(validator)
            .orElse(new UserPasswordValidator(List.of()));

        return this;
    }

    public UserBuilder withPassword(final String name) {
        this.password = name;
        return this;
    }

    public Optional<User> create() {
        var isCodeValid = codeValidator.isValid(code);
        var isPasswordValid = passwordValidator.isValid(password);

        if (isCodeValid && isPasswordValid) {
            return Optional.of(new User(code, password));
        }

        return Optional.empty();
    }
}

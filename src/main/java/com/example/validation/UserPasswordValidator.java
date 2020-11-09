package com.example.validation;

import java.util.List;
import java.util.function.Function;

public final class UserPasswordValidator extends UserPropertyValidator<String> {

    public static final Function<String, Boolean> isPasswordEightCharsLength = password -> password.length() >= 8;
    public static final Function<String, Boolean> containsAtLeastOneHashbang = password -> password.contains("#!");

    public UserPasswordValidator(final List<Function<String, Boolean>> validationRules) {
        super(validationRules);
    }
}

package com.example.validation;

import java.util.List;
import java.util.function.Function;

public final class UserCodeValidator extends UserPropertyValidator<Integer> {

    public static final Function<Integer, Boolean> isCodeSignPositive = code -> !String.valueOf(code).contains("-");
    public static final Function<Integer, Boolean> isCodeLengthThree = code -> String.valueOf(code).length() == 3;

    public UserCodeValidator(final List<Function<Integer, Boolean>> validationRules) {
        super(validationRules);
    }
}

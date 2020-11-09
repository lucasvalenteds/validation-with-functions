package com.example.validation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserPropertyValidator<T> {

    private final List<Function<T, Boolean>> validationRules;

    public UserPropertyValidator(final List<Function<T, Boolean>> validationRules) {
        this.validationRules = Optional
            .ofNullable(validationRules)
            .orElse(List.of())
            .stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    public List<Function<T, Boolean>> getValidationRules() {
        return List.copyOf(validationRules);
    }

    public Boolean isValid(final T property) {
        return Optional.ofNullable(property)
            .map(it -> validationRules.stream().allMatch(rule -> rule.apply(it)))
            .orElse(false);
    }
}

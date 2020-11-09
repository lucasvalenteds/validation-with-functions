package com.example.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserPropertyValidatorTest {

    @DisplayName("If none validation rule set is passed an empty list is used")
    @Test
    void testNoneValidationRulesInformed() {
        var validator = new UserCodeValidator(null);

        assertThat(validator.getValidationRules().size()).isZero();
        assertThat(validator.getValidationRules()).isEqualTo(List.of());
    }

    @DisplayName("If a null rule is passed to the validator it ignores it")
    @Test
    void testIgnoreNullValidationRules() {
        ArrayList<Function<Integer, Boolean>> validationRulesWithNullElement = new ArrayList<>();
        validationRulesWithNullElement.add(null);

        var validator = new UserPropertyValidator<>(validationRulesWithNullElement);

        assertThat(validator.getValidationRules())
            .doesNotContain(validationRulesWithNullElement.get(0))
            .isEmpty();
    }

    @DisplayName("The getter of the validation rules returns an immutable list")
    @Test
    void testGetterReturnsImmutableList() {
        var validator = new UserPropertyValidator<String>(List.of());

        List<Function<String, Boolean>> rules = validator.getValidationRules();

        assertThatThrownBy(() -> rules.add(code -> true))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("The isValid method treats null values as invalid")
    @Test
    void testNullSafety() {
        assertThat(new UserPropertyValidator<String>(List.of()).isValid(null)).isFalse();
        assertThat(new UserPropertyValidator<Integer>(List.of()).isValid(null)).isFalse();
    }
}

package com.example.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class UserCodeValidatorTest {

    private final List<Function<Integer, Boolean>> validationRules = List.of(
        UserCodeValidator.isCodeSignPositive,
        UserCodeValidator.isCodeLengthThree);

    private final UserCodeValidator validator = new UserCodeValidator(validationRules);

    @Nested
    class Usage {

        @DisplayName("A code with a positive number of three digits should be considered valid")
        @Test
        void testValidCode() {
            var isCodeValid = validator.isValid(123);

            assertThat(isCodeValid).isTrue();
        }

        @DisplayName("A code with other than three digits should be considered invalid")
        @ParameterizedTest(name = "Code {0} is invalid")
        @ValueSource(ints = {-4321, -321, -21, -1, 0, 12, 1234})
        void testInvalidCode(int code) {
            var isCodeValid = validator.isValid(code);

            assertThat(isCodeValid).isFalse();
        }
    }

    @Nested
    class Rules {

        @DisplayName("Code length rule considers sign to calculate length")
        @Test
        void testCodeLengthRuleWorks() {
            assertThat(UserCodeValidator.isCodeLengthThree.apply(-1)).isFalse();
            assertThat(UserCodeValidator.isCodeLengthThree.apply(-11)).isTrue();
            assertThat(UserCodeValidator.isCodeLengthThree.apply(-111)).isFalse();
            assertThat(UserCodeValidator.isCodeLengthThree.apply(-1111)).isFalse();

            assertThat(UserCodeValidator.isCodeLengthThree.apply(1)).isFalse();
            assertThat(UserCodeValidator.isCodeLengthThree.apply(11)).isFalse();
            assertThat(UserCodeValidator.isCodeLengthThree.apply(111)).isTrue();
            assertThat(UserCodeValidator.isCodeLengthThree.apply(1111)).isFalse();
        }

        @DisplayName("Code sign rule works with positive and negative numbers")
        @Test
        void testCodeSignIsPositiveWorks() {
            assertThat(UserCodeValidator.isCodeSignPositive.apply(1)).isTrue();
            assertThat(UserCodeValidator.isCodeSignPositive.apply(-1)).isFalse();
        }
    }
}

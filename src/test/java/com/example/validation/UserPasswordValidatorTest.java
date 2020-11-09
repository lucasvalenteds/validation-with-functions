package com.example.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class UserPasswordValidatorTest {

    private final List<Function<String, Boolean>> validationRules = List.of(
        UserPasswordValidator.isPasswordEightCharsLength,
        UserPasswordValidator.containsAtLeastOneHashbang);

    private final UserPasswordValidator validator = new UserPasswordValidator(validationRules);

    @Nested
    class Usage {

        @DisplayName("A password with at least eight chars and with a hashbang is considered valid")
        @Test
        void testValidPassword() {
            var isPasswordValid = validator.isValid("#!eight!");

            assertThat(isPasswordValid).isTrue();
        }

        @DisplayName("A password shorter than eight chars and without a hashbang is considered invalid")
        @ParameterizedTest(name = "Password {0} is invalid")
        @ValueSource(strings = {"1234567", "12345678", "123#!45"})
        void testInvalidPassword(String password) {
            var isPasswordValid = validator.isValid(password);

            assertThat(isPasswordValid).isFalse();
        }
    }

    @Nested
    class Rules {

        @DisplayName("Password length should be eight chars or longer")
        @Test
        void testPasswordLengthWorks() {
            assertThat(UserPasswordValidator.isPasswordEightCharsLength.apply("1234567")).isFalse();
            assertThat(UserPasswordValidator.isPasswordEightCharsLength.apply("12345678")).isTrue();
            assertThat(UserPasswordValidator.isPasswordEightCharsLength.apply("123456789")).isTrue();
        }

        @DisplayName("Hashbang can be detected in the password")
        @Test
        void testPasswordHasHashbang() {
            assertThat(UserPasswordValidator.containsAtLeastOneHashbang.apply("#!")).isTrue();
            assertThat(UserPasswordValidator.containsAtLeastOneHashbang.apply("123#!")).isTrue();
            assertThat(UserPasswordValidator.containsAtLeastOneHashbang.apply("#!123")).isTrue();

            assertThat(UserPasswordValidator.containsAtLeastOneHashbang.apply("#")).isFalse();
            assertThat(UserPasswordValidator.containsAtLeastOneHashbang.apply("123#")).isFalse();
            assertThat(UserPasswordValidator.containsAtLeastOneHashbang.apply("#123")).isFalse();

            assertThat(UserPasswordValidator.containsAtLeastOneHashbang.apply("!")).isFalse();
            assertThat(UserPasswordValidator.containsAtLeastOneHashbang.apply("123!")).isFalse();
            assertThat(UserPasswordValidator.containsAtLeastOneHashbang.apply("!123")).isFalse();
        }
    }
}

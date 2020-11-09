package com.example.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UserBuilderTest {

    private final UserCodeValidator codeValidator = new UserCodeValidator(List.of(
        UserCodeValidator.isCodeSignPositive,
        UserCodeValidator.isCodeLengthThree));

    private final UserPasswordValidator passwordValidator = new UserPasswordValidator(List.of(
        UserPasswordValidator.isPasswordEightCharsLength,
        UserPasswordValidator.containsAtLeastOneHashbang));

    @DisplayName("It can applies validation rules on creation")
    @Test
    void testApplyValidationRules() {
        Optional<User> userInstance = new UserBuilder()
            .withCode(101).validatedBy(codeValidator)
            .withPassword("s3cr3t#!").validatedBy(passwordValidator)
            .create();

        assertThat(userInstance.isPresent()).isTrue();

        var user = userInstance.get();
        assertThat(user.getPassword()).isEqualTo("s3cr3t#!");
        assertThat(user.getCode()).isEqualTo(101);
    }

    public static Stream<Arguments> invalidArguments() {
        return Stream.of(
            Arguments.of(-1, "s3cr3t#!"),
            Arguments.of(101, "invalid")
        );
    }

    @DisplayName("It returns empty when code or password are invalid")
    @ParameterizedTest
    @MethodSource("invalidArguments")
    void testApplyValidationRulesInvalid(int code, String password) {
        Optional<User> userInstance = new UserBuilder()
            .withCode(code).validatedBy(codeValidator)
            .withPassword(password).validatedBy(passwordValidator)
            .create();

        assertThat(userInstance.isPresent()).isFalse();
    }
}

package com.example.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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
}

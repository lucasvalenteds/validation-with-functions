package com.example.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @DisplayName("It has a password and a numeric code")
    @Test
    void testUserHasCodeAndName() {
        var dave = new User(1, "passwordIsNotAPassword");

        assertThat(dave.getPassword()).isEqualTo("passwordIsNotAPassword");
        assertThat(dave.getCode()).isEqualTo(1);
    }

    @DisplayName("The toString method is override to return code and password")
    @Test
    void testToStringProvidesFields() {
        var amy = new User(2, "superSecurePassword");

        assertThat(amy.toString())
            .contains(amy.getCode() + "")
            .contains(amy.getPassword());
    }
}

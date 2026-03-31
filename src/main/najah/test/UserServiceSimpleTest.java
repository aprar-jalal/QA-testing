package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.UserService;

class UserServiceSimpleTest {
    UserService user;

    @BeforeEach
    void setUp() throws Exception {
        user = new UserService();
    }
  //***************************************************************

    @ParameterizedTest
    @ValueSource(strings = {"admin@test.com", "user1@test.com"})
    void testIsValidEmail(String email) {
        assertTrue(user.isValidEmail(email));
    }
  //***************************************************************

    @ParameterizedTest
    @ValueSource(strings = {"user@test","usertest.com","pp"})
    void testInValidEmail(String email) {
        assertFalse(user.isValidEmail(email));
        assertFalse(user.isValidEmail(null)); 
    }
  //***************************************************************

    @Test
    void testAuthenticate() {
        assertTrue(user.authenticate("admin", "1234"));
    }
  //***************************************************************

    @Test
    void testNotAuthenticate() {
        assertFalse(user.authenticate("admin", "wrong"));
        assertFalse(user.authenticate(null, "1234")); 
        assertFalse(user.authenticate("admin", null));
        assertFalse(user.authenticate(null, null));
        assertFalse(user.authenticate("aa", "1234"));

    }
}
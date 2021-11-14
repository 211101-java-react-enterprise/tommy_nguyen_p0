package com.revature.banking.services;

import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.models.AppUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTestSuite {

    UserService sut; //system under test

    @Before
    public void testCaseSetup() {
        sut = new UserService();
    }

    @Test
    public void test_isUserValid_returnsTrue_givenValidUser() {
        //Arrange
        AppUser validUser = new AppUser("Tommy", "Nguyen", "tommy.n@revature.net", "tommyn", "password");

        //Act
        boolean actualResult = sut.isUserValid(validUser);

        //Assert
        Assert.assertTrue("Expected user to be considered valid", actualResult);

    }

    @Test
    public void test_isUserValid_returnFalse_givenUserWithInvalidFirstName() {
        //Arrange
        AppUser invalidUser_1 = new AppUser("", "Nguyen", "tommy.n@revature.net", "tommyn", "password");
        AppUser invalidUser_2 = new AppUser(null, "Nguyen", "tommy.n@revature.net", "tommyn", "password");
        AppUser invalidUser_3 = new AppUser("         ", "Nguyen", "tommy.n@revature.net", "tommyn", "password");

        //Act
        boolean actualResult_1 = sut.isUserValid(invalidUser_1);
        boolean actualResult_2 = sut.isUserValid(invalidUser_2);
        boolean actualResult_3 = sut.isUserValid(invalidUser_2);

        //Assert
        Assert.assertFalse("Expected user to be considered false", actualResult_1);
        Assert.assertFalse("Expected user to be considered false", actualResult_2);
        Assert.assertFalse("Expected user to be considered false", actualResult_3);
    }

    //TODO: correct implementation so that UserService#registerNewUser is tested in isolation (mock/fake the AppUserDAO)
    @Test
    public void test_registerNewUser_returnTrue_givenValidUser() {
        //Arrange
        AppUser validUser = new AppUser("Tommy", "Nguyen", "tommy.n@revature.net", "tommyn", "password");

        //Act
        boolean actualResult = sut.registerNewUser(validUser);

        //Assert
        Assert.assertTrue("Expected result to be true with valid user provided.",actualResult);
    }
    @Test (expected = InvalidRequestException.class)
    public void test_registerNewUser_throwsInvalidRequestException_givenInvalidUser() {
        sut.registerNewUser(null);


    }

    //TODO implement test case
    @Test
    public void test_registerNewUser_throwsInvalidRequestException_givenUserWithDuplicatedEmailorUsername() {

    }
}

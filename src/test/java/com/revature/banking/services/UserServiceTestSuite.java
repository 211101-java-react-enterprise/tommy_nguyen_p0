package com.revature.banking.services;

import com.revature.banking.daos.AppUserDAO;
import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.exceptions.ResourcePersistenceException;
import com.revature.banking.models.AppUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class UserServiceTestSuite {

    UserService sut; //system under test
    AppUserDAO mockUserDAO;

    @Before
    public void testCaseSetup() {
        //TODO fix test following login refactor
        mockUserDAO = Mockito.mock(AppUserDAO.class);
        sut = new UserService(mockUserDAO);
    }

    @Test
    public void test_isUserValid_returnsTrue_givenValidUser() {
        //Arrange
        AppUser validUser = new AppUser("Tommy", "Nguyen", "tommy.n@revature.net", "tommyn", "password", 0);

        //Act
        boolean actualResult = sut.isUserValid(validUser);

        //Assert
        Assert.assertTrue("Expected user to be considered valid", actualResult);

    }

    @Test
    public void test_isUserValid_returnFalse_givenUserWithInvalidFirstName() {
        //Arrange
        AppUser invalidUser_1 = new AppUser("", "Nguyen", "tommy.n@revature.net", "tommyn", "password", 0);
        AppUser invalidUser_2 = new AppUser(null, "Nguyen", "tommy.n@revature.net", "tommyn", "password", 0);
        AppUser invalidUser_3 = new AppUser("         ", "Nguyen", "tommy.n@revature.net", "tommyn", "password", 0);

        //Act
        boolean actualResult_1 = sut.isUserValid(invalidUser_1);
        boolean actualResult_2 = sut.isUserValid(invalidUser_2);
        boolean actualResult_3 = sut.isUserValid(invalidUser_2);

        //Assert
        Assert.assertFalse("Expected user to be considered false", actualResult_1);
        Assert.assertFalse("Expected user to be considered false", actualResult_2);
        Assert.assertFalse("Expected user to be considered false", actualResult_3);
    }

    @Test
    public void test_registerNewUser_returnTrue_givenValidUser() {
        //Arrange
        AppUser validUser = new AppUser("Tommy", "Nguyen", "tommy.n@revature.net", "tommyn", "password", 0);
        when(mockUserDAO.findUserByUsername(validUser.getUsername())).thenReturn(null);
        when(mockUserDAO.findUserByEmail(validUser.getEmail())).thenReturn(null);
        when(mockUserDAO.save(validUser)).thenReturn(validUser);

        //Act
        boolean actualResult = sut.registerNewUser(validUser);

        //Assert
        Assert.assertTrue("Expected result to be true with valid user provided.",actualResult);
        verify(mockUserDAO, times(1)).save(validUser);
    }

    @Test (expected = ResourcePersistenceException.class)
    public void test_registerNewUser_throwsResourcePersistenceException_givenValidUserWithTakenUsername() {
        //Arrange
        AppUser validUser = new AppUser("Tommy", "Nguyen", "tommy.n@revature.net", "tommyn", "password", 0);
        when(mockUserDAO.findUserByUsername(validUser.getUsername())).thenReturn(new AppUser());
        when(mockUserDAO.findUserByEmail(validUser.getEmail())).thenReturn(null);
        when(mockUserDAO.save(validUser)).thenReturn(validUser);

        //Act
        try {
            boolean actualResult = sut.registerNewUser(validUser);
        } finally {
            //Assert
            verify(mockUserDAO, times(0)).save(validUser);
        }
    }

    @Test (expected = ResourcePersistenceException.class)
    public void test_registerNewUser_throwsResourcePersistenceException_givenValidUserWithTakenEmail() {
        //Arrange
        AppUser validUser = new AppUser("Tommy", "Nguyen", "tommy.n@revature.net", "tommyn", "password", 0);
        when(mockUserDAO.findUserByUsername(validUser.getUsername())).thenReturn(new AppUser());
        when(mockUserDAO.findUserByEmail(validUser.getEmail())).thenReturn(null);
        when(mockUserDAO.save(validUser)).thenReturn(validUser);

        //Act
        try {
            boolean actualResult = sut.registerNewUser(validUser);
        } finally {
            //Assert
            verify(mockUserDAO, times(0)).save(validUser);
        }
    }

    @Test (expected = InvalidRequestException.class)
    public void test_registerNewUser_throwsInvalidRequestException_givenInvalidUser() {
        sut.registerNewUser(null);
    }
}

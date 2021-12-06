package com.revature.tixter.services;

import com.revature.nimble.OrmServiceDriver;
import com.revature.nimble.TEMP.TempUsers;
import com.revature.tixter.daos.UserDAO;
import com.revature.tixter.exceptions.InvalidRequestException;
import com.revature.tixter.exceptions.ResourcePersistenceException;
import com.revature.tixter.models.Users;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class UserServiceTest {
    public UserService sut;
    public UserDAO dao;
    public OrmServiceDriver orm;

    @Before
    public void testStart(){
        orm=mock(OrmServiceDriver.class);
        dao=new UserDAO(orm);
        sut=new UserService(dao);
    }

    @After
    public void testEnd(){
        sut=null;
        dao=null;
        orm=null;
    }

    @Test
    public void testIsUserValid(){
        //Unit test to isUserValid method: testing different cases of invalid input
        Users testUser1=null;
        Users testUser2=new Users("Test","Tester","T.Tester@revature.com","password",19);
        Users testUser3=new Users(null,"Tester","test@revature.com","password",20);
        Users testUser4=new Users("Test","","test@revature.com","password",20);
        Users testUser5=new Users("Test","Tester",null,"password",20);
        Users testUser6=new Users("Test","Tester","test@revature.com","",20);
        Users testUser7=new Users("Test","Tester","test@revature.com","password",-10);

        boolean testcase1=sut.isUserValid(testUser1);
        boolean testcase2=sut.isUserValid(testUser2);
        boolean testcase3=sut.isUserValid(testUser3);
        boolean testcase4=sut.isUserValid(testUser4);
        boolean testcase5=sut.isUserValid(testUser5);
        boolean testcase6=sut.isUserValid(testUser6);
        boolean testcase7=sut.isUserValid(testUser7);

        Assert.assertFalse(testcase1);
        Assert.assertTrue(testcase2);
        Assert.assertFalse(testcase3);
        Assert.assertFalse(testcase4);
        Assert.assertFalse(testcase5);
        Assert.assertFalse(testcase6);
        Assert.assertFalse(testcase7);
    }

    @Test
    public void testRegister() throws IllegalAccessException, InstantiationException {

        Users testUser1=new Users("Mock","Mock","m.m@revature.com","password",21);
        when(orm.reading(Users.class,testUser1.email)).thenReturn(null);
        when(orm.creating(testUser1)).thenReturn(testUser1);
        // Assert
        boolean actualResult =sut.register(testUser1);

        Assert.assertTrue("Expected result to be true with valid user provided.", actualResult);
        verify(orm, times(1)).creating(testUser1);
    }



    @Test(expected = ResourcePersistenceException.class)
    public void testOccupiedEmail() throws IllegalAccessException, InstantiationException {
        Users testUser1=new Users("Test","Tester","Test.Tester@revature.com","password",19);

        when(orm.reading(Users.class,testUser1.email)).thenReturn(testUser1);
        try {
            boolean acutalRsult=sut.register(testUser1);
        }finally {
            verify(orm,times(0)).creating(testUser1);
        }
    }

    @Test
    public void testAuthenticateUser() throws IllegalAccessException, InstantiationException {
        //prepare a mock user as fake output from orm to dao
        Users mockUsers=new Users();
        mockUsers.setEmail("Test.Tester@revature.com");
        mockUsers.setPassword("password");
        //return such user when dao calls orm
        when(orm.reading(Users.class,"Test.Tester@revature.com")).thenReturn(mockUsers);
        //expecting true
        boolean testcase1=sut.authenticateUser("Test.Tester@revature.com","password")!=null;
        Assert.assertTrue("Expecting true since password matches",testcase1);
        verify(orm,times(1)).reading(Users.class,"Test.Tester@revature.com");

    }

    @Test(expected = InvalidRequestException.class)
    public void testEmptyCredential() throws IllegalAccessException, InstantiationException {
        sut.authenticateUser(null,"");
    }


}

package com.revature.tixter.services;

import com.revature.tixter.models.Users;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class userServiceTest {
    userService sut;

    @Before
    public void testStart(){
        sut=new userService();
    }

    @After
    public void testEnd(){
        sut=null;
    }

    @Test
    public void testIsUserValid(){
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
}

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
        Users testUser=null;
        boolean testcase1=sut.isUserValid(testUser);
        Assert.assertFalse(testcase1);
    }
}

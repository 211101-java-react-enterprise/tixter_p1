package com.revature.tixter.services;

import com.revature.nimble.OrmServiceDriver;
import com.revature.tixter.daos.TicketDAO;
import com.revature.tixter.daos.UserDAO;
import com.revature.tixter.exceptions.InvalidRequestException;
import com.revature.tixter.exceptions.ResourcePersistenceException;
import com.revature.tixter.models.Tickets;
import com.revature.tixter.web.dtos.TicketRequest;
import org.junit.*;

import static org.mockito.Mockito.*;

public class TicketServiceTest {
    public TicketService sut;
    public TicketDAO dao;
    public OrmServiceDriver orm;

    @Before
    public void testStart(){
        orm=mock(OrmServiceDriver.class);
        dao=new TicketDAO(orm);
        sut=new TicketService(dao);
    }

    @After
    public void testEnd(){
        sut=null;
        dao=null;
        orm=null;
    }


    @Test
    public void testIsTicketValid(){
        Tickets testTicket1=new Tickets("","");
        Tickets testTicket2=new Tickets("Hello Tickets","asdfadsf");
        Tickets testTicket3=null;

        boolean testcase1= sut.isTicketValid(testTicket1);
        boolean testcase2= sut.isTicketValid(testTicket2);
        boolean testcase3= sut.isTicketValid(testTicket3);

        Assert.assertFalse(testcase1);
        Assert.assertTrue(testcase2);
        Assert.assertFalse(testcase3);
    }


//    @Test
//    public void TestCreateNewTicket() throws IllegalAccessException, InstantiationException {
//        TicketRequest dto1= new TicketRequest();
//        dto1.setName("Mock");
//        dto1.setPublisher("asdfadsflasdfj");
//        Tickets testTicket1=new Tickets("Mock","asdfadsflasdfj");
//        when(orm.creating(testTicket1)).thenReturn(testTicket1);
//
//        boolean actualResult=sut.createNewTicket(dto1);
//
//        Assert.assertTrue(actualResult);
//        verify(orm,times(1)).creating(testTicket1);
//    }


    @Test(expected = InvalidRequestException.class)
    public void testCreateEmptyName() throws IllegalAccessException, InstantiationException {
        TicketRequest dto1 = new TicketRequest();
        dto1.setName("");
        dto1.setPublisher("");
        Tickets tickets1=new Tickets("","");
        try {
            sut.createNewTicket(dto1);
        }finally {
            verify(orm,times(0)).creating(tickets1);
        }
    }

    @Ignore
    @Test(expected = ResourcePersistenceException.class)
    public void testCreateWithDatabaseCouldNotPersist() throws IllegalAccessException, InstantiationException {
        TicketRequest dto1 = new TicketRequest();
        dto1.setName("Test");
        dto1.setPublisher("12345");
        Tickets tickets1=new Tickets("Test","12345");
        when(orm.creating(tickets1)).thenReturn(null);
        try {
            sut.createNewTicket(dto1);
        }finally {
            verify(orm,times(1)).creating(tickets1);
        }
    }

    @Ignore
    @Test
    public void testUpdateTicketAvailability() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        when(orm.update(Tickets.class,"1",Tickets.class.getField("available"), 10)).thenReturn(true);
        boolean actualResult=sut.updateTicketAvailability("1",10);
        Assert.assertTrue(actualResult);
        verify(orm,times(1)).update(Tickets.class,"1",Tickets.class.getField("available"), 10);
    }

    @Test
    public void testFindById() throws IllegalAccessException, InstantiationException {
        Tickets mockTicket=new Tickets();
        when(orm.reading(Tickets.class,"asdfa")).thenReturn(mockTicket);
        boolean testcase1= (sut.findByTicketId("asdfa")!=null);
        Assert.assertTrue(testcase1);
        verify(orm,times(1)).reading(Tickets.class,"asdfa");
    }

    @Test(expected = InvalidRequestException.class)
    public void testFindEmptyId() throws IllegalAccessException, InstantiationException {
        try{
            sut.findByTicketId("");
        }finally {
            verify(orm,times(0)).reading(Tickets.class,"");
        }
    }




}

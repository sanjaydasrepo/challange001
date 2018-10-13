package com.seam.messages;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.seam.messages.pojo.Message;
import com.seam.services.MessageReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageReaderImplIntegrationTest {

	private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSSZ";
	@Autowired
	private MessageReader messageService;

	
	@Test
	 public void testAssertThatLatestMessage(){
		 
		 //Testing with the data from resources/db directory
		 
	    //	 String subscriberId = "e8549d50-31ec-451d-a9ba-44e13549ddb1";
		// String latestMessageTimestamp = "2018-06-18 15:01:04.176+0530";
		 
		 String subscriberId = "9020be9e-958a-4536-8164-4a8bbc17c3da";
		 String latestMessageTimestamp = "2018-06-18 15:02:54.786+0530";
		 
		 Message message = messageService.getLatestMessage(subscriberId);
		 assertEqualDates( latestMessageTimestamp , message.getCreatedAt());
	 }

	@Test
	public void testAssertThatFirstUnreadMessage(){

		// Testing with the data from resources/db directory

		// String subscriberId = "e8549d50-31ec-451d-a9ba-44e13549ddb1";
		// String latestMessageTimestamp = "2018-06-18 15:01:04.176+0530";

		String subscriberId = "9020be9e-958a-4536-8164-4a8bbc17c3da";
		String firstUnreadMessageTimestamp = "2018-06-18 15:02:42.786+0530";


		Message message = messageService.getFirstUnreadMessage(subscriberId);
		assertEqualDates( firstUnreadMessageTimestamp , message.getCreatedAt());
	}

	private static void assertEqualDates(String expectedStrDate, Date value) {

		 Date expectedDate;
		try {
			expectedDate = new SimpleDateFormat(DATE_PATTERN).parse(expectedStrDate);
			assertThat(expectedDate,is(value));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	

}

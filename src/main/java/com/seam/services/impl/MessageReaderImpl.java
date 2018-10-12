package com.seam.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.seam.messages.pojo.Message;
import com.seam.services.MessageReader;
import com.seam.messages.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MessageReaderImpl implements MessageReader {

	@Override
	public Message getLatestMessage(String subscriberId) {
		Date createdDate = null;
		Message latestMessage = null;
		try {
			// Read the message folder
			File folder = ResourceUtils.getFile("classpath:db");
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				
				File[] subListOfFiles = file.listFiles();
				if (subListOfFiles.length > 0) {
					for (File subFile : subListOfFiles) {
						if (subFile.isFile()) {
							// Get the message node from the file
							JsonNode messageNode = JsonLoader.fromFile(subFile);
							// Convert the message node to the corresponding object
							Message message = MapperUtils.getMapper().convertValue(messageNode, Message.class);
							//Check if subscriberid found
							if( subscriberId.equals( message.getSenderId() ) ) {
								if (  createdDate == null) {
									createdDate = message.getCreatedAt();
									latestMessage = message ;
								}else if( message.getCreatedAt().compareTo(createdDate) > 0) {
									createdDate = message.getCreatedAt();
									latestMessage = message;
								}
							}
						
						}

					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return latestMessage;
	}

	@Override
	public Message getFirstUnreadMessage(String subscriberId) {
		Date createdDate = null;
		Message firstUnreadMessage = null;
		try {
			File folder = ResourceUtils.getFile("classpath:db");
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				
				File[] subListOfFiles = file.listFiles();
				if (subListOfFiles.length > 0) {
					for (File subFile : subListOfFiles) {
						if (subFile.isFile()) {
							// Get the message node from the file
							JsonNode messageNode = JsonLoader.fromFile(subFile);
							// Convert the message node to the corresponding object
							Message message = MapperUtils.getMapper().convertValue(messageNode, Message.class);
							
							String jsonString = message.getReadAt().toString();
							Map<String, String> map = MapperUtils.getMapper().readValue(jsonString, new TypeReference<HashMap<String,String>>(){});
							System.out.println("gagagaga "+jsonString + " vall "+map);
						//	&& !msgReadTimestamp.equals("0")
							//
							
							//Check if subscriberid is found and the message read timestamp as 0 
							if( subscriberId.equals( message.getSenderId() ) ) {
								if (  createdDate == null) {
									createdDate = message.getCreatedAt();
									firstUnreadMessage = message ;
								}else if( message.getCreatedAt().compareTo(createdDate) < 0) {
									createdDate = message.getCreatedAt();
									firstUnreadMessage = message;
								}
							}
						
						}

					}
				}

			}
			
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		return firstUnreadMessage;
	}

}

package com.seam.messages.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The message object class
 */

public class Message {

    public String senderId;

    public String body;

    public List<String> attachmentIds;
   
 
    public Date createdAt;

    public JsonNode readAt; //Unread Message will have value as 0 and the key is the reciever id
 
    
    
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(String senderId, String body, List<String> attachmentIds, Date createdAt, JsonNode readAt) {
		super();
		this.senderId = senderId;
		this.body = body;
		this.attachmentIds = attachmentIds;
		this.createdAt = createdAt;
		this.readAt = readAt;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public List<String> getAttachmentIds() {
		return attachmentIds;
	}

	public void setAttachmentIds(List<String> attachmentIds) {
		this.attachmentIds = attachmentIds;
	}
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSSZ" , timezone="IST")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public JsonNode getReadAt() {
		return readAt;
	}

	public void setReadAt(JsonNode readAt) {
		this.readAt = readAt;
	}

}

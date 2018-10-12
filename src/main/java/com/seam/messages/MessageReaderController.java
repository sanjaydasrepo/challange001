package com.seam.messages;

import com.seam.messages.pojo.Message;
import com.seam.services.MessageReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller to get the messages
 */
@RestController
@RequestMapping(value = "/messages")
public class MessageReaderController {

    @Autowired
    private MessageReader messageReader;

    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    public Message getLatestMessage(@RequestParam("subscriberId") String subscriberId) {
        return messageReader.getLatestMessage(subscriberId);
    }

    @RequestMapping(value = "/unread", method = RequestMethod.GET)
    public Message getFirstUnreadMessage(@RequestParam("subscriberId") String subscriberId) {
        return messageReader.getFirstUnreadMessage(subscriberId);
    }
}

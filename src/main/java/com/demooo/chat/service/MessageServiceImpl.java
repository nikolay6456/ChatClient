package com.demooo.chat.service;

import java.sql.Timestamp;
import java.util.Date;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demooo.chat.dao.MessageDAO;
import com.demooo.chat.model.MessageModel;
import com.demooo.chat.model.MessageType;

@Service
@Component
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Override
	public void saveMessage (MessageModel message, MessageType messageType) {
		message.setMessageType(messageType);
		message.setCreatedAt(new Timestamp(new Date().getTime()));
		messageDAO.save(message);
	}

}


package com.demooo.chat.service;

import com.demooo.chat.model.MessageModel;
import com.demooo.chat.model.MessageType;

public interface MessageService {
	void saveMessage (MessageModel message, MessageType messageType);
}

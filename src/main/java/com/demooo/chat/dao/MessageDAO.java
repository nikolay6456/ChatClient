package com.demooo.chat.dao;

import com.demooo.chat.model.MessageModel;

public interface MessageDAO {
	MessageModel save(MessageModel message);
}

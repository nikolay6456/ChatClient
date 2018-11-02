package com.demooo.chat.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.demooo.chat.model.MessageModel;

@Repository
@Transactional
public class MessageDAOImpl implements MessageDAO{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	@Transactional
	public MessageModel save(MessageModel message) {
		entityManager.persist(message);
		return message;
	}

}

package com.demooo.chat.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Repository
@Table(name = "MESSAGES")
@XmlRootElement(name = "message")
@SequenceGenerator(name = "SEQ", initialValue = 1, allocationSize = 1)
public class MessageModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private MessageType messageType;
	private Date createdAt;
	private String payload;

	public MessageModel(Long id,  MessageType messageType, Date createdAt, String payload ) {
		super();
		this.id = id;
		this.messageType = messageType;
		this.createdAt = createdAt;
		this.payload = payload;
	}

	public MessageModel() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@XmlElement(name="payload")
	@Column(name = "PAYLOAD")
	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Column(name = "CREATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE")
	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	@Override
	@JsonIgnore
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((messageType == null) ? 0 : messageType.hashCode());
		result = prime * result + ((payload == null) ? 0 : payload.hashCode());
		return result;
	}

	@Override
	@JsonIgnore
	public boolean equals(Object obj) {

		MessageModel message = (MessageModel) obj;

		if ((this.payload == message.payload) 
			&& (this.messageType == message.messageType)
			&& (this.createdAt == message.createdAt)) {
			return true;
		}
		if (message.payload == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (id == null) {
			if (message.id != null) {
				return false;
			}
		} else if (!id.equals(message.id)) {
			return false;
		}
		return true;
	}
	
	@Override
	@JsonIgnore
	public String toString() {
		return String.format("Message [id=%s, messageType=%s, createdAt=%s, payload=%s]", id, messageType, createdAt,
				payload);
	}
}
package com.demooo.chat.controller;

import javax.jws.WebService;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demooo.chat.model.MessageModel;
import com.demooo.chat.model.MessageType;
import com.demooo.chat.service.MessageService;

@WebService
@RestController
@RequestMapping(value = "/")
public class EntryPoint {

	public static final String REGEX_PATTERN_TEXT = "^.{1,160}$";
	public static final String REGEX_PATTERN_EMOTION = "^\\D{2,9}$";

	@Autowired
	protected MessageService messageService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "/messages/{type}", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" })
	public ResponseEntity<?> saveMessage(@PathVariable(value = "type") String type,
			@Valid @RequestBody MessageModel message) {
		String payload = message.getPayload();
		switch (type) {
			case "send_text":
				if (!payload.matches(REGEX_PATTERN_TEXT)) {
					return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
				}
				messageService.saveMessage(message, MessageType.TEXT);
				break;
			case "send_emotion":
				if (!payload.matches(REGEX_PATTERN_EMOTION)) {
					return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
				}
				messageService.saveMessage(message, MessageType.EMOTION);
				break;
			default:
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}

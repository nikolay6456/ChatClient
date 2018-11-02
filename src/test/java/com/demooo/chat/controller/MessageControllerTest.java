package com.demooo.chat.controller;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demooo.chat.dao.MessageDAO;
import com.demooo.chat.model.MessageModel;
import com.demooo.chat.model.MessageType;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EntryPoint.class, secure = false)
public class MessageControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MessageDAO messageDAO;
	
	String exampleCorrectText = "{\"payload\":\"Test payload text\"}";
	String exampleCorrectEmotion = "{\"payload\":\":) (: E\"}";
	String exampleIncorrectText = "{\"payload\":\"12345678901234567890123456789012345678901234567890"
			+ "12345678901234567890123456789012345678901234567890123456789012345678901234567890"
			+ "123456789012345678901234567890 EXTRA Text over 160 symbols\"}";
	String exampleIncorrectEmo = "{\"payload\":\"EmoWith9\"}";
	
	@Test
	public void sendTextMessage() throws Exception{
		MessageModel mockMessage = new MessageModel((long) 1, MessageType.TEXT, new Timestamp(new Date().getTime()), "Sample Text 123");
		Mockito.when(messageDAO.save(Mockito.any(MessageModel.class))).thenReturn(mockMessage);
									  
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/messages/send_text")
				.accept(MediaType.APPLICATION_JSON).content(exampleCorrectText)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		RequestBuilder requestBuilder2 = MockMvcRequestBuilders
				.post("/messages/send_emotion")
				.accept(MediaType.APPLICATION_JSON).content(exampleCorrectEmotion)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result2 = mockMvc.perform(requestBuilder2).andReturn();
		MockHttpServletResponse response2 = result2.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response2.getStatus());
		
		RequestBuilder requestBuilder3 = MockMvcRequestBuilders
				.post("/messages/send_text")
				.accept(MediaType.APPLICATION_JSON).content(exampleIncorrectText)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result3 = mockMvc.perform(requestBuilder3).andReturn();
		MockHttpServletResponse response33 = result3.getResponse();
		assertEquals(HttpStatus.PRECONDITION_FAILED.value(), response33.getStatus());
		
		RequestBuilder requestBuilder4 = MockMvcRequestBuilders
				.post("/messages/send_emotion")
				.accept(MediaType.APPLICATION_JSON).content(exampleIncorrectEmo)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result4 = mockMvc.perform(requestBuilder4).andReturn();
		MockHttpServletResponse response4 = result4.getResponse();
		assertEquals(HttpStatus.PRECONDITION_FAILED.value(), response4.getStatus());

	}
			
}

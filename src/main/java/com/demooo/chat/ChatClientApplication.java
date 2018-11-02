package com.demooo.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ChatClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatClientApplication.class, args);
	}
}

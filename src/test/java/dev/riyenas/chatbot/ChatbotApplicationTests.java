package dev.riyenas.chatbot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatbotApplicationTests {

	private static Logger logger = LoggerFactory.getLogger(ChatbotApplicationTests.class);

	@Test
	@DisplayName("Log4j2 log 색상")
	void contextLoads() {
		logger.info("info");
		logger.error("error");
		logger.warn("warn");
	}
}

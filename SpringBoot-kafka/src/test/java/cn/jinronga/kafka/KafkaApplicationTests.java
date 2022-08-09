package cn.jinronga.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
class KafkaApplicationTests {

	private final static String TOPIC_NAME = "zhTest"; //topic的名称

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;


	@Test
	void contextLoads() {

		//发送功能就一行代码~
		kafkaTemplate.send(TOPIC_NAME,  "key", "test message send~");

	}

}

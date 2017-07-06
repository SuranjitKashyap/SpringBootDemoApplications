package io.sample.springbootstarter.integrationtest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import io.sample.springbootstarter.topic.Topic;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestClass {

	TestRestTemplate restTemplate = new TestRestTemplate();
	
	//Testing get topic by id service
	@Test
	public void testGetTopic() {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:3000/v1/api/topics/Spring", String.class);
		String expected = "{id:Spring, name:Spring Framework, description:Spring Framework Description}";
		JSONAssert.assertEquals(expected, responseEntity.getBody(), false);
	}
	
	//Testing create a new topic service
	@Test
	public void testAddTopic() {
		ResponseEntity responseEntity = restTemplate.postForEntity("http://localhost:3000/v1/api/topics", new Topic(), Topic.class);
		Topic client = (Topic)responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

	}
}

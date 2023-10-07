package com.example.clothshop.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import com.example.clothshop.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

    @Autowired
    private TestRestTemplate restTemplate;


    private String getRootUrl() {
        return "http://localhost:8080/api/";
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetAllUser() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/user",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetEmployeeById() {
        User user = restTemplate.getForObject(getRootUrl() + "/user/1", User.class);
        System.out.println(user.getFirstName());
        assertNotNull(user);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setLogin("admin_com");
        user.setFirstName("admin");
        user.setPassword("admin123");
        user.setBirthday("01.02.2005");
        user.setEmail("test@gmail.com");

        ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/user", user, User.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateUser() {
        int id = 2;
        User user = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
        user.setLogin("admin_com2");
        user.setFirstName("admin2");
        user.setPassword("admin12");
        user.setBirthday("01.02.2005");
        user.setEmail("test2@gmail.com");
        restTemplate.put(getRootUrl() + "/user/" + id, user);
        User updatedUser = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
        assertNotNull(updatedUser);
    }

    @Test
    public void testDeleteUser() {
        int id = 4;
        User user = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
        assertNotNull(user);
        restTemplate.delete(getRootUrl() + "/user/" + id);
        try {
            user = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}


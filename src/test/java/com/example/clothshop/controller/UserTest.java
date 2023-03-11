package com.example.clothshop.controller;


//import com.example.clothshop.model.User;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.*;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.client.HttpClientErrorException;
//
//import javax.faces.application.Application;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;

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


//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

    @Autowired
    private TestRestTemplate restTemplate;

//    @LocalServerPort
//    private int port;

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


//    @Test
//    public void contextLoads() {
//    }
//
//    @MockBean
//    UserService userService;
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//
//    HttpHeaders headers = new HttpHeaders();
//
//    public ResponseEntity<String> doRestCall(String url, MultiValueMap<String, String> queryParam,
//                                             Map<String, String> pathParam, String body, MultiValueMap<String, String> header, HttpMethod method) {
//        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
//        HttpEntity<String> entity = new HttpEntity<>(body, header);
//
//        ResponseEntity<String> response = testRestTemplate.exchange(builder.buildAndExpand(pathParam).toUri(),
//                method, entity, String.class);
//        return response;
//    }
//
//    @Test
//    public void getUserTest() {
//        String url = "http://localhost:" + port + "/user/{id}";
//        Map<String, String> pathVariable = new HashMap<>();
//        pathVariable.put("id", "1");
//
//        HttpEntity<String> entity = new HttpEntity<>(null, null);
//        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
//
//        ResponseEntity<String> response = testRestTemplate.exchange(builder.buildAndExpand(pathVariable).toUri(),
//                HttpMethod.GET, entity, String.class);
//        System.out.println(response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//
//    @Test
//    public void addUserTest() {
//        String url = "http://localhost:" + port + "/user";
//        String body = "{\n" +
//                " \"login\": \"John\", \n" +
//                " \"firstName\": \"Odisha\" \n" +
//                " \"password\": \"Odisha\" \n" +
//                " \"birthday\": \"02.01.2023\" \n" +
//                " \"email\": \"odisha@gmail.com\" \n" +
//                "}";
//
//        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//        List<String> al = new ArrayList<>();
//        al.add("application/json");
//        headers.put("Content-Type", al);
//        Map<String, String> pathParam = new HashMap<>();
//
//        ResponseEntity<String> response = doRestCall(url, null, pathParam, body,
//                headers, HttpMethod.POST);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }


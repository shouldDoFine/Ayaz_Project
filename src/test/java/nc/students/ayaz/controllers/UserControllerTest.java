package nc.students.ayaz.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import nc.students.ayaz.deserializer.UserDeserializer;
import nc.students.ayaz.model.User;
import nc.students.ayaz.model.Video;
import nc.students.ayaz.model.exceptions.NoSuchUserException;
import nc.students.ayaz.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @MockBean
    private UserRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    private ObjectMapper mapper;

    @Before
    public void setup() {
        this.mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(User.class, new UserDeserializer());
        mapper.registerModule(module);
    }

    @Test
    public void shouldAddUserToRepositoryWhenPostRequestSent() {
        ResponseEntity<String> response = restTemplate.postForEntity("/users/Ayaz", "", String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void shouldFetchUserWhenGetRequestSent() throws NoSuchUserException, IOException {
        User user = new User("Ayaz");
        user.addVideo(new Video("FunnyCats"));
        when(repository.getUserByNickname("Ayaz")).thenReturn(user);

        ResponseEntity<String> response = restTemplate.exchange(
                "/users/Ayaz",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class
        );

        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        User fetchedUser = mapper.readValue(response.getBody(), User.class);
        assertEquals(user, fetchedUser);
    }

    @Test
    public void shouldReturnNotFoundStatusCodeWhenTryingToGetNonexistentUser() throws Exception {
        when(repository.getUserByNickname("UfoResearcher")).thenThrow(new NoSuchUserException());

        ResponseEntity<String> response = restTemplate.getForEntity("/users/UfoResearcher", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
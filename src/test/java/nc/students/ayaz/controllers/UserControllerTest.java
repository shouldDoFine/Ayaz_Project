package nc.students.ayaz.controllers;

import nc.students.ayaz.model.User;
import nc.students.ayaz.model.Video;
import nc.students.ayaz.model.exceptions.NoSuchUserException;
import nc.students.ayaz.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @MockBean
    private UserRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldAddUserToRepositoryWhenPostRequestSent() {
        ResponseEntity<String> response = restTemplate.postForEntity("/users/Ayaz", "", String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void shouldReturnUserVideosWhenGetRequestSentWithUsersNickname() throws Exception {
        User user = new User("Ayaz");
        Video firstVideo = new Video("FunnyCats");
        Video secondVideo = new Video("SadDogs");
        user.addVideo(firstVideo);
        user.addVideo(secondVideo);
        when(repository.getUserByNickname("Ayaz")).thenReturn(user);


        ResponseEntity<List<Video>> response = restTemplate.exchange(
                "/users/Ayaz",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<Video>>() {
                }
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Video> fetchedVideos = response.getBody();
        assertTrue(fetchedVideos.contains(firstVideo));
        assertTrue(fetchedVideos.contains(secondVideo));
    }

    @Test
    public void shouldReturnNotFoundStatusCodeWhenTryingToGetNonexistentUser() throws Exception {
        when(repository.getUserByNickname("UFOresearcher")).thenThrow(new NoSuchUserException());

        ResponseEntity<String> response = restTemplate.getForEntity("/users/UFOresearcher", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
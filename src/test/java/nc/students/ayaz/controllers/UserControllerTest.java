package nc.students.ayaz.controllers;

import nc.students.ayaz.model.User;
import nc.students.ayaz.model.Video;
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

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
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

        ParameterizedTypeReference<Map<String, Video>> typeRef = new ParameterizedTypeReference<Map<String, Video>>() {};
        ResponseEntity<Map<String, Video>> response = restTemplate.exchange("/users/Ayaz", HttpMethod.GET, HttpEntity.EMPTY, typeRef);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Video> map = new HashMap<>();
        map.put(firstVideo.getName(), firstVideo);
        map.put(secondVideo.getName(), secondVideo);
        assertEquals(map, response.getBody());
    }
}
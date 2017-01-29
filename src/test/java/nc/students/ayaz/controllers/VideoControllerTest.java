package nc.students.ayaz.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import nc.students.ayaz.deserializer.VideoDeserializer;
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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VideoControllerTest {

    @MockBean
    private UserRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    private ObjectMapper mapper;

    @Before
    public void setup() {
        this.mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Video.class, new VideoDeserializer());
        mapper.registerModule(module);
    }

    @Test
    public void shouldAddVideoToUserWhenPostRequestSentWithVideoName() throws Exception {
        User user = new User("Ayaz");
        when(repository.getUserByNickname("Ayaz")).thenReturn(user);

        ResponseEntity<String> response = restTemplate.postForEntity("/users/Ayaz/videos/FunnyCats", "", String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(user.ownsVideo("FunnyCats"));
    }

    @Test
    public void shouldReturnUserVideosWhenGetRequestSentWithoutCertainVideoName() throws Exception {
        User user = new User("Ayaz");
        Video video = new Video("FunnyCats");
        user.addVideo(video);
        when(repository.getUserByNickname("Ayaz")).thenReturn(user);

        ResponseEntity<String> response = restTemplate.exchange(
                "/users/Ayaz/videos",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Video> fetchedVideos = mapper.readValue(
                response.getBody(),
                new TypeReference<List<Video>>() {
                }
        );
        assertEquals(1, fetchedVideos.size());
        assertTrue(fetchedVideos.contains(video));
    }

    @Test
    public void shouldReturnVideoWhenPostRequestSentFirstAndThenGetRequestSent() throws NoSuchUserException, IOException {
        User user = new User("Ayaz");
        when(repository.getUserByNickname("Ayaz")).thenReturn(user);
        restTemplate.postForEntity("/users/Ayaz/videos/FunnyCats", "", String.class);

        ResponseEntity<String> response = restTemplate.exchange(
                "/users/Ayaz/videos/FunnyCats",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Video fetchedVideo = mapper.readValue(response.getBody(), Video.class);
        assertEquals(new Video("FunnyCats"), fetchedVideo);
    }

    @Test
    public void shouldReturnNotFoundStatusCodeWhenTryingToGetNonexistentVideo() throws Exception {
        User user = new User("Ayaz");
        when(repository.getUserByNickname("Ayaz")).thenReturn(user);

        ResponseEntity<String> response = restTemplate.getForEntity("/users/Ayaz/videos/UfoProof", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void shouldReturnNotFoundStatusCodeWhenTryingToGetVideosFromNonexistentUser() throws Exception {
        when(repository.getUserByNickname("UfoResearcher")).thenThrow(new NoSuchUserException());

        ResponseEntity<String> response = restTemplate.getForEntity("/users/UfoResearcher/videos/UfoProof", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
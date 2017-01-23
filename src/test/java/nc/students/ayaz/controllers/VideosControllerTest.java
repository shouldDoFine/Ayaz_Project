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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VideosControllerTest {

    @MockBean
    private UserRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldAddVideoToUserWhenPostMethodSent() throws Exception {
        User user = new User("Ayaz");
        when(repository.getUserByNickname("Ayaz")).thenReturn(user);

        ResponseEntity<String> response = restTemplate.postForEntity("/users/Ayaz/videos/FunnyCats", "", String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(user.ownsVideo(new Video("FunnyCats")));
    }

    @Test
    public void shouldReturnVideoWhenGetRequestSentWithVideoName() throws Exception {
        User user = new User("Ayaz");
        Video video = new Video("FunnyCats");
        user.addVideo(video);
        when(repository.getUserByNickname("Ayaz")).thenReturn(user);

        ResponseEntity<Video> response = restTemplate.getForEntity("/users/Ayaz/videos/FunnyCats", Video.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Video fetchVideo = response.getBody();
        assertEquals(video, fetchVideo);
    }

    @Test
    public void shouldReturnNotFoundStatusCodeWhenTryingToGetNonexistentVideo() throws Exception {
        User user = new User("Ayaz");
        when(repository.getUserByNickname("Ayaz")).thenReturn(user);

        ResponseEntity<String> response = restTemplate.getForEntity("/users/Ayaz/videos/UFOproof", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
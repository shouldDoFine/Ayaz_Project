package nc.students.ayaz.controllers;

import nc.students.ayaz.model.User;
import nc.students.ayaz.model.Video;
import nc.students.ayaz.repositories.UserRepository;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VideosControllerTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldAddVideoToUserWhenPOSTmethodSent() {
        repository.addUser(new User("Ayaz"));

        ResponseEntity<String> response = restTemplate.postForEntity("/users/Ayaz/videos/FunnyCats", "", String.class);

        User user = repository.getUserByNickname("Ayaz");
        assertTrue(user.ownsVideo(new Video("FunnyCats")));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void shouldReturnVideoWhenSendGETrequestWithVideoName() throws Exception {
        User user = new User("Ayaz");
        repository.addUser(user);
        user.addVideo(new Video("FunnyCats"));

        ResponseEntity<String> response = restTemplate.getForEntity("/users/Ayaz/videos/FunnyCats", String.class);

        JSONObject jsonVideo = new JSONObject();
        jsonVideo.put("name", "FunnyCats");
        assertEquals(jsonVideo.toString(), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturnNotFoundStatusCodeWhenTryingToGetNonexistentVideo() {
        repository.addUser(new User("Ayaz"));

        ResponseEntity<String> response = restTemplate.getForEntity("/users/Ayaz/videos/UFOproof", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
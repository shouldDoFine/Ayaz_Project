package nc.students.ayaz.controllers;

import nc.students.ayaz.model.User;
import nc.students.ayaz.model.Video;
import nc.students.ayaz.repositories.UserRepository;
import org.json.JSONArray;
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
public class UserControllerTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldAddUserToRepositoryWhenPOSTrequestSent() {
        ResponseEntity<String> response = restTemplate.postForEntity("/users/Ayaz", "", String.class);

        assertTrue(repository.contains("Ayaz"));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void shouldReturnUserVideosWhenSendGETrequestWithUsersNickname() throws Exception {
        User user = new User("Ayaz");
        repository.addUser(user);
        user.addVideo(new Video("FunnyCats"));
        user.addVideo(new Video("SadDogs"));

        ResponseEntity<String> response = restTemplate.getForEntity("/users/Ayaz", String.class);

        JSONObject jsonFirstVideo = new JSONObject();
        jsonFirstVideo.put("name", "FunnyCats");
        JSONObject jsonSecondVideo = new JSONObject();
        jsonSecondVideo.put("name", "SadDogs");
        JSONArray jsonVideos = new JSONArray();
        jsonVideos.put(jsonFirstVideo);
        jsonVideos.put(jsonSecondVideo);
        assertEquals(jsonVideos.toString(), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
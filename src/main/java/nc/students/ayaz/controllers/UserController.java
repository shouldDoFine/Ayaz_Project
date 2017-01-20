package nc.students.ayaz.controllers;

import nc.students.ayaz.model.User;
import nc.students.ayaz.model.Video;
import nc.students.ayaz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(method = RequestMethod.POST, value = "/{nickname}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@PathVariable String nickname) {
        repository.addUser(new User(nickname));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{nickname}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getUser(@PathVariable String nickname) {
        User user = repository.getUserByNickname(nickname);
        return user.getVideos();
    }
}

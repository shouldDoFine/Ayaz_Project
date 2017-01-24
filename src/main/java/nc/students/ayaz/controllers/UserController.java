package nc.students.ayaz.controllers;

import nc.students.ayaz.model.User;
import nc.students.ayaz.model.Video;
import nc.students.ayaz.model.exceptions.NoSuchUserException;
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

    @PostMapping("/{nickname}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@PathVariable String nickname) {
        repository.addUser(new User(nickname));
    }

    @GetMapping("/{nickname}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getUsersVideos(@PathVariable String nickname) throws NoSuchUserException {
        User user = repository.getUserByNickname(nickname);
        return user.getVideos();
    }

    @ExceptionHandler(NoSuchUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoSuchUserException(Exception e) {

    }
}

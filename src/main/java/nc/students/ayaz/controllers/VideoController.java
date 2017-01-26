package nc.students.ayaz.controllers;


import nc.students.ayaz.model.User;
import nc.students.ayaz.model.Video;
import nc.students.ayaz.model.exceptions.NoSuchUserException;
import nc.students.ayaz.model.exceptions.NoSuchVideoException;
import nc.students.ayaz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping(value = "/users/{nickname}/videos")
public class VideoController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/{videoName}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createVideo(@PathVariable String nickname, @PathVariable String videoName) throws NoSuchUserException {
        User user = repository.getUserByNickname(nickname);
        user.addVideo(new Video(videoName));
    }

    @GetMapping("/{videoName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getUsersVideos(@PathVariable String nickname, @PathVariable String videoName) throws NoSuchUserException, NoSuchVideoException {
        User user = repository.getUserByNickname(nickname);

        return asList(user.getVideoByName(videoName));
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> getUsersVideos2(@PathVariable String nickname) throws NoSuchUserException, NoSuchVideoException {
        User user = repository.getUserByNickname(nickname);

        return user.getVideos();
    }

    @ExceptionHandler(NoSuchVideoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoSuchVideoException() {
    }

    @ExceptionHandler(NoSuchUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoSuchUserException() {
    }
}

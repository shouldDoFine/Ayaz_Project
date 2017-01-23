package nc.students.ayaz.controllers;


import nc.students.ayaz.model.User;
import nc.students.ayaz.model.Video;
import nc.students.ayaz.model.exceptions.NoSuchRecourceException;
import nc.students.ayaz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users/{nickname}/videos")
public class VideosController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/{videoName}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createVideo(@PathVariable String nickname, @PathVariable String videoName) throws NoSuchRecourceException {
        User user = repository.getUserByNickname(nickname);
        user.addVideo(new Video(videoName));
    }

    @GetMapping("/{videoName}")
    @ResponseStatus(HttpStatus.OK)
    public Video getVideo(@PathVariable String nickname, @PathVariable String videoName) throws NoSuchRecourceException {
        User user = repository.getUserByNickname(nickname);
        return user.getVideoByName(videoName);
    }

    @ExceptionHandler(NoSuchRecourceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoSuchVideoException(Exception e) {

    }
}

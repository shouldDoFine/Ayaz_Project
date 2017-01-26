package nc.students.ayaz.controllers;

import nc.students.ayaz.model.User;
import nc.students.ayaz.model.exceptions.NoSuchUserException;
import nc.students.ayaz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public User getUser(@PathVariable String nickname) throws NoSuchUserException {
        return repository.getUserByNickname(nickname);
    }

    @ExceptionHandler(NoSuchUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoSuchUserException() {
    }
}

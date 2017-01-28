package nc.students.ayaz.repositories;

import nc.students.ayaz.model.User;
import nc.students.ayaz.model.exceptions.NoSuchUserException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private Map<String, User> users = new HashMap<>();

    public User getUserByNickname(String nickname) throws NoSuchUserException {
        if (users.containsKey(nickname)) {
            return users.get(nickname);
        } else {
            throw new NoSuchUserException();
        }
    }

    public void addUser(User user) {
        users.put(user.getNickname(), user);
    }
}

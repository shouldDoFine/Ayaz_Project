package nc.students.ayaz.repositories;

import nc.students.ayaz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private Map<String, User> users = new HashMap<>();

    public User getUserByNickname(String nickname) {
        return users.get(nickname);
    }

    public void addUser(User user) {
        users.put(user.getNickname(), user);
    }

    public boolean contains(String nickname) {
        return users.containsKey(nickname);
    }
}

package nc.students.ayaz.repositories;

import nc.students.ayaz.model.User;
import nc.students.ayaz.model.exceptions.NoSuchRecourceException;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class UserRepository {

    private Set<User> users = new HashSet<>();


    public User getUserByNickname(String nickname) throws NoSuchRecourceException {
        for (User user : users) {
            if (nickname.equals(user.getNickname())) {
                return user;
            }
        }
        throw new NoSuchRecourceException();
    }

    public void addUser(User user) {
        users.add(user);
    }
}

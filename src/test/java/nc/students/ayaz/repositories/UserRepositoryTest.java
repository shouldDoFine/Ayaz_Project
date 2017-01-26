package nc.students.ayaz.repositories;

import nc.students.ayaz.model.User;
import nc.students.ayaz.model.exceptions.NoSuchUserException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserRepositoryTest {

    @Test
    public void shouldGetUserByNicknameWhenAddedEarlier() throws NoSuchUserException {
        UserRepository repository = new UserRepository();
        User user = new User("Ayaz");
        repository.addUser(user);

        User fetchedUser = repository.getUserByNickname("Ayaz");

        assertEquals(user, fetchedUser);
    }

    @Test(expected = NoSuchUserException.class)
    public void shouldThrowWhenRepositoryDoesNotContainUser() throws NoSuchUserException {
        UserRepository repository = new UserRepository();

        repository.getUserByNickname("UfoResearcher");
    }
}
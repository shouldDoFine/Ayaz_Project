package nc.students.ayaz.repositories;

import nc.students.ayaz.model.User;
import nc.students.ayaz.model.exceptions.NoSuchUserException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class UserRepositoryTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldGetUserByNicknameWhenAddedEarlier() throws NoSuchUserException {
        UserRepository repository = new UserRepository();
        User user = new User("Ayaz");
        repository.addUser(user);

        User fetchedUser = repository.getUserByNickname("Ayaz");

        assertEquals(user, fetchedUser);
    }

    @Test
    public void shouldThrowNoSuchUserExceptionWhenRepositoryDoesntHaveUser() throws NoSuchUserException {
        UserRepository repository = new UserRepository();
        exception.expect(NoSuchUserException.class);

        repository.getUserByNickname("UFOresearcher");
    }
}
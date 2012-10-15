package ro.sdl.repository;

import ro.sdl.application.data.AppDataLoader;
import ro.sdl.domain.*;

import java.util.Collection;

public class UserRepositoryMemoryImpl implements UserRepository {


    public User load(long userId) throws RepositoryException {
        for (User currentUser : AppDataLoader.users) {
            if (currentUser.getId() == userId) {
                return currentUser;
            }

        }
        return null;
    }

    public Collection<User> getUsers() throws RepositoryException {
        return AppDataLoader.users;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void add(User user) throws RepositoryException {
        AppDataLoader.users.add(user);
    }

    public void update(User user) throws RepositoryException {
        for (User currentUser : AppDataLoader.users) {
            if (currentUser.getId() == user.getId()) {
                currentUser.setName(user.getName());
                currentUser.setRole(user.getRole());
                currentUser.setState(user.getState());
                currentUser.setProject(user.getProject());

            }

        }
    }

    public void delete(long userId) throws RepositoryException {
        for (User currentUser : AppDataLoader.users) {
            if (currentUser.getId() == userId) {
                AppDataLoader.users.remove(currentUser);

            }

        }
    }
}

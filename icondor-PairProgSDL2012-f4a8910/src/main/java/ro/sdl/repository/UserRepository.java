package ro.sdl.repository;

import ro.sdl.domain.*;

import java.util.Collection;

public interface UserRepository {

    User load(long userId) throws RepositoryException;

	Collection<User> getUsers() throws RepositoryException;

	void add(User user) throws RepositoryException;

	void update(User user) throws RepositoryException;

	void delete(long userId) throws RepositoryException;
}

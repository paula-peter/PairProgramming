package ro.sdl.repository;

import ro.sdl.domain.*;

import java.util.Collection;
import java.util.List;

public interface ProjectRepository {

    Project load(Integer projectId) throws RepositoryException;

    Collection<Project> getProjects() throws RepositoryException;

    void add(Project project) throws RepositoryException;

    void update(Project project) throws RepositoryException;

    void delete(Integer projectId) throws RepositoryException;

    public List<User> getProjectUsers(Project project);
}

package ro.sdl.repository;

import ro.sdl.application.data.AppDataLoader;
import ro.sdl.domain.*;
import java.util.Collection;
import java.util.List;

public class ProjectRepositoryMemoryImpl implements ProjectRepository {


    public Project load(Integer projectId) throws RepositoryException {
        for (Project currentProject : AppDataLoader.projects) {
            if (currentProject.getId() == projectId) {
                return currentProject;
            }
        }
        return null;
    }

    public Collection<Project> getProjects() throws RepositoryException {
        return AppDataLoader.projects;
    }

    public void add(Project project) throws RepositoryException {
        AppDataLoader.projects.add(project);
    }

    public void update(Project project) throws RepositoryException {
        for (Project currentProject : AppDataLoader.projects) {
            if (currentProject.getId() == project.getId()) {
                currentProject.setUsers(project.getUsers());
                currentProject.setDescription(project.getDescription());
            }
        }
    }

    public void delete(Integer projectId) throws RepositoryException {
        for (Project currentProject : AppDataLoader.projects) {
            if (currentProject.getId() == projectId) {
                AppDataLoader.projects.remove(currentProject);

            }
        }
    }

    public List<User> getProjectUsers(Project project) {
        return project.getUsers();
    }
}

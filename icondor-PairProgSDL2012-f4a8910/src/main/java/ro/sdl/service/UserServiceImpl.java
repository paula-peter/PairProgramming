package ro.sdl.service;

import ro.sdl.domain.Project;
import ro.sdl.domain.User;
import ro.sdl.repository.ProjectRepository;
import ro.sdl.repository.ProjectRepositoryMemoryImpl;
import ro.sdl.repository.UserRepository;
import ro.sdl.repository.UserRepositoryMemoryImpl;

public class UserServiceImpl implements UserService {
    ProjectRepository projectRepository = new ProjectRepositoryMemoryImpl();
    UserRepository userRepository = new UserRepositoryMemoryImpl();


    public Boolean associateUserToProject(Project project, User user) {
        if (!projectRepository.getProjectUsers(project).contains(user)) {
            project.getUsers().add(user);
            user.setProject(project);
            return true;
        } else
            return false;
    }
}

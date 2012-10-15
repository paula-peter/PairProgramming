package ro.sdl.service;

import ro.sdl.domain.*;

public interface UserService {

    public Boolean associateUserToProject(Project project, User user);

}

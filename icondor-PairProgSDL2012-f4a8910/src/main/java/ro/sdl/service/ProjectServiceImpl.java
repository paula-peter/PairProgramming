package ro.sdl.service;


import ro.sdl.application.data.AppDataLoader;
import ro.sdl.domain.Project;
import ro.sdl.domain.Role;
import ro.sdl.domain.State;
import ro.sdl.domain.User;
import ro.sdl.dto.ProjectDetailedDistributionDTO;
import ro.sdl.dto.ProjectDistributionDTO;
import ro.sdl.dto.ProjectStateDistributionDTO;
import ro.sdl.repository.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    ProjectRepository projectRepository = new ProjectRepositoryMemoryImpl();
    UserRepository userRepository = new UserRepositoryMemoryImpl();

    /**
     * @param project: the project
     * @param user:    the user
     * @return true if operation works ok
     *         false if the user could not be found for the project
     */
    public Boolean removeUserFromProject(Project project, User user) {
        if (projectRepository.getProjectUsers(project).contains(user)) {
            project.getUsers().remove(user);
            return true;
        }
        return false;
    }

    /**
     * 4.	List project composition for a project or for all the projects
     * GW
     * Ana qa jr
     * Maria dev sr
     *
     * @param project: the project
     * @return list of users as described above
     */
    public List<User> getProjectComposition(Project project) {
        return projectRepository.getProjectUsers(project);
    }

    /**
     * 5.	List dev/qa distribution for a given project
     * <p/>
     * GW
     * Dev – 70%
     * QA – 30 %
     *
     * @param project: the project
     * @return : a project distribution object
     */
    public ProjectDistributionDTO getProjectDistribution(Project project) {
        ProjectDistributionDTO projectDistributionDTO = new ProjectDistributionDTO();
        int devCount = 0;
        int qaCount = 0;
        for (User currentUser : projectRepository.getProjectUsers(project)) {
            if (currentUser.getRole().equals(Role.DEV)) {
                devCount++;
            } else
                qaCount++;
        }

        projectDistributionDTO.setDevPercentage((devCount * 100 / projectRepository.getProjectUsers(project).size()));
        projectDistributionDTO.setQaPercentage((qaCount * 100 / projectRepository.getProjectUsers(project).size()));
        return projectDistributionDTO;
    }

    /**
     * 6.	List jr/mid/dev for a project and for a role
     * GW
     * Dev
     * Sr – 40%
     * Mid – 10%
     * Jr – 50%
     * QA
     * Sr – 80%
     * Mid – 20%
     * Jr – 0%
     *
     * @param project : the  project
     * @return : a project detailed distribution object for project
     */
    public ProjectDetailedDistributionDTO getProjectDetailedDistribution(Project project) {
        ProjectDetailedDistributionDTO projectDetailedDistributionDTO = new ProjectDetailedDistributionDTO();
        projectDetailedDistributionDTO = getDetailedDistribution(projectRepository.getProjectUsers(project));
        return projectDetailedDistributionDTO;
    }

    /**
     * Dev
     * Sr – 30%
     * Mid – 10%
     * Jr – 60%
     * QA
     * Sr – 10%
     * Mid – 80%
     * Jr – 10%
     *
     * @return a  project detailed distribution for all existing projects
     * @throws RepositoryException
     */

    public ProjectDetailedDistributionDTO getStructureDistribution() {
        ArrayList<User> users = new ArrayList<User>();

        for (Project project : AppDataLoader.projects) {
            for (User user : project.getUsers()) {
                users.add(user);
            }
        }
        ProjectDetailedDistributionDTO projectDetailedDistributionDTO = getDetailedDistribution(users);
        return projectDetailedDistributionDTO;
    }
    public ProjectDetailedDistributionDTO getProjectDetailedDistribution() throws RepositoryException {
        ProjectDetailedDistributionDTO projectDetailedDistributionDTO = getDetailedDistribution(userRepository.getUsers());
        return projectDetailedDistributionDTO;
    }


    public ProjectDetailedDistributionDTO getDetailedDistribution(Collection<User> users) {
        ProjectDetailedDistributionDTO projectDetailedDistributionDTO = new ProjectDetailedDistributionDTO();
        int devSrCount = 0;
        int devMidCount = 0;
        int devJrCount = 0;
        int qaSrCount = 0;
        int qaMidCount = 0;
        int qaJrCount = 0;
        for (User currentUser : users) {
            if (currentUser.getRole().equals(Role.DEV)) {
                if (currentUser.getState().equals(State.SENIOR)) {
                    devSrCount++;
                }
                if (currentUser.getState().equals(State.MID)) {
                    devMidCount++;
                }
                if (currentUser.getState().equals(State.JUNIOR)) {
                    devJrCount++;
                }
            } else {
                if (currentUser.getState().equals(State.SENIOR)) {
                    qaSrCount++;
                }
                if (currentUser.getState().equals(State.MID)) {
                    qaMidCount++;
                }
                if (currentUser.getState().equals(State.JUNIOR)) {
                    qaJrCount++;
                }
            }
        }
        ProjectStateDistributionDTO projectDevStateDistributionDTO = new ProjectStateDistributionDTO();
        ProjectStateDistributionDTO projectQAStateDistributionDTO = new ProjectStateDistributionDTO();
        projectDevStateDistributionDTO.setSeniorPercentage(devSrCount * 100 / (devSrCount + devMidCount + devJrCount));
        projectDevStateDistributionDTO.setMidPercentage(devMidCount * 100 / (devSrCount + devMidCount + devJrCount));
        projectDevStateDistributionDTO.setJuniorPercentage(devJrCount * 100 / (devSrCount + devMidCount + devJrCount));
        projectDetailedDistributionDTO.setProjectDevStateDistributionDTO(projectDevStateDistributionDTO);
        projectQAStateDistributionDTO.setSeniorPercentage(qaSrCount * 100 / (qaSrCount + qaMidCount + qaJrCount));
        projectQAStateDistributionDTO.setMidPercentage(qaMidCount * 100 / (qaSrCount + qaMidCount + qaJrCount));
        projectQAStateDistributionDTO.setJuniorPercentage(qaJrCount * 100 / (qaSrCount + qaMidCount + qaJrCount));
        projectDetailedDistributionDTO.setProjectQaStateDistributionDTO(projectQAStateDistributionDTO);

        return projectDetailedDistributionDTO;
    }

    /**
     * @param value a constant value for which the ratio DEV/QA will be computed
     * @return the list of projects for which dev / qa ratio is greater than value
     * @throws RepositoryException
     */
    public List<Project> listProjectWithRole(int value) throws RepositoryException {
        ProjectDistributionDTO projectDistributionDTO = new ProjectDistributionDTO();
        int devCount = 0;
        int qaCount = 0;
        List<Project> projects = new ArrayList<Project>();
        for (Project currentProject : projectRepository.getProjects()) {
            for (User currentUser : projectRepository.getProjectUsers(currentProject)) {
                if (currentUser.getRole().equals(Role.DEV)) {
                    devCount++;
                } else
                    qaCount++;
            }
            if (devCount / qaCount >= value) {
                projects.add(currentProject);
            }

        }
        return projects;
    }

    public List<Project> listProjectWithState
            (
                    int value) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}

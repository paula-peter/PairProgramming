package ro.sdl.dto;

public class ProjectDetailedDistributionDTO {

    ProjectStateDistributionDTO projectDevStateDistributionDTO;
    ProjectStateDistributionDTO projectQaStateDistributionDTO;

    public ProjectStateDistributionDTO getProjectDevStateDistributionDTO() {
        return projectDevStateDistributionDTO;
    }

    public void setProjectDevStateDistributionDTO(ProjectStateDistributionDTO projectDevStateDistributionDTO) {
        this.projectDevStateDistributionDTO = projectDevStateDistributionDTO;
    }

    public ProjectStateDistributionDTO getProjectQaStateDistributionDTO() {
        return projectQaStateDistributionDTO;
    }

    public void setProjectQaStateDistributionDTO(ProjectStateDistributionDTO projectQaStateDistributionDTO) {
        this.projectQaStateDistributionDTO = projectQaStateDistributionDTO;
    }
}

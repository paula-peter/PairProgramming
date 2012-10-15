package ro.sdl.dto;


public class ProjectStateDistributionDTO {

    int seniorPercentage;
    int midPercentage;
    int juniorPercentage;

    public int getSeniorPercentage() {
        return seniorPercentage;
    }

    public void setSeniorPercentage(int seniorPercentage) {
        this.seniorPercentage = seniorPercentage;
    }

    public int getMidPercentage() {
        return midPercentage;
    }

    public void setMidPercentage(int midPercentage) {
        this.midPercentage = midPercentage;
    }

    public int getJuniorPercentage() {
        return juniorPercentage;
    }

    public void setJuniorPercentage(int juniorPercentage) {
        this.juniorPercentage = juniorPercentage;
    }
}

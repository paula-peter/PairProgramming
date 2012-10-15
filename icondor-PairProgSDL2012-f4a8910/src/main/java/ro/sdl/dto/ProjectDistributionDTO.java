package ro.sdl.dto;


public class ProjectDistributionDTO {

       private int devPercentage;
       private int qaPercentage;

    public int getDevPercentage() {
        return devPercentage;
    }

    public void setDevPercentage(int devPercentage) {
        this.devPercentage = devPercentage;
    }

    public int getQaPercentage() {
        return qaPercentage;
    }

    public void setQaPercentage(int qaPercentage) {
        this.qaPercentage = qaPercentage;
    }
}

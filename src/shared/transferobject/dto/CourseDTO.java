package shared.transferobject.dto;

import java.io.Serializable;

public class CourseDTO implements Serializable {
    private String shortName;
    private String name;
    private String programShortName;

    public CourseDTO(String shortName, String name, String programShortName) {
        this.shortName = shortName;
        this.name = name;
        this.programShortName = programShortName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgramShortName() {
        return programShortName;
    }

    public void setProgramShortName(String programShortName) {
        this.programShortName = programShortName;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "shortName='" + shortName + '\'' +
                ", name='" + name + '\'' +
                ", programShortName='" + programShortName + '\'' +
                '}';
    }
}

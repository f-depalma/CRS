package shared.transferobject.dto;

public class FavoriteCourseDTO {
    private String courseShortName;
    private int profileId;

    public FavoriteCourseDTO(String courseShortName, int profileId) {
        this.courseShortName = courseShortName;
        this.profileId = profileId;
    }

    public String getCourseShortName() {
        return courseShortName;
    }

    public void setCourseShortName(String courseShortName) {
        this.courseShortName = courseShortName;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }
}

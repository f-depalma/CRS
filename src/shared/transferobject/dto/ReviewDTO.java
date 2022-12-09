package shared.transferobject.dto;

import java.io.Serializable;

public class ReviewDTO implements Serializable {
    private String review;
    private int rate;
    private String date;
    private String courseName;
    private String profileName;
    private int profileId;

    public ReviewDTO(String review, int rate, String date, String courseName, int profileId, String profileName) {
        this.review = review;
        this.rate = rate;
        this.date = date;
        this.courseName = courseName;
        this.profileId = profileId;
        this.profileName = profileName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}

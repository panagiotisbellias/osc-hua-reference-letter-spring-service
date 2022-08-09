package gr.hua.dit.ds.reference.letter.service.payload;

public class ProfileDto {

    private String username;
    private String fullName;
    private String email;

    private String school;
    private String uniId;
    private String urlGradingFile;

    private String description;
    private String type;

    public ProfileDto() {
        this.school = this.uniId = this.urlGradingFile = "";
    }

    public ProfileDto(String username, String fullName, String email,
                      String school, String uniId, String urlGradingFile) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.school = school;
        this.uniId = uniId;
        this.urlGradingFile = urlGradingFile;
        this.description = "";
        this.type = "student";
    }

    public ProfileDto(String username, String fullName, String email, String description) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.school = this.uniId = this.urlGradingFile = "";
        this.description = description;
        this.type = "teacher";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getUniId() {
        return uniId;
    }

    public void setUniId(String uniId) {
        this.uniId = uniId;
    }

    public String getUrlGradingFile() {
        return urlGradingFile;
    }

    public void setUrlGradingFile(String urlGradingFile) {
        this.urlGradingFile = urlGradingFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ProfileDto{" +
                "username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", school='" + school + '\'' +
                ", uniId='" + uniId + '\'' +
                ", urlGradingFile='" + urlGradingFile + '\'' +
                ", description=" + description +
                ", type='" + type +
                '}';
    }

}

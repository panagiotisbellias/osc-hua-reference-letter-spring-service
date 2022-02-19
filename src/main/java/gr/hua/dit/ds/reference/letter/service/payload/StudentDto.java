package gr.hua.dit.ds.reference.letter.service.payload;

// REST API
public class StudentDto {

    private String username;
    private String password;
    private String fullName;
    private String email;
    private String school;
    private String uniId;
    private String urlGradingFile;

    public StudentDto(String username, String password, String fullName, String email, String school,
                      String uniId, String urlGradingFile) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.school = school;
        this.uniId = uniId;
        this.urlGradingFile = urlGradingFile;
    }

    public StudentDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "SignUpStudentDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", school='" + school + '\'' +
                ", uniId='" + uniId + '\'' +
                ", urlGradingFile='" + urlGradingFile + '\'' +
                '}';
    }

}

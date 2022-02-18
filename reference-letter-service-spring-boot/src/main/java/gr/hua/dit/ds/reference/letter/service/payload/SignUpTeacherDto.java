package gr.hua.dit.ds.reference.letter.service.payload;

import java.util.List;

// REST API
public class SignUpTeacherDto {

    private String username;
    private String password;
    private String fullName;
    private String email;
    private List<CourseDto> courses;
    private List<CertificateDto> certificates;

    public SignUpTeacherDto(String username, String password, String fullName, String email,
                            List<CourseDto> courses, List<CertificateDto> certificates) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.courses = courses;
        this.certificates = certificates;
    }

    public SignUpTeacherDto() {
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

    public List<CourseDto> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDto> courses) {
        this.courses = courses;
    }

    public List<CertificateDto> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<CertificateDto> certificates) {
        this.certificates = certificates;
    }

    @Override
    public String toString() {
        return "SignUpTeacherDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", courses=" + courses +
                ", certificates=" + certificates +
                '}';
    }
}

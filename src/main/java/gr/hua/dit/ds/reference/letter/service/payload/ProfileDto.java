package gr.hua.dit.ds.reference.letter.service.payload;

import java.util.*;

public class ProfileDto {

    private String username;
    private String fullName;
    private String email;

    private String school;
    private String uniId;
    private String urlGradingFile;

    private List<CourseDto> courses;
    private List<CertificateDto> certificates;

    private String type;

    public ProfileDto() {
        this.school = this.uniId = this.urlGradingFile = "";
        this.courses = null;
        this.certificates = null;
    }

    public ProfileDto(String username, String fullName, String email,
                      String school, String uniId, String urlGradingFile) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.school = school;
        this.uniId = uniId;
        this.urlGradingFile = urlGradingFile;
        this.courses = null;
        this.certificates = null;
        this.type = "student";
    }

    public ProfileDto(String username, String fullName, String email,
                      List<CourseDto> courses, List<CertificateDto> certificates) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.school = this.uniId = this.urlGradingFile = "";
        this.courses = courses;
        this.certificates = certificates;
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
                ", courses=" + courses +
                ", certificates=" + certificates +
                ", type='" + type +
                '}';
    }

    public void addCourse(CourseDto acourse){
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(acourse);
    }

    public void addCertificate(CertificateDto acertificate){
        if (certificates == null) {
            certificates = new ArrayList<>();
        }
        certificates.add(acertificate);
    }

}

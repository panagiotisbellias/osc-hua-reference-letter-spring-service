package project.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "school")
    private String school;

    @Column(name = "uni_id")
    private String uniId;

    @Column(name = "url_grading_file")
    private String urlGradingFile;

    public Student() {
    }

    public Student(int id, String fullName, String email, String school, String uniId, String urlGradingFile) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.school = school;
        this.uniId = uniId;
        this.urlGradingFile = urlGradingFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSchool() {
        return school;
    }

    public String getUniId() {
        return uniId;
    }

    public String getUrlGradingFile() {
        return urlGradingFile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setUniId(String uniId) {
        this.uniId = uniId;
    }

    public void setUrlGradingFile(String urlGradingFile) {
        this.urlGradingFile = urlGradingFile;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", school='" + school + '\'' +
                ", uniId='" + uniId + '\'' +
                ", urlGradingFile='" + urlGradingFile + '\'' +
                '}';
    }
}


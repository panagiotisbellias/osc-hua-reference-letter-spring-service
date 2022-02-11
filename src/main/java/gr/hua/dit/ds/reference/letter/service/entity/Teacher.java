package gr.hua.dit.ds.reference.letter.service.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="teacher_id")
    private List<Course> courses;

    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="teacher_id")
    private List<Certificate> certificates;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="users_username")
    private User user;

    public Teacher() {
    }

    public Teacher(int id, String fullName, String email, User user) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.user = user;
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

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificate) {
        this.certificates = certificate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email +
                '}';
    }

    public void addCourse(Course acourse){
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(acourse);
    }

    public void addCertificate(Certificate acertificate){
        if (certificates == null) {
            certificates = new ArrayList<>();
        }
        certificates.add(acertificate);
    }

}
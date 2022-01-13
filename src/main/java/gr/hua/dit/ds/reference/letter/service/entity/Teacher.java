package gr.hua.dit.ds.reference.letter.service.entity;

import gr.hua.dit.ds.reference.letter.service.entity.hibernate.DateUtils;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "courses_id")
    private int coursesId;

    @Column(name = "certificates_id")
    private int certificatesId;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    public Teacher() {
    }

    public Teacher(int id, String fullName, String email, int coursesId, int certificatesId,
                   Date dateOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.coursesId = coursesId;
        this.certificatesId = certificatesId;
        this.dateOfBirth = dateOfBirth;
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

    public int getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(int coursesId) {
        this.coursesId = coursesId;
    }

    public int getCertificatesId() {
        return certificatesId;
    }

    public void setCertificatesId(int certificatesId) {
        this.certificatesId = certificatesId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", coursesId=" + coursesId +
                ", certificatesId=" + certificatesId +
                ", dateOfBirth=" + DateUtils.formatDate(dateOfBirth) +
                '}';
    }
}
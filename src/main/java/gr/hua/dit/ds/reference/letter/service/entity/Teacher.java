package gr.hua.dit.ds.reference.letter.service.entity;

import gr.hua.dit.ds.reference.letter.service.entity.hibernate.DateUtils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    /* Must be corrected to One To Many */
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="courses_id")
    private Course courses;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="certificates_id")
    private Certificate certificates;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    public Teacher() {
    }

    public Teacher(int id, String fullName, String email, Date dateOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
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

    public Course getCourses() {
        return courses;
    }

    public void setCourses(Course courses) {
        this.courses = courses;
    }

    public Certificate getCertificates() {
        return certificates;
    }

    public void setCertificate(Certificate certificate) {
        this.certificates = certificate;
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
                ", courses=" + courses + '\'' +
                ", certificates=" + certificates + '\'' +
                ", dateOfBirth=" + DateUtils.formatDate(dateOfBirth) +
                '}';
    }
}
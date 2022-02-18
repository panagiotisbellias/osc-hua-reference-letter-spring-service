package gr.hua.dit.ds.reference.letter.service.entity;

import javax.persistence.*;

@Entity
@Table(name = "reference_letter_requests")
public class ReferenceLetterRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_teacher")
    private Teacher teacher;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_student")
    private Student student;

    @Column(name = "carrier_name")
    private String carrierName;

    @Column(name = "carrier_email")
    private String carrierEmail;

    public ReferenceLetterRequest() {
    }

    public ReferenceLetterRequest(int id, String carrierName, String carrierEmail) {
        this.id = id;
        this.carrierName = carrierName;
        this.carrierEmail = carrierEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getCarrierEmail() {
        return carrierEmail;
    }

    public void setCarrierEmail(String carrierEmail) {
        this.carrierEmail = carrierEmail;
    }

    @Override
    public String toString() {
        return "ReferenceLetterRequest{" +
                "id=" + id +
                ", teacher=" + teacher + '\'' +
                ", student=" + student + '\'' +
                ", carrierName='" + carrierName + '\'' +
                ", carrierEmail='" + carrierEmail +
                '}';
    }
}

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

    @Column(name = "is_approved")
    private boolean isApproved;

    @Column(name = "is_declined")
    private boolean isDeclined;

    @Column(name = "is_pending")
    private boolean isPending;

    public ReferenceLetterRequest() {
    }

    public ReferenceLetterRequest(int id, String carrierName, String carrierEmail, boolean isApproved,
                                  boolean isDeclined, boolean isPending) {
        this.id = id;
        this.carrierName = carrierName;
        this.carrierEmail = carrierEmail;
        this.isApproved = isApproved;
        this.isDeclined = isDeclined;
        this.isPending = isPending;
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

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public boolean isDeclined() {
        return isDeclined;
    }

    public void setDeclined(boolean declined) {
        isDeclined = declined;
    }

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    @Override
    public String toString() {
        return "ReferenceLetterRequest{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", student=" + student +
                ", carrierName='" + carrierName + '\'' +
                ", carrierEmail='" + carrierEmail + '\'' +
                ", isApproved=" + isApproved +
                ", isDeclined=" + isDeclined +
                ", isPending=" + isPending +
                '}';
    }
}

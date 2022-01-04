package gr.hua.dit.ds.reference.letter.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reference_letter_requests")
public class ReferenceLetterRequest {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "id_teacher")
    private int idTeacher;

    @Column(name = "id_student")
    private int idStudent;

    @Column(name = "carrier_name")
    private String carrierName;

    @Column(name = "carrier_email")
    private String carrierEmail;

    public ReferenceLetterRequest() {
    }

    public ReferenceLetterRequest(int id, int idTeacher, int idStudent, String carrierName, String carrierEmail) {
        this.id = id;
        this.idTeacher = idTeacher;
        this.idStudent = idStudent;
        this.carrierName = carrierName;
        this.carrierEmail = carrierEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
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
                ", idTeacher=" + idTeacher +
                ", idStudent=" + idStudent +
                ", carrierName='" + carrierName + '\'' +
                ", carrierEmail='" + carrierEmail + '\'' +
                '}';
    }
}

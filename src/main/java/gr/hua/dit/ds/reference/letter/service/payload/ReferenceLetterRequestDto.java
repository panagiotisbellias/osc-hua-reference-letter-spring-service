package gr.hua.dit.ds.reference.letter.service.payload;

public class ReferenceLetterRequestDto {

    private TeacherDto teacher;
    private StudentDto student;
    private String carrierName;
    private String carrierEmail;
    private String status;

    public ReferenceLetterRequestDto(){
        this.status = "pending";
    }

    public ReferenceLetterRequestDto(TeacherDto teacher, String carrierName, String carrierEmail, String status) {
        this.teacher = teacher;
        this.carrierName = carrierName;
        this.carrierEmail = carrierEmail;
        this.status = status;
    }

    public TeacherDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDto teacher) {
        this.teacher = teacher;
    }

    public StudentDto getStudent() {return student;}

    public void setStudent(StudentDto student) {this.student = student;}

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReferenceLetterRequestDto{" +
                "teacher=" + teacher +
                ", carrierName='" + carrierName + '\'' +
                ", carrierEmail='" + carrierEmail + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

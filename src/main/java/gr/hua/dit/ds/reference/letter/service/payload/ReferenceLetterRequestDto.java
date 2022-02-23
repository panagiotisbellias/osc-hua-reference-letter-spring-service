package gr.hua.dit.ds.reference.letter.service.payload;

public class ReferenceLetterRequestDto {

    private int id;
    private TeacherDto teacher;
    private StudentDto student;
    private String carrierName;
    private String carrierEmail;
    private String text;
    private String status;

    public ReferenceLetterRequestDto(){
        this.status = "pending";
    }

    public ReferenceLetterRequestDto(int id, TeacherDto teacher, String carrierName, String carrierEmail,
                                     String text, String status) {
        this.id = id;
        this.teacher = teacher;
        this.carrierName = carrierName;
        this.carrierEmail = carrierEmail;
        this.text = text;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
                "id=" + id +
                ", teacher=" + teacher +
                ", student=" + student +
                ", carrierName='" + carrierName + '\'' +
                ", carrierEmail='" + carrierEmail + '\'' +
                ", text='" + text + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

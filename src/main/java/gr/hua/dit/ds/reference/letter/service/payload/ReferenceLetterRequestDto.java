package gr.hua.dit.ds.reference.letter.service.payload;

public class ReferenceLetterRequestDto {

    private TeacherDto teacher;
    private String carrierName;
    private String carrierEmail;

    public ReferenceLetterRequestDto(){

    }

    public ReferenceLetterRequestDto(TeacherDto teacher, String carrierName, String carrierEmail) {
        this.teacher = teacher;
        this.carrierName = carrierName;
        this.carrierEmail = carrierEmail;
    }

    public TeacherDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDto teacher) {
        this.teacher = teacher;
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
        return "ReferenceLetterRequestDto{" +
                "teacher='" + teacher + '\'' +
                ", carrierName='" + carrierName + '\'' +
                ", carrierEmail='" + carrierEmail + '\'' +
                '}';
    }
}

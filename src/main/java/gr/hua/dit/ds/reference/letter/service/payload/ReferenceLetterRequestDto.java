package gr.hua.dit.ds.reference.letter.service.payload;

public class ReferenceLetterRequestDto {

    private int teacherId;
    private String carrierName;
    private String carrierEmail;

    public ReferenceLetterRequestDto(){

    }

    public ReferenceLetterRequestDto(int teacherId, String carrierName, String carrierEmail) {
        this.teacherId = teacherId;
        this.carrierName = carrierName;
        this.carrierEmail = carrierEmail;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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
                "teacherId='" + teacherId + '\'' +
                ", carrierName='" + carrierName + '\'' +
                ", carrierEmail='" + carrierEmail + '\'' +
                '}';
    }
}

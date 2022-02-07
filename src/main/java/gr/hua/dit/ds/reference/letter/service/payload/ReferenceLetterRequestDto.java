package gr.hua.dit.ds.reference.letter.service.payload;

public class ReferenceLetterRequestDto {

    private String carrierName;
    private String carrierEmail;

    public ReferenceLetterRequestDto(){

    }

    public ReferenceLetterRequestDto(String carrierName, String carrierEmail) {
        this.carrierName = carrierName;
        this.carrierEmail = carrierEmail;
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
                "carrierName='" + carrierName + '\'' +
                ", carrierEmail='" + carrierEmail + '\'' +
                '}';
    }
}

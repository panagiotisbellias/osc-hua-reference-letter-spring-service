package gr.hua.dit.ds.reference.letter.service.payload;

public class CertificateDto {

    private String title;
    private String university;

    public CertificateDto(String title, String university) {
        this.title = title;
        this.university = university;
    }

    public CertificateDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "CertificateDto{" +
                "title='" + title + '\'' +
                ", university='" + university + '\'' +
                '}';
    }

}

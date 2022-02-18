package gr.hua.dit.ds.reference.letter.service.payload;

public class CourseDto {

    private String title;
    private String university;

    public CourseDto(String title, String university) {
        this.title = title;
        this.university = university;
    }

    public CourseDto() {
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
        return "CourseDto{" +
                "title='" + title + '\'' +
                ", university='" + university + '\'' +
                '}';
    }
}

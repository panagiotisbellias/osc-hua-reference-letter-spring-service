package gr.hua.dit.ds.reference.letter.service.entity;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "university")
    private String university;

    public Course() {
    }

    public Course(int id, String title, String university) {
        this.id = id;
        this.title = title;
        this.university = university;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", university='" + university + '\'' +
                '}';
    }

}
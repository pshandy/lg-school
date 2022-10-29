package com.pshandy.lgschool.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer id;

    @Column(columnDefinition="TEXT", name = "course_title", nullable = false)
    private String title;

    @Column(columnDefinition="TEXT", name = "course_description")
    private String description;

    @ManyToOne
    @JoinColumn(name="course_language_id", nullable=false)
    private CourseLanguage language;

    @ManyToOne
    @JoinColumn(name="course_level_id", nullable=false)
    private CourseLevel level;

    @ManyToOne
    @JoinColumn(name="course_category_id", nullable=false)
    private CourseCategory category;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private Set<Lesson> lessons;

    @ManyToMany
    @JoinTable(
            name = "user_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    Set<Student> students;

    public void addStudent(Student student){
        this.students.add(student);
        student.getCourses().add(this);
    }


}

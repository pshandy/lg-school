package com.pshandy.lgschool.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "course_language")
public class CourseLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_language_id")
    private Integer id;

    @Column(columnDefinition="TEXT", name = "course_language_name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy="language")
    private Set<Course> courses;

}

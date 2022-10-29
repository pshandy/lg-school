package com.pshandy.lgschool.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "course_category")
public class CourseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_category_id")
    private Integer id;

    @Column(columnDefinition="TEXT", name = "course_category_name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy="category")
    private Set<Course> categories;

}

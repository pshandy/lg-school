package com.pshandy.lgschool.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "course_level")
public class CourseLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_level_id")
    private Integer id;

    @Column(columnDefinition="TEXT", name = "course_level_name", unique = true, nullable = false)
    private String name;

}

package com.pshandy.lgschool.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "lesson_type")
public class LessonType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_type_id")
    private Integer id;

    @Column(columnDefinition="TEXT", name = "lesson_type_name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "lessonType")
    private Set<Lesson> lessons;

}

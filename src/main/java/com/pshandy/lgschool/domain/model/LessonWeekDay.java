package com.pshandy.lgschool.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "lesson_weekday")
public class LessonWeekDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_weekday_id")
    private Integer id;

    @Column(columnDefinition="TEXT", name = "lesson_weekday_name", unique = true, nullable = false)
    private String name;

}

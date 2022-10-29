package com.pshandy.lgschool.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Integer id;

    @Column(name = "lesson_start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "lesson_end_time", nullable = false)
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "lesson_teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name="lesson_course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "lesson_weekday_id", nullable = false)
    private LessonWeekDay weekday;

    @ManyToOne
    @JoinColumn(name = "lesson_type_id", nullable = false)
    private LessonType lessonType;
    
}

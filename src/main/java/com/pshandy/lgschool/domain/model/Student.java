package com.pshandy.lgschool.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer id;

    @Column(columnDefinition="TEXT", name = "student_firstname", nullable = false)
    private String firstName;

    @Column(columnDefinition="TEXT", name = "student_lastname", nullable = false)
    private String lastName;

    @Column(columnDefinition="TEXT", name = "student_middle_name", nullable = true)
    private String middleName;

    @Column(columnDefinition="TEXT", name = "student_email", nullable = false)
    private String email;

    @Column(columnDefinition="TEXT", name = "student_phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "student_birthdate", nullable = false)
    private Date birthdate;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "student_account_id", referencedColumnName = "account_login", nullable = true, unique = true)
    private Account account;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.REMOVE)
    Set<Course> courses;

    public void addCourse(Course course){
        this.courses.add(course);
        course.students.add(this);
    }

}

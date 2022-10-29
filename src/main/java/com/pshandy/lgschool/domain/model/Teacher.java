package com.pshandy.lgschool.domain.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Integer id;

    @Column(columnDefinition="TEXT", name = "teacher_firstname", nullable = false)
    private String firstName;

    @Column(columnDefinition="TEXT", name = "teacher_lastname", nullable = false)
    private String lastName;

    @Column(columnDefinition="TEXT", name = "teacher_middle_name", nullable = true)
    private String middleName;

    @Column(columnDefinition="TEXT", name = "teacher_email", nullable = false)
    private String email;

    @Column(columnDefinition="TEXT", name = "teacher_phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "teacher_birthdate", nullable = false)
    private Date birthdate;

    @Column(name = "teacher_experience", nullable = false)
    private Integer experience;

    @Column(columnDefinition="TEXT", name = "teacher_description", nullable = true)
    private String description;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "teacher_account_id", referencedColumnName = "account_login", nullable = true, unique = true)
    private Account account;

    @OneToMany(mappedBy = "teacher")
    private Set<Lesson> lessons;

}

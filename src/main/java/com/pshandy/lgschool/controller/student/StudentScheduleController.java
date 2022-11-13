package com.pshandy.lgschool.controller.student;

import com.pshandy.lgschool.domain.model.Account;
import com.pshandy.lgschool.domain.model.Lesson;
import com.pshandy.lgschool.security.HUserDetails;
import com.pshandy.lgschool.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student/schedule")
public class StudentScheduleController {

    private final StudentService studentService;

    public StudentScheduleController(@Autowired StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/")
    public String getStudent(Model model) {
        Account account =
                ((HUserDetails)(SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal())).getAccount();
        List<Lesson> lessons = studentService.getStudentCourses(account)
                .stream()
                .flatMap(course -> course.getLessons().stream()).toList();
        model.addAttribute("lessons", lessons);
        return "student/lessons";
    }

}

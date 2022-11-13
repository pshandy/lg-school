package com.pshandy.lgschool.controller.student;

import com.pshandy.lgschool.domain.model.Account;
import com.pshandy.lgschool.domain.model.Course;
import com.pshandy.lgschool.domain.model.Student;
import com.pshandy.lgschool.security.HUserDetails;
import com.pshandy.lgschool.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/student/courses")
public class StudentCourseController {

    private final StudentService studentService;

    public StudentCourseController(@Autowired StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/")
    public String getStudent(Model model) {
        Account account =
                ((HUserDetails)(SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal())).getAccount();
        model.addAttribute("courses", studentService.getStudentCourses(account));
        return "student/courses";
    }

}

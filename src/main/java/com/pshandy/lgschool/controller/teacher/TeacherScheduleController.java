package com.pshandy.lgschool.controller.teacher;

import com.pshandy.lgschool.domain.model.Account;
import com.pshandy.lgschool.domain.model.Lesson;
import com.pshandy.lgschool.security.HUserDetails;
import com.pshandy.lgschool.service.StudentService;
import com.pshandy.lgschool.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/teacher/schedule")
public class TeacherScheduleController {

    private final TeacherService teacherService;

    public TeacherScheduleController(@Autowired TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(path = "/")
    public String getStudent(Model model) {
        Account account =
                ((HUserDetails)(SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal())).getAccount();
        Set<Lesson> lessons = teacherService.getTeacherLessons(account);
        model.addAttribute("lessons", lessons);
        return "teacher/schedule";
    }

}

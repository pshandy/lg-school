package com.pshandy.lgschool.controller.admin;

import com.pshandy.lgschool.domain.model.Teacher;
import com.pshandy.lgschool.domain.repository.AccountRepository;
import com.pshandy.lgschool.exception.AccountExistsException;
import com.pshandy.lgschool.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(@Autowired TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(path = "/")
    public String getTeachers(Model model) {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("teachers", teacherService.getTeachers());
        return "admin/teacher/display_all";
    }

    @PostMapping(path = "/")
    public String createTeacher(@ModelAttribute Teacher teacher,
                                Model model) {
        try {
            teacherService.createTeacher(teacher);
        } catch (Exception e) {
            model.addAttribute("teachers", teacherService.getTeachers());
            model.addAttribute("errorMessage", "Не удалось создать запись. Некорректный/повторяющийся логин.");
            return "admin/teacher/display_all";
        }
        return "redirect:/admin/teacher/";
    }

    @GetMapping(path = "/{id}")
    public String getTeacher(Model model,
                                 @PathVariable("id") Integer id) {
        Teacher teacher = teacherService.getTeacher(id);
        model.addAttribute("teacher", teacher);
        return "admin/teacher/edit";
    }

    @PatchMapping(path = "/{id}")
    public String updateTeacher(RedirectAttributes redirectAttributes,
                                    @PathVariable("id") Integer id,
                                    @ModelAttribute Teacher teacher,
                                    Model model) {
        try {
            teacherService.updateTeacher(id, teacher);
        } catch (Exception e) {
            model.addAttribute("teachers", teacherService.getTeachers());
            model.addAttribute("errorMessage", "Не удалось обновить запись. Некорректный/повторяющийся логин.");
            return "admin/teacher/edit";
        }
        return "redirect:/admin/teacher/";
    }


    @DeleteMapping(path = "/{id}")
    public String deleteTeacher(@PathVariable("id") Integer id) {
        teacherService.deleteTeacher(id);
        return "redirect:/admin/teacher/";
    }

}

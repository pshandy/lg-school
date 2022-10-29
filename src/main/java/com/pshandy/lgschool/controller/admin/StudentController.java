package com.pshandy.lgschool.controller.admin;

import com.pshandy.lgschool.domain.model.Student;
import com.pshandy.lgschool.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(@Autowired StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/")
    public String getStudents(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("students", studentService.getStudents());
        return "admin/student/display_all";
    }

    @PostMapping(path = "/")
    public String createStudent(@ModelAttribute Student student,
                                Model model) {
        try {
            studentService.createStudent(student);
        } catch (Exception e) {
            model.addAttribute("students", studentService.getStudents());
            model.addAttribute("errorMessage", "Не удалось создать запись. Некорректный/повторяющийся логин.");
            return "admin/student/display_all";
        }
        return "redirect:/admin/student/";
    }

    @GetMapping(path = "/{id}")
    public String getStudent(Model model,
                                 @PathVariable("id") Integer id) {
        model.addAttribute("student", studentService.getStudent(id));
        model.addAttribute("courses", studentService.getStudentCourses(id));
        return "admin/student/edit";
    }

    @PatchMapping(path = "/{id}")
    public String updateStudent(RedirectAttributes redirectAttributes,
                                    @PathVariable("id") Integer id,
                                    @ModelAttribute Student student,
                                    Model model) {
        try {
            studentService.updateStudent(id, student);
        } catch (Exception e) {
            model.addAttribute("students", studentService.getStudents());
            model.addAttribute("errorMessage", "Не удалось обновить запись. Некорректный/повторяющийся логин.");
            return "admin/student/edit";
        }
        return "redirect:/admin/student/";
    }


    @DeleteMapping(path = "/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/admin/student/";
    }

}

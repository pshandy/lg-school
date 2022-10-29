package com.pshandy.lgschool.controller.admin;

import com.pshandy.lgschool.domain.model.*;
import com.pshandy.lgschool.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin/course")
public class CourseController {

    private final CourseService courseService;
    private final CourseCategoryService courseCategoryService;
    private final CourseLevelService courseLevelService;
    private final CourseLanguageService courseLanguageService;
    private final StudentService studentService;

    public CourseController(@Autowired CourseService courseService,
                            @Autowired CourseCategoryService courseCategoryService,
                            @Autowired CourseLevelService courseLevelService,
                            @Autowired CourseLanguageService courseLanguageService,
                            @Autowired StudentService studentService) {
        this.courseService = courseService;
        this.courseCategoryService = courseCategoryService;
        this.courseLevelService = courseLevelService;
        this.courseLanguageService = courseLanguageService;
        this.studentService = studentService;
    }

    @GetMapping(path = "/")
    public String getCourses(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("languages", courseLanguageService.getCourseLanguages());
        model.addAttribute("levels", courseLevelService.getCourseLevels());
        model.addAttribute("categories", courseCategoryService.getCourseCategorys());
        model.addAttribute("courses", courseService.getCourses());
        return "admin/course/display_all";
    }

    @PostMapping(path = "/")
    public String createCourse(@ModelAttribute Course course) {
        courseService.createCourse(course);
        return "redirect:/admin/course/";
    }

    @GetMapping(path = "/{id}")
    public String getCourse(Model model,
                                 @PathVariable("id") Integer id) {
        Course course = courseService.getCourse(id);
        model.addAttribute("course", course);
        model.addAttribute("students", courseService.getCourseStudents(id));
        model.addAttribute("languages", courseLanguageService.getCourseLanguages());
        model.addAttribute("levels", courseLevelService.getCourseLevels());
        model.addAttribute("categories", courseCategoryService.getCourseCategorys());
        Set<Student> copy = new HashSet<>(studentService.getStudents());
        copy.removeAll(courseService.getCourseStudents(id));
        model.addAttribute("studentsList", copy);
        return "admin/course/edit";
    }

    @PatchMapping(path = "/{id}")
    public String updateCourse(RedirectAttributes redirectAttributes,
                                    @PathVariable("id") Integer id,
                                    @ModelAttribute Course course,
                                    Model model) {
        courseService.updateCourse(id, course);
        return "redirect:/admin/course/";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteCourse(@PathVariable("id") Integer id) {
        courseService.deleteCourse(id);
        return "redirect:/admin/course/";
    }

    @PostMapping(path = "/add_student")
    public String addStudentToCourse(@ModelAttribute Course course,
                                     @RequestParam Integer courseId,
                                     @RequestParam Integer studentId) {
        courseService.addCourseStudents(courseId, studentId);
        return "redirect:/admin/course/" + courseId;
    }

}

package com.pshandy.lgschool.controller;

import com.pshandy.lgschool.domain.model.CourseLanguage;
import com.pshandy.lgschool.service.CourseLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/course_language")
public class CourseLanguageController {

    private final CourseLanguageService courseLanguageService;

    public CourseLanguageController(@Autowired CourseLanguageService courseLanguageService) {
        this.courseLanguageService = courseLanguageService;
    }

    @GetMapping(path = "/")
    public String getCourseLanguages(Model model) {
        model.addAttribute("courseLanguage", new CourseLanguage());
        model.addAttribute("courseLanguages", courseLanguageService.getCourseLanguages());
        return "admin/course_language/display_all";
    }

    @PostMapping(path = "/")
    public String createCourseLanguage(RedirectAttributes redirectAttributes,
                                          @ModelAttribute CourseLanguage courseLanguage) {
        try {
            courseLanguageService.createCourseLanguage(courseLanguage);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Не удалось создать запись. Такая запись уже существует.");
        }
        return "redirect:/admin/course_language/";
    }

    @GetMapping(path = "/{id}")
    public String getCourseLanguage(Model model,
                                 @PathVariable("id") Integer id) {
        CourseLanguage courseLanguage = courseLanguageService.getCourseLanguage(id);
        model.addAttribute("courseLanguage", courseLanguage);
        return "admin/course_language/edit";
    }

    @PatchMapping(path = "/{id}")
    public String updateCourseLanguage(RedirectAttributes redirectAttributes,
                                    @PathVariable("id") Integer id,
                                    @ModelAttribute CourseLanguage courseLanguage,
                                    Model model) {
        try {
            courseLanguageService.updateCourseLanguage(id, courseLanguage);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Не удалось обновить запись. Такая запись уже существует.");
            return "admin/course_language/edit";
        }
        return "redirect:/admin/course_language/";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteCourseLanguage(@PathVariable("id") Integer id) {
        courseLanguageService.deleteCourseLanguage(id);
        return "redirect:/admin/course_language/";
    }

}

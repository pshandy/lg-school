package com.pshandy.lgschool.controller;

import com.pshandy.lgschool.domain.model.CourseLevel;
import com.pshandy.lgschool.domain.repository.CourseLevelRepository;
import com.pshandy.lgschool.service.CourseLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/course_level")
public class CourseLevelController {

    private final CourseLevelService courseLevelService;

    public CourseLevelController(@Autowired CourseLevelService courseLevelService) {
        this.courseLevelService = courseLevelService;
    }

    @GetMapping(path = "/")
    public String getCourseLevels(Model model) {
        model.addAttribute("courseLevel", new CourseLevel());
        model.addAttribute("courseLevels", courseLevelService.getCourseLevels());
        return "admin/course_level/display_all";
    }

    @PostMapping(path = "/")
    public String createCourseLevel(RedirectAttributes redirectAttributes,
                                          @ModelAttribute CourseLevel courseLevel) {
        try {
            courseLevelService.createCourseLevel(courseLevel);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Не удалось создать запись. Такая запись уже существует.");
        }
        return "redirect:/admin/course_level/";
    }

    @GetMapping(path = "/{id}")
    public String getCourseLevel(Model model,
                                 @PathVariable("id") Integer id) {
        CourseLevel courseLevel = courseLevelService.getCourseLevel(id);
        model.addAttribute("courseLevel", courseLevel);
        return "admin/course_level/edit";
    }

    @PatchMapping(path = "/{id}")
    public String updateCourseLevel(RedirectAttributes redirectAttributes,
                                    @PathVariable("id") Integer id,
                                    @ModelAttribute CourseLevel courseLevel,
                                    Model model) {
        try {
            courseLevelService.updateCourseLevel(id, courseLevel);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Не удалось обновить запись. Такая запись уже существует.");
            return "admin/course_level/edit";
        }
        return "redirect:/admin/course_level/";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteCourseLevel(@PathVariable("id") Integer id) {
        courseLevelService.deleteCourseLevel(id);
        return "redirect:/admin/course_level/";
    }

}

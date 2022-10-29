package com.pshandy.lgschool.controller.admin;

import com.pshandy.lgschool.domain.model.CourseCategory;
import com.pshandy.lgschool.service.CourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/course_category")
public class CourseCategoryController {

    private final CourseCategoryService courseCategoryService;

    public CourseCategoryController(@Autowired CourseCategoryService courseCategoryService) {
        this.courseCategoryService = courseCategoryService;
    }

    @GetMapping(path = "/")
    public String getCourseCategorys(Model model) {
        model.addAttribute("courseCategory", new CourseCategory());
        model.addAttribute("courseCategorys", courseCategoryService.getCourseCategorys());
        return "admin/course_category/display_all";
    }

    @PostMapping(path = "/")
    public String createCourseCategory(RedirectAttributes redirectAttributes,
                                          @ModelAttribute CourseCategory courseCategory) {
        try {
            courseCategoryService.createCourseCategory(courseCategory);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Не удалось создать запись. Такая запись уже существует.");
        }
        return "redirect:/admin/course_category/";
    }

    @GetMapping(path = "/{id}")
    public String getCourseCategory(Model model,
                                 @PathVariable("id") Integer id) {
        CourseCategory courseCategory = courseCategoryService.getCourseCategory(id);
        model.addAttribute("courseCategory", courseCategory);
        return "admin/course_category/edit";
    }

    @PatchMapping(path = "/{id}")
    public String updateCourseCategory(@PathVariable("id") Integer id,
                                    @ModelAttribute CourseCategory courseCategory,
                                    Model model) {
        try {
            courseCategoryService.updateCourseCategory(id, courseCategory);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Не удалось обновить запись. Такая запись уже существует.");
            return "admin/course_category/edit";
        }
        return "redirect:/admin/course_category/";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteCourseCategory(RedirectAttributes redirectAttributes,
                                       @PathVariable("id") Integer id) {
        try {
            courseCategoryService.deleteCourseCategory(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Не удалось удалить запись. Обнаружены внешние ссылки.");
        }
        return "redirect:/admin/course_category/";
    }

}

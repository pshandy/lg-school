package com.pshandy.lgschool.controller;

import com.pshandy.lgschool.domain.model.LessonType;
import com.pshandy.lgschool.service.LessonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/lesson_type")
public class LessonTypeController {

    private final LessonTypeService lessonTypeService;

    public LessonTypeController(@Autowired LessonTypeService lessonTypeService) {
        this.lessonTypeService = lessonTypeService;
    }

    @GetMapping(path = "/")
    public String getLessonTypes(Model model) {
        model.addAttribute("lessonType", new LessonType());
        model.addAttribute("lessonTypes", lessonTypeService.getLessonTypes());
        return "admin/lesson_type/display_all";
    }

    @PostMapping(path = "/")
    public String createLessonType(RedirectAttributes redirectAttributes,
                                          @ModelAttribute LessonType lessonType) {
        try {
            lessonTypeService.createLessonType(lessonType);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Не удалось создать запись. Такая запись уже существует.");
        }
        return "redirect:/admin/lesson_type/";
    }

    @GetMapping(path = "/{id}")
    public String getLessonType(Model model,
                                 @PathVariable("id") Integer id) {
        LessonType lessonType = lessonTypeService.getLessonType(id);
        model.addAttribute("lessonType", lessonType);
        return "admin/lesson_type/edit";
    }

    @PatchMapping(path = "/{id}")
    public String updateLessonType(RedirectAttributes redirectAttributes,
                                    @PathVariable("id") Integer id,
                                    @ModelAttribute LessonType lessonType,
                                    Model model) {
        try {
            lessonTypeService.updateLessonType(id, lessonType);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Не удалось обновить запись. Такая запись уже существует.");
            return "admin/lesson_type/edit";
        }
        return "redirect:/admin/lesson_type/";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteLessonType(@PathVariable("id") Integer id) {
        lessonTypeService.deleteLessonType(id);
        return "redirect:/admin/lesson_type/";
    }

}

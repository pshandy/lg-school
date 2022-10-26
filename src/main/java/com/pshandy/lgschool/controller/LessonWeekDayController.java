package com.pshandy.lgschool.controller;

import com.pshandy.lgschool.domain.model.LessonWeekDay;
import com.pshandy.lgschool.service.LessonWeekDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/lesson_weekday")
public class LessonWeekDayController {

    private final LessonWeekDayService lessonWeekDayService;

    public LessonWeekDayController(@Autowired LessonWeekDayService lessonWeekDayService) {
        this.lessonWeekDayService = lessonWeekDayService;
    }

    @GetMapping(path = "/")
    public String getLessonWeekDays(Model model) {
        model.addAttribute("lessonWeekDay", new LessonWeekDay());
        model.addAttribute("lessonWeekDays", lessonWeekDayService.getLessonWeekDays());
        return "admin/lesson_weekday/display_all";
    }

    @PostMapping(path = "/")
    public String createLessonWeekDay(RedirectAttributes redirectAttributes,
                                   @ModelAttribute LessonWeekDay lessonWeekDay) {
        try {
            lessonWeekDayService.createLessonWeekDay(lessonWeekDay);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Не удалось создать запись. Такая запись уже существует.");
        }
        return "redirect:/admin/lesson_weekday/";
    }

    @GetMapping(path = "/{id}")
    public String getLessonWeekDay(Model model,
                                @PathVariable("id") Integer id) {
        LessonWeekDay lessonWeekDay = lessonWeekDayService.getLessonWeekDay(id);
        model.addAttribute("lessonWeekDay", lessonWeekDay);
        return "admin/lesson_weekday/edit";
    }

    @PatchMapping(path = "/{id}")
    public String updateLessonWeekDay(RedirectAttributes redirectAttributes,
                                   @PathVariable("id") Integer id,
                                   @ModelAttribute LessonWeekDay lessonWeekDay,
                                   Model model) {
        try {
            lessonWeekDayService.updateLessonWeekDay(id, lessonWeekDay);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Не удалось обновить запись. Такая запись уже существует.");
            return "admin/lesson_weekday/edit";
        }
        return "redirect:/admin/lesson_weekday/";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteLessonWeekDay(@PathVariable("id") Integer id) {
        lessonWeekDayService.deleteLessonWeekDay(id);
        return "redirect:/admin/lesson_weekday/";
    }
    
}

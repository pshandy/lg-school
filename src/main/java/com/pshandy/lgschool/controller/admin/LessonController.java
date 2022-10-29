package com.pshandy.lgschool.controller.admin;

import com.pshandy.lgschool.domain.model.Lesson;
import com.pshandy.lgschool.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/lesson")
public class LessonController {

    private final LessonService lessonService;

    private final CourseService courseService;

    private final LessonWeekDayService lessonWeekDayService;

    private final LessonTypeService lessonTypeService;

    private final TeacherService teacherService;

    public LessonController(@Autowired LessonService lessonService,
                            @Autowired CourseService courseService,
                            @Autowired LessonWeekDayService lessonWeekDayService,
                            @Autowired LessonTypeService lessonTypeService,
                            @Autowired TeacherService teacherService) {
        this.lessonService = lessonService;
        this.lessonWeekDayService = lessonWeekDayService;
        this.lessonTypeService = lessonTypeService;
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @GetMapping(path = "/")
    public String getLessons(Model model) {
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("lessons", lessonService.getLessons());
        model.addAttribute("courses", courseService.getCourses());
        model.addAttribute("weekdays", lessonWeekDayService.getLessonWeekDays());
        model.addAttribute("types", lessonTypeService.getLessonTypes());
        model.addAttribute("teachers", teacherService.getTeachers());
        return "admin/lesson/display_all";
    }

    @PostMapping(path = "/")
    public String createLesson(@ModelAttribute Lesson lesson) {
        lessonService.createLesson(lesson);
        return "redirect:/admin/lesson/";
    }

    @GetMapping(path = "/{id}")
    public String getLesson(Model model,
                                 @PathVariable("id") Integer id) {
        Lesson lesson = lessonService.getLesson(id);
        model.addAttribute("lesson", lesson);
        model.addAttribute("lessons", lessonService.getLessons());
        model.addAttribute("courses", courseService.getCourses());
        model.addAttribute("weekdays", lessonWeekDayService.getLessonWeekDays());
        model.addAttribute("types", lessonTypeService.getLessonTypes());
        model.addAttribute("teachers", teacherService.getTeachers());
        return "admin/lesson/edit";
    }

    @PatchMapping(path = "/{id}")
    public String updateLesson(RedirectAttributes redirectAttributes,
                                    @PathVariable("id") Integer id,
                                    @ModelAttribute Lesson lesson,
                                    Model model) {
        lessonService.updateLesson(id, lesson);
        return "redirect:/admin/lesson/";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteLesson(@PathVariable("id") Integer id) {
        lessonService.deleteLesson(id);
        return "redirect:/admin/lesson/";
    }

}

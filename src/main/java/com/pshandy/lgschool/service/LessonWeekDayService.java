package com.pshandy.lgschool.service;

import com.pshandy.lgschool.domain.model.LessonWeekDay;
import com.pshandy.lgschool.domain.repository.LessonWeekDayRepository;
import com.pshandy.lgschool.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonWeekDayService {

    private final LessonWeekDayRepository lessonWeekDayRepository;

    public LessonWeekDayService(@Autowired LessonWeekDayRepository lessonWeekDayRepository) {
        this.lessonWeekDayRepository = lessonWeekDayRepository;
    }

    public LessonWeekDay getLessonWeekDay(Integer id) {
        return lessonWeekDayRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public LessonWeekDay createLessonWeekDay(LessonWeekDay lessonWeekDay) {
        return lessonWeekDayRepository.save(lessonWeekDay);
    }

    public List<LessonWeekDay> getLessonWeekDays() {
        return (List<LessonWeekDay>) lessonWeekDayRepository.findAll();
    }

    public LessonWeekDay updateLessonWeekDay(Integer id, LessonWeekDay request) {
        LessonWeekDay fromDb = getLessonWeekDay(id);
        fromDb.setName(request.getName());
        return lessonWeekDayRepository.save(fromDb);
    }

    public void deleteLessonWeekDay(Integer id) {
        LessonWeekDay fromDb = getLessonWeekDay(id);
        lessonWeekDayRepository.delete(fromDb);
    }
    
}

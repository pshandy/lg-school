package com.pshandy.lgschool.service;

import com.pshandy.lgschool.domain.model.Lesson;
import com.pshandy.lgschool.domain.repository.LessonRepository;
import com.pshandy.lgschool.exception.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;

    public LessonService(@Autowired LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public Lesson getLesson(Integer id) {
        return lessonRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public List<Lesson> getLessons() {
        return (List<Lesson>) lessonRepository.findAll();
    }


    public Lesson updateLesson(Integer id, Lesson request) {
        Lesson fromDb = getLesson(id);
        BeanUtils.copyProperties(request, fromDb);
        return lessonRepository.save(fromDb);
    }

    public void deleteLesson(Integer id) {
        Lesson fromDb = getLesson(id);
        lessonRepository.delete(fromDb);
    }
    
}

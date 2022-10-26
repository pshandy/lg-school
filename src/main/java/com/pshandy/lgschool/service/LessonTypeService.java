package com.pshandy.lgschool.service;

import com.pshandy.lgschool.domain.model.LessonType;
import com.pshandy.lgschool.domain.repository.LessonTypeRepository;
import com.pshandy.lgschool.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonTypeService {

    private final LessonTypeRepository lessonTypeRepository;

    public LessonTypeService(@Autowired LessonTypeRepository lessonTypeRepository) {
        this.lessonTypeRepository = lessonTypeRepository;
    }

    public LessonType getLessonType(Integer id) {
        return lessonTypeRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public LessonType createLessonType(LessonType lessonType) {
        return lessonTypeRepository.save(lessonType);
    }

    public List<LessonType> getLessonTypes() {
        return (List<LessonType>) lessonTypeRepository.findAll();
    }

    public LessonType updateLessonType(Integer id, LessonType request) {
        LessonType fromDb = getLessonType(id);
        fromDb.setName(request.getName());
        return lessonTypeRepository.save(fromDb);
    }

    public void deleteLessonType(Integer id) {
        LessonType fromDb = getLessonType(id);
        lessonTypeRepository.delete(fromDb);
    }
    
}

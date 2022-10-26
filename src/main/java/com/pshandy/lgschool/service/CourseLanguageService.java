package com.pshandy.lgschool.service;

import com.pshandy.lgschool.domain.model.CourseLanguage;
import com.pshandy.lgschool.domain.repository.CourseLanguageRepository;
import com.pshandy.lgschool.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseLanguageService {

    private final CourseLanguageRepository courseLanguageRepository;

    public CourseLanguageService(@Autowired CourseLanguageRepository courseLanguageRepository) {
        this.courseLanguageRepository = courseLanguageRepository;
    }

    public CourseLanguage getCourseLanguage(Integer id) {
        return courseLanguageRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public CourseLanguage createCourseLanguage(CourseLanguage courseLanguage) {
        return courseLanguageRepository.save(courseLanguage);
    }

    public List<CourseLanguage> getCourseLanguages() {
        return (List<CourseLanguage>) courseLanguageRepository.findAll();
    }

    public CourseLanguage updateCourseLanguage(Integer id, CourseLanguage request) {
        CourseLanguage fromDb = getCourseLanguage(id);
        fromDb.setName(request.getName());
        return courseLanguageRepository.save(fromDb);
    }

    public void deleteCourseLanguage(Integer id) {
        CourseLanguage fromDb = getCourseLanguage(id);
        courseLanguageRepository.delete(fromDb);
    }

}

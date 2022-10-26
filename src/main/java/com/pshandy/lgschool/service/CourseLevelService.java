package com.pshandy.lgschool.service;

import com.pshandy.lgschool.domain.model.CourseLevel;
import com.pshandy.lgschool.domain.repository.CourseLevelRepository;
import com.pshandy.lgschool.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseLevelService {

    private final CourseLevelRepository courseLevelRepository;

    public CourseLevelService(@Autowired CourseLevelRepository courseLevelRepository) {
        this.courseLevelRepository = courseLevelRepository;
    }

    public CourseLevel getCourseLevel(Integer id) {
        return courseLevelRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public CourseLevel createCourseLevel(CourseLevel courseLevel) {
        return courseLevelRepository.save(courseLevel);
    }

    public List<CourseLevel> getCourseLevels() {
        return (List<CourseLevel>) courseLevelRepository.findAll();
    }

    public CourseLevel updateCourseLevel(Integer id, CourseLevel request) {
        CourseLevel fromDb = getCourseLevel(id);
        fromDb.setName(request.getName());
        return courseLevelRepository.save(fromDb);
    }

    public void deleteCourseLevel(Integer id) {
        CourseLevel fromDb = getCourseLevel(id);
        courseLevelRepository.delete(fromDb);
    }

}

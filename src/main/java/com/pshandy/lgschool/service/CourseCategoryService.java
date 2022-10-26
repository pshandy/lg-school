package com.pshandy.lgschool.service;

import com.pshandy.lgschool.domain.model.CourseCategory;
import com.pshandy.lgschool.domain.repository.CourseCategoryRepository;
import com.pshandy.lgschool.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseCategoryService {

    private final CourseCategoryRepository courseCategoryRepository;

    public CourseCategoryService(@Autowired CourseCategoryRepository courseCategoryRepository) {
        this.courseCategoryRepository = courseCategoryRepository;
    }

    public CourseCategory getCourseCategory(Integer id) {
        return courseCategoryRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public CourseCategory createCourseCategory(CourseCategory courseCategory) {
        return courseCategoryRepository.save(courseCategory);
    }

    public List<CourseCategory> getCourseCategorys() {
        return (List<CourseCategory>) courseCategoryRepository.findAll();
    }

    public CourseCategory updateCourseCategory(Integer id, CourseCategory request) {
        CourseCategory fromDb = getCourseCategory(id);
        fromDb.setName(request.getName());
        return courseCategoryRepository.save(fromDb);
    }

    public void deleteCourseCategory(Integer id) {
        CourseCategory fromDb = getCourseCategory(id);
        courseCategoryRepository.delete(fromDb);
    }
    
}

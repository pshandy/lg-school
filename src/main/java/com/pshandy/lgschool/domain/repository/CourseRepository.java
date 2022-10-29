package com.pshandy.lgschool.domain.repository;

import com.pshandy.lgschool.domain.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer> {
}

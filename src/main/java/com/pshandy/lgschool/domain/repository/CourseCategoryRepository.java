package com.pshandy.lgschool.domain.repository;

import com.pshandy.lgschool.domain.model.CourseCategory;
import org.springframework.data.repository.CrudRepository;

public interface CourseCategoryRepository extends CrudRepository<CourseCategory, Integer> {
}

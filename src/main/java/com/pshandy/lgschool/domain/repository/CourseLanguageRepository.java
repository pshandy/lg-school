package com.pshandy.lgschool.domain.repository;

import com.pshandy.lgschool.domain.model.CourseLanguage;
import com.pshandy.lgschool.domain.model.CourseLevel;
import org.springframework.data.repository.CrudRepository;

public interface CourseLanguageRepository extends CrudRepository<CourseLanguage, Integer> {
}

package com.pshandy.lgschool.domain.repository;

import com.pshandy.lgschool.domain.model.Lesson;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
}

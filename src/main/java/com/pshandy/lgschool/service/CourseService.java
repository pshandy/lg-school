package com.pshandy.lgschool.service;

import com.pshandy.lgschool.domain.model.Course;
import com.pshandy.lgschool.domain.model.Student;
import com.pshandy.lgschool.domain.repository.CourseRepository;
import com.pshandy.lgschool.domain.repository.StudentRepository;
import com.pshandy.lgschool.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.AcceptPendingException;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    private final StudentService studentService;

    public CourseService(@Autowired CourseRepository courseRepository,
                         @Autowired StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
    }

    public Course getCourse(Integer id) {
        return courseRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    public Course updateCourse(Integer id, Course request) {
        Course fromDb = getCourse(id);
        fromDb.setTitle(request.getTitle());
        fromDb.setDescription(request.getDescription());
        fromDb.setLanguage(request.getLanguage());
        fromDb.setCategory(request.getCategory());
        fromDb.setLevel(request.getLevel());
        return courseRepository.save(fromDb);
    }

    public void deleteCourse(Integer id) {
        Course fromDb = getCourse(id);
        courseRepository.delete(fromDb);
    }

    public Set<Student> getCourseStudents(Integer courseId) {
        Course fromDb = getCourse(courseId);
        return fromDb.getStudents();
    }

    public void addCourseStudents(Integer courseId, Integer studentId) {
        Course fromDb = getCourse(courseId);
        fromDb.addStudent(studentService.getStudent(studentId));
        courseRepository.save(fromDb);
    }

    
}

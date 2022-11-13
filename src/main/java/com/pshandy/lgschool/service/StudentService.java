package com.pshandy.lgschool.service;

import com.pshandy.lgschool.domain.model.Account;
import com.pshandy.lgschool.domain.model.Course;
import com.pshandy.lgschool.domain.model.Student;
import com.pshandy.lgschool.domain.repository.AccountRepository;
import com.pshandy.lgschool.domain.repository.StudentRepository;
import com.pshandy.lgschool.domain.repository.TeacherRepository;
import com.pshandy.lgschool.exception.AccountExistsException;
import com.pshandy.lgschool.exception.AccountNotExistsException;
import com.pshandy.lgschool.exception.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final AccountRepository accountRepository;

    private final TeacherRepository teacherRepository;


    public StudentService(@Autowired StudentRepository studentRepository,
                          @Autowired AccountRepository accountRepository,
                          @Autowired TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.accountRepository = accountRepository;
        this.teacherRepository = teacherRepository;
    }

    public Student getStudent(Integer id) {
        return studentRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Student createStudent(Student student) {
        String accLogin = student.getAccount().getLogin();
        if (teacherRepository.existsByLogin(accLogin)) {
            throw new AccountExistsException();
        }
        if (accLogin.length() == 0) {
            student.setAccount(null);
            return studentRepository.save(student);
        }
        if (teacherRepository.existsByLogin(accLogin)) {
            throw new AccountExistsException();
        }
        if (!accountRepository.existsByLogin(accLogin)) {
            throw new AccountNotExistsException();
        }
        return studentRepository.save(student);
    }

    public List<Student> getStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    public Student updateStudent(Integer id, Student request) {

        Student fromDb = getStudent(id);
        BeanUtils.copyProperties(request, fromDb);

        String accLogin = fromDb.getAccount().getLogin();
        if (teacherRepository.existsByLogin(accLogin)) {
            throw new AccountExistsException();
        }
        if (accLogin.length() == 0) {
            fromDb.setAccount(null);
            return studentRepository.save(fromDb);
        }
        if (!accountRepository.existsByLogin(accLogin)) {
            throw new AccountNotExistsException();
        }
        return studentRepository.save(fromDb);

    }

    public void deleteStudent(Integer id) {
        Student fromDb = getStudent(id);
        studentRepository.delete(fromDb);
    }

    public Set<Course> getStudentCourses(Integer id) {
        Student fromDb = getStudent(id);
        return fromDb.getCourses();
    }

    public Set<Course> getStudentCourses(Account account) {
        if (account == null) {
            return (null);
        }
        Optional<Student> student = studentRepository.findByAccount(account);
        if (student.isEmpty()) {
            return (null);
        }
        return student.get().getCourses();
    }

}

package com.pshandy.lgschool.service;

import com.pshandy.lgschool.domain.model.*;
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
public class TeacherService {

    private final TeacherRepository teacherRepository;

    private final AccountRepository accountRepository;

    private final StudentRepository studentRepository;

    public TeacherService(@Autowired StudentRepository studentRepository,
                          @Autowired AccountRepository accountRepository,
                          @Autowired TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.accountRepository = accountRepository;
        this.teacherRepository = teacherRepository;
    }

    public Teacher getTeacher(Integer id) {
        return teacherRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Teacher createTeacher(Teacher teacher) {
        String accLogin = teacher.getAccount().getLogin();
        if (studentRepository.existsByLogin(accLogin)) {
            throw new AccountExistsException();
        }
        if (accLogin.length() == 0) {
            teacher.setAccount(null);
            return teacherRepository.save(teacher);
        }
        if (!accountRepository.existsByLogin(accLogin)) {
            throw new AccountNotExistsException();
        }
        return teacherRepository.save(teacher);
    }

    public List<Teacher> getTeachers() {
        return (List<Teacher>) teacherRepository.findAll();
    }

    public Teacher updateTeacher(Integer id, Teacher request) {

        Teacher fromDb = getTeacher(id);
        BeanUtils.copyProperties(request, fromDb);

        String accLogin = fromDb.getAccount().getLogin();
        if (studentRepository.existsByLogin(accLogin)) {
            throw new AccountExistsException();
        }
        if (accLogin.length() == 0) {
            fromDb.setAccount(null);
            return teacherRepository.save(fromDb);
        }
        if (!accountRepository.existsByLogin(accLogin)) {
            throw new AccountNotExistsException();
        }
        return teacherRepository.save(fromDb);

    }
    public void deleteTeacher(Integer id) {
        Teacher fromDb = getTeacher(id);
        teacherRepository.delete(fromDb);
    }

    public Set<Lesson> getTeacherLessons(Account account) {
        if (account == null) {
            return (null);
        }
        Optional<Teacher> teacher = teacherRepository.findByAccount(account);
        if (teacher.isEmpty()) {
            return (null);
        }
        return teacher.get().getLessons();
    }
    
}

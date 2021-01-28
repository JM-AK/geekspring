package com.geekbrains.geekspring.services;

import com.geekbrains.geekspring.entities.Course;
import com.geekbrains.geekspring.entities.Student;
import com.geekbrains.geekspring.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentsService {
    private StudentsRepository studentsRepository;
    private CoursesService coursesService;

    @Autowired
    public void setStudentsRepository(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Autowired
    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    public StudentsService() {
    }

    public List<Student> getAllStudentsList() {
        return (List<Student>)studentsRepository.findAll();
    }

    public void addStudent(Student s) {
        studentsRepository.save(s);
    }

    public void removeById(Long id) {
        studentsRepository.deleteById(id);
    }

    public List<Course> getCoursesByStudentId(Long id) {
        return studentsRepository.findOneById(id).getCourses();
    }

    public List<Course> getMissingCoursesByStudentId(Long id) {
        List<Course> courses = coursesService.getAllCoursesList();
        List<Course> studentsCourses = studentsRepository.findOneById(id).getCourses();
        courses.removeAll(studentsCourses);
        return courses;
    }

    public void addCourseToStudent(Long studentId, Long courseId) {
        Student student = studentsRepository.findOneById(studentId);
        Course course = coursesService.getCourseById(courseId);
        if(student.getCourses() == null) {
            student.setCourses(new ArrayList<Course>());
        }
        student.getCourses().add(course);
        studentsRepository.save(student);
    }

    public void removeCourseFromStudent(Long studentId, Long courseId) {
        Student student = studentsRepository.findOneById(studentId);
        Course course = coursesService.getCourseById(courseId);
        student.getCourses().remove(course);
        studentsRepository.save(student);
    }
}

package com.geekbrains.geekspring.controllers;

import com.geekbrains.geekspring.entities.Student;
import com.geekbrains.geekspring.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/students")
@Transactional
public class StudentsController {
    private StudentsService studentsService;

    @Autowired
    public void setStudentsService(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @RequestMapping("/list")
    @Transactional
    public String showStudentsList(Model model) {
        List<Student> allStudents = studentsService.getAllStudentsList();
        model.addAttribute("studentsList", allStudents);
        return "students-list";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String showAddForm(Model model) {
        Student student = new Student();
        student.setName("Unknown");
        model.addAttribute("student", student);
        return "add-student-form";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String showAddForm(Student student) {
        studentsService.addStudent(student);
        return "redirect:/students/list";
    }

    @RequestMapping(path = "/remove/{id}", method = RequestMethod.GET)
    public String removeById(@PathVariable(value = "id") Long studentId) {
        studentsService.removeById(studentId);
        return "redirect:/students/list";
    }

    @RequestMapping(path = "/courses/{id}", method = RequestMethod.GET)
    public String showStudentsCoursesInfo(Model model, @PathVariable(value = "id") Long studentId) {
        model.addAttribute("studentId", studentId);
        model.addAttribute("studentCourses", studentsService.getCoursesByStudentId(studentId));
        model.addAttribute("studentMissingCourses", studentsService.getMissingCoursesByStudentId(studentId));
        return "student-courses-list";
    }

    @RequestMapping(path = "/courses/add/{studentId}/{courseId}", method = RequestMethod.GET)
    public String addCourseToStudent(Model model, @PathVariable(value = "studentId") Long studentId, @PathVariable(value = "courseId") Long courseId) {
        studentsService.addCourseToStudent(studentId, courseId);
        return "redirect:/students/courses/" + studentId;
    }

    @RequestMapping(path = "/courses/remove/{studentId}/{courseId}", method = RequestMethod.GET)
    public String removeCourseFromStudent(Model model, @PathVariable(value = "studentId") Long studentId, @PathVariable(value = "courseId") Long courseId) {
        studentsService.removeCourseFromStudent(studentId, courseId);
        return "redirect:/students/courses/" + studentId;
    }
}

package com.student.com.controllers;

import com.student.com.handler.StudentDecoder;
import com.student.com.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentDecoder studentDecoder;


    @GetMapping("/")
    public String index() {
        Student[] students = studentDecoder.get_students("https://hccs-advancejava.s3.amazonaws.com/student_course.json");
        int len = students.length;
        for (int i = 0; i < len; i++) {
            System.out.println(students[i]);
        }

        return "Hello World";
    }

    // /search?name=John
    @GetMapping("/search")
    public String searchByName(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "course", required = false) String course){
        Student to_return = null;
        Student[] students = studentDecoder.get_students("https://hccs-advancejava.s3.amazonaws.com/student_course.json");
        if (students == null){
            return "No students found";
        }
        int len = students.length;

        if (name != null){
            for (int i = 0; i < len; i++) {
                if (students[i].getFirst_name().equals(name)){
                    to_return = students[i];
                    break;
                }
            }
            return to_return.toString();
        }
        else if (course != null){
            Student[] students_with_course = new Student[len];
            for (int i = 0; i < len; i++) {
                String[] course_nos = students[i].courseNos();
                if (course_nos == null){
                    continue;
                }
                for (int j = 0; j < course_nos.length; j++) {
                    if (course_nos[j].equals(course)){
                        students_with_course[i] = students[i];

                    }
                }
            }


            String json = "";
            for (int i = 0; i < len; i++) {
                if (students_with_course[i] != null){
                    json += students_with_course[i].toString();
                }
            }
            return json;
        }
        return "Please enter a name or course";


    }
}

package com.student.com.handler;

import com.student.com.models.Student;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDecoder {

    public Student[] get_students(String url) {
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        Student[] students = null;
        try {
            students = mapper.readValue(json, Student[].class);
        } catch (Exception e) {
            System.out.println(e);
        }
        return students;


    }




}

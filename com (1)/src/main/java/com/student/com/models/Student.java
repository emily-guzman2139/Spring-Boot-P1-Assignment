package com.student.com.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude()
class Course{
    private String courseNo;
    private String grade;
    private int creditHours;

    public Course() {
    }

    public Course(String courseNo, String grade, int creditHours) {
        this.courseNo = courseNo;
        this.grade = grade;
        this.creditHours = creditHours;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

}

@JsonInclude()
public class Student {

    private int id;
    private String first_name;
    private String email;
    private String gender;
    private Course[] course;

    public Student(int id, String first_name, String email, String gender, Course[] course) {
        this.id = id;
        this.first_name = first_name;
        this.email = email;
        this.gender = gender;
        this.course = course;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Course[] getCourse() {
        return course;
    }

    public void setCourse(Course[] course) {
        this.course = course;
    }

    public String getGpa() {
        int total_credit_hours = 0;
        int total_grade_points = 0;
        if (this.course == null) {
            return "0.00";
        }
        for (Course course : this.course) {
            total_credit_hours += course.getCreditHours();
            switch (course.getGrade()) {
                case "A":
                    total_grade_points += 4 * course.getCreditHours();
                    break;
                case "B":
                    total_grade_points += 3 * course.getCreditHours();
                    break;
                case "C":
                    total_grade_points += 2 * course.getCreditHours();
                    break;
                case "D":
                    total_grade_points += 1 * course.getCreditHours();
                    break;
                case "F":
                    total_grade_points += 0 * course.getCreditHours();
                    break;
            }
        }
        return String.format("%.2f", (double) total_grade_points / total_credit_hours);
    }

    public String[] courseNos() {
        if (this.course == null) {
            return null;
        }
        String[] courseNos = new String[this.course.length];
        for (int i = 0; i < this.course.length; i++) {
            courseNos[i] = this.course[i].getCourseNo();
        }
        return courseNos;
    }






    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(this);
        } catch (Exception e) {
            System.out.println(e);
        }
        return json;
    }


}

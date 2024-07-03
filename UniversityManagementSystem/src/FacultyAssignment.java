/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
import java.io.*;
class FacultyAssignment implements Serializable {
    private Course course;
    private Faculty faculty;

    public FacultyAssignment(Course course, Faculty faculty) {
        this.course = course;
        this.faculty = faculty;
    }

    public Course getCourse() {
        return course;
    }

    public Faculty getFaculty() {
        return faculty;
    }
}

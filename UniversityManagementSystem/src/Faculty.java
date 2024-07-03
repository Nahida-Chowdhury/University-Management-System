/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
import java.io.*;
class Faculty implements Serializable {
    private String facultyId;
    private String facultyName;
    private FacultyType facultyType;

    public Faculty(String facultyId, String facultyName, FacultyType facultyType) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.facultyType = facultyType;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public FacultyType getFacultyType() {
        return facultyType;
    }
}

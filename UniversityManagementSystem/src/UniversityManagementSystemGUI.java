/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UniversityManagementSystemGUI extends JFrame {
    private List<Student> studentList;
    private List<Course> courseList;
    private DefaultTableModel tableModel;
    private JTable dataTable;

    private JTextField studentIdField, studentNameField, courseIdField, courseNameField;
    private JComboBox<CourseType> courseTypeComboBox;
    private JButton saveButton;

    public UniversityManagementSystemGUI() {
        setTitle("University Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        initUI();
        initListeners();

        studentList = new ArrayList<>();
        courseList = new ArrayList<>();
        deserializeData();
    }

    private void initUI() {
        
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        inputPanel.add(studentIdField);
        inputPanel.add(new JLabel("Student Name:"));
        studentNameField = new JTextField();
        inputPanel.add(studentNameField);
        inputPanel.add(new JLabel("Course ID:"));
        courseIdField = new JTextField();
        inputPanel.add(courseIdField);
        inputPanel.add(new JLabel("Course Name:"));
        courseNameField = new JTextField();
        inputPanel.add(courseNameField);
        inputPanel.add(new JLabel("Course Type:"));
        courseTypeComboBox = new JComboBox<>(CourseType.values());
        inputPanel.add(courseTypeComboBox);

        saveButton = new JButton("Save"); 
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(saveButton, BorderLayout.SOUTH);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Student ID");
        tableModel.addColumn("Student Name");
        tableModel.addColumn("Course ID");
        tableModel.addColumn("Course Name");
        tableModel.addColumn("Course Type");

        dataTable = new JTable(tableModel);
        mainPanel.add(new JScrollPane(dataTable), BorderLayout.CENTER);

        add(mainPanel);
    }


    private void initListeners() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDataToFile();
            }
        });
    }

    private void saveDataToFile() {
        String studentId = studentIdField.getText();
        String studentName = studentNameField.getText();
        String courseId = courseIdField.getText();
        String courseName = courseNameField.getText();
        CourseType courseType = (CourseType) courseTypeComboBox.getSelectedItem();

        if (!studentId.isEmpty() && !studentName.isEmpty() && !courseId.isEmpty() && !courseName.isEmpty()) {
            Student student = new Student(studentId, studentName);
            Course course = new Course(courseId, courseName, courseType);

            studentList.add(student);
            courseList.add(course);

            Object[] rowData = {studentId, studentName, courseId, courseName, courseType};
            tableModel.addRow(rowData);

            serializeData();
            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
        }
    }

    private void serializeData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("university_data.ser"))) {
            oos.writeObject(studentList);
            oos.writeObject(courseList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deserializeData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("university_data.ser"))) {
            Object obj1 = ois.readObject(); 
            Object obj2 = ois.readObject();
            if (obj1 instanceof List && obj2 instanceof List) {
                studentList = (List<Student>) obj1;
                courseList = (List<Course>) obj2;

                for (int i = 0; i < studentList.size(); i++) {
                    Student student = studentList.get(i);
                    Course course = courseList.get(i);

                    Object[] rowData = {student.getStudentId(), student.getStudentName(),
                                        course.getCourseId(), course.getCourseName(), course.getCourseType()};
                    tableModel.addRow(rowData);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearInputFields() {
        studentIdField.setText("");
        studentNameField.setText("");
        courseIdField.setText("");
        courseNameField.setText("");
        courseTypeComboBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UniversityManagementSystemGUI().setVisible(true);
            }
        });
    }
}

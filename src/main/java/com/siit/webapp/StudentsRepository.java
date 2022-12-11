package com.siit.webapp;

import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentsRepository {
    public List<Student> getStudents() {
        List<String> lines = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();

        try {
            lines = Files.readAllLines(Path.of("com/siit/webapp/students.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String str : lines) {
            String[] line = str.split(",");
            List<Integer> grades = new ArrayList<>();
            for (int i = 2; i < line.length; i++) {
                grades.add(Integer.valueOf(line[i]));
            }
            studentList.add(new Student(line[0], line[1], grades));
        }
        return studentList;


    }
}


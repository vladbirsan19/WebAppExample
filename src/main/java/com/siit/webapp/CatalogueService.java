package com.siit.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CatalogueService {

    @Autowired
    public CatalogueService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    private final StudentsRepository studentsRepository;

    public String createStudentCatalogue() {
        List<Student> studentListToBeProcessed = studentsRepository.getStudents();
        String result = "";

        for (Student student : studentListToBeProcessed) {
            result = result.concat(student.getFirstName()).concat(" ").concat(student.getLastName().concat("<br></br>"));

        }
        return result;
    }

    public Double calculateAverage(Student student) {
        return student.getGrades()
                .stream()
                .mapToInt(num -> num)
                .average()
                .getAsDouble();
    }

    public String rankStudents() {
        List<Student> rankedStudents = studentsRepository.getStudents();
        rankedStudents.forEach(student -> student.setGradeAverage(calculateAverage(student)));
        rankedStudents.sort(Comparator.comparing(Student::getGradeAverage).reversed());

        String result = "";

        for (Student student : rankedStudents) {
            result = result.concat(student.getFirstName()).concat(" ").concat(student.getLastName()
                    .concat(" - ").concat(student.getGradeAverage().toString()).concat("<br></br>"));
        }
        return result;
    }
}

package com.siit.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogueService {

    @Autowired
    public CatalogueService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    private final StudentsRepository studentsRepository;
    public String createStudentCatalogue(){
        List<Student> studentListToBeProcessed = studentsRepository.getStudents();
        String result = "";

        for (Student student: studentListToBeProcessed) {
            result = result.concat(student.getFirstName()).concat(" ").concat(student.getLastName().concat("<br></br>"));
        }

        return result;
    }

}

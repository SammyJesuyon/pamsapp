package edu.miu.cs.cs489appsd.lab2b.pamsapp;

import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs489appsd.lab2b.pamsapp.model.Patient;

import java.io.File;
import java.io.IOException;

public class PAMSApp {
    public static void main(String[] args) {
        List<Patient> patients = Arrays.asList(
            new Patient(1, "John", "Doe", "555-0100", "john.doe@example.com", "100 Elm St", LocalDate.of(1990, 5, 15)),
            new Patient(2, "Jane", "Doe", "555-0101", "jane.doe@example.com", "101 Elm St", LocalDate.of(1985, 8, 20)),
            new Patient(3, "Jim", "Beam", "555-0102", "jim.beam@example.com", "102 Elm St", LocalDate.of(1975, 3, 10)),
            new Patient(4, "Jill", "Stark", "555-0103", "jill.stark@example.com", "103 Elm St", LocalDate.of(2000, 12, 25)),
            new Patient(5, "Jack", "White", "555-0104", "jack.white@example.com", "104 Elm St", LocalDate.of(1995, 7, 30))
        );

        // Sort patients by age in descending order
        patients.sort(Comparator.comparing(Patient::getAge).reversed());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            String json = objectMapper.writeValueAsString(patients);
            System.out.println(json);
            objectMapper.writeValue(new File("patients.json"), patients);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

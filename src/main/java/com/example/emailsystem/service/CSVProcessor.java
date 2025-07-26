package com.example.emailsystem.service;

import com.example.emailsystem.model.Employee;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVProcessor {
    public static List<Employee> parseCSV(MultipartFile file) throws Exception {
        List<Employee> employees = new ArrayList<>();

        CSVParser parser = CSVFormat.DEFAULT
                .withHeader()
                .withIgnoreHeaderCase() // makes header names case-insensitive
                .withTrim()             // trims leading/trailing spaces from values
                .parse(new InputStreamReader(file.getInputStream()));

        for (CSVRecord record : parser) {
            Employee emp = new Employee();
            emp.setName(record.get("name").trim());
            emp.setEmail(record.get("email").trim());
            emp.setDepartment(record.get("department").trim());
            emp.setDoj(record.get("doj").trim());
            emp.setNewDesignation(record.get("newDesignation").trim());
            emp.setNewSalary(record.get("newSalary").trim());
            emp.setEffectiveDate(record.get("effectiveDate").trim());

            // Normalize letterType field
            String type = record.get("letterType").trim();
            System.out.println("DEBUG: CSV letterType = '" + type + "'");
            emp.setLetterType("increment".equalsIgnoreCase(type) ? "appraisal" : type.toLowerCase());

            employees.add(emp);
        }

        return employees;
    }
}

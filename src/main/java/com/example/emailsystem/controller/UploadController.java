package com.example.emailsystem.controller;

import com.example.emailsystem.model.Employee;
import com.example.emailsystem.service.CSVProcessor;
import com.example.emailsystem.service.EmailService;
import com.example.emailsystem.service.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UploadController {

    @Autowired
    private EmailService emailService;

    // Route to show the upload form
    @GetMapping("/")
    public String homePage(Model model) {
        return "upload"; // This must match upload.html or update.html in src/main/resources/templates
    }

    // Handle CSV file upload and send emails
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        try {
            List<Employee> employees = CSVProcessor.parseCSV(file);
            for (Employee emp : employees) {
                byte[] pdf = PDFGenerator.generatePDF(emp);
                emailService.sendEmailWithAttachment(emp, pdf);
            }
            redirectAttributes.addFlashAttribute("message", "✅ Emails sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "❌ Failed to send emails: " + e.getMessage());
        }
        return "redirect:/";
    }
}

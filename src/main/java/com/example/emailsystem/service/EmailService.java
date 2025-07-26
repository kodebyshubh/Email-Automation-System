package com.example.emailsystem.service;

import com.example.emailsystem.model.Employee;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    public void sendEmailWithAttachment(Employee emp, byte[] pdfBytes) {
        final String from = "bohrashubh44@gmail.com"; // ✅ Your Gmail
        final String password = "cxmi ttpz ncfe zocg"; // ✅ 16-char App Password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emp.getEmail()));

            // Determine content based on letterType
            String subject;
            String textBody;
            String fileName;

            if ("promotion".equalsIgnoreCase(emp.getLetterType())) {
                subject = "Promotion Letter";
                textBody = "Dear " + emp.getName() + ",\n\nCongratulations on your promotion to " +
                        emp.getNewDesignation() + " in the " + emp.getDepartment() + " department, effective " + emp.getEffectiveDate() + ".\n\nBest regards,\nHR Department";
                fileName = "Promotion_Letter.pdf";
            } else if ("appraisal".equalsIgnoreCase(emp.getLetterType()))
 {
                subject = "Appraisal Letter";
                textBody = "Dear " + emp.getName() + ",\n\nWe are pleased to inform you of your salary appraisal. Your new salary is ₹" +
                        emp.getNewSalary() + ", effective " + emp.getEffectiveDate() + ".\n\nBest regards,\nHR Department";
                fileName = "Appraisal_Letter.pdf";
            } else {
                subject = "Notification Letter";
                textBody = "Dear " + emp.getName() + ",\n\nPlease find your attached letter.\n\nBest regards,\nHR Department";
                fileName = "Notification_Letter.pdf";
            }

            message.setSubject(subject);

            // Email body part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(textBody);

            // Attachment part
            MimeBodyPart pdfPart = new MimeBodyPart();
            pdfPart.setFileName(fileName);
            pdfPart.setContent(pdfBytes, "application/pdf");

            // Combine parts
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(pdfPart);

            message.setContent(multipart);

            // Send the email
            Transport.send(message);

        } catch (Exception e) {
            System.err.println("Email send failed: " + e.getMessage());
        }
    }
}

package com.example.emailsystem.service;

import com.example.emailsystem.model.Employee;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.net.URL;

public class PDFGenerator {
    public static byte[] generatePDF(Employee emp) throws Exception {
        Document doc = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(doc, out);
        doc.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        // ✅ Load logo
        String imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQg94QpSU6eACYlMOiu5Wy-vXWqYO0xJCp0Tg&s";
        Image logo = Image.getInstance(new URL(imageUrl));
        logo.scaleToFit(100, 100);
        logo.setAlignment(Element.ALIGN_CENTER);
        doc.add(logo);
        doc.add(new Paragraph(" ")); // Spacer

        // ✅ Get clean letterType
        String letterType = emp.getLetterType() != null ? emp.getLetterType().trim().toLowerCase() : "";
        System.out.println("DEBUG: Letter Type = '" + letterType + "'");

        switch (letterType) {
            case "promotion":
                Paragraph promoTitle = new Paragraph("PROMOTION LETTER", titleFont);
                promoTitle.setAlignment(Element.ALIGN_CENTER);
                doc.add(promoTitle);
                doc.add(new Paragraph(" "));

                doc.add(new Paragraph("Date: " + emp.getEffectiveDate(), bodyFont));
                doc.add(new Paragraph(" "));

                doc.add(new Paragraph("To,", bodyFont));
                doc.add(new Paragraph(emp.getName(), bodyFont));
                doc.add(new Paragraph(emp.getDepartment() + " Department", bodyFont));
                doc.add(new Paragraph(" "));

                doc.add(new Paragraph("Subject: Promotion Confirmation", bodyFont));
                doc.add(new Paragraph(" ", bodyFont));
                doc.add(new Paragraph("Dear " + emp.getName() + ",", bodyFont));
                doc.add(new Paragraph(" ", bodyFont));

                doc.add(new Paragraph("We are pleased to inform you that in recognition of your hard work and dedication since your date of joining on "
                        + emp.getDoj() + ", you are being promoted to the position of "
                        + emp.getNewDesignation() + " in the " + emp.getDepartment() + " department.", bodyFont));
                doc.add(new Paragraph(" ", bodyFont));
                doc.add(new Paragraph("This promotion will be effective from " + emp.getEffectiveDate() + ".", bodyFont));
                doc.add(new Paragraph("Please accept our heartfelt congratulations on this well-deserved recognition of your efforts.", bodyFont));
                doc.add(new Paragraph("We look forward to your continued contributions and success in your new role.", bodyFont));
                break;

            case "appraisal":
                Paragraph appraisalTitle = new Paragraph("APPRAISAL LETTER", titleFont);
                appraisalTitle.setAlignment(Element.ALIGN_CENTER);
                doc.add(appraisalTitle);
                doc.add(new Paragraph(" "));

                doc.add(new Paragraph("Date: " + emp.getEffectiveDate(), bodyFont));
                doc.add(new Paragraph(" "));

                doc.add(new Paragraph("To,", bodyFont));
                doc.add(new Paragraph(emp.getName(), bodyFont));
                doc.add(new Paragraph(emp.getDepartment() + " Department", bodyFont));
                doc.add(new Paragraph(" "));

                doc.add(new Paragraph("Subject: Appraisal Notification", bodyFont));
                doc.add(new Paragraph(" ", bodyFont));
                doc.add(new Paragraph("Dear " + emp.getName() + ",", bodyFont));
                doc.add(new Paragraph(" ", bodyFont));

                doc.add(new Paragraph("We are pleased to inform you that based on your continued performance and dedication since your joining on "
                        + emp.getDoj() + ", your salary has been revised as part of our appraisal cycle.", bodyFont));
                doc.add(new Paragraph("Your revised salary will be ₹" + emp.getNewSalary() + ", effective from " + emp.getEffectiveDate() + ".", bodyFont));
                doc.add(new Paragraph(" ", bodyFont));
                doc.add(new Paragraph("We appreciate your valuable contributions and look forward to your continued commitment.", bodyFont));
                break;

            default:
                Paragraph unknown = new Paragraph("⚠ UNKNOWN LETTER TYPE: " + letterType, titleFont);
                unknown.setAlignment(Element.ALIGN_CENTER);
                doc.add(unknown);
                break;
        }

        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Best Regards,", bodyFont));
        doc.add(new Paragraph("Human Resources Department", bodyFont));

        doc.close();
        return out.toByteArray();
    }
}

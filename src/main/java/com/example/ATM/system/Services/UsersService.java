package com.example.ATM.system.Services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.ATM.system.Models.Users;
import com.example.ATM.system.Repository.UsersRepository;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public Double getBalance(String AccountNumber){
        Users users = usersRepository.findByAccountNumber(AccountNumber);
        return users.getBalance(); 
    }
    public void withdraw(String accountNumber, Double amount){
        Users users = usersRepository.findByAccountNumber(accountNumber);
        if(users==null){
            System.out.println("Account not found");
        }
        Double oldBalance = users.getBalance();
        users.setBalance(oldBalance-amount);
        usersRepository.save(users);
    }   

    // public byte[] generateReceipt(String accountNumber, String amount, String transactionDate, String status, String transactionId) {
    //     try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {  // Use this single stream
    //         PdfWriter writer = new PdfWriter(out);
    //         PdfDocument pdf = new PdfDocument(writer);
    //         Document document = new Document(pdf);
    
    //         // Add content to the PDF
    //         document.add(new Paragraph("Transaction Receipt"));
    //         document.add(new Paragraph("Transaction Type: Withdrawal"));
    //         document.add(new Paragraph("Status: "+ status));
    //         document.add(new Paragraph("Account Number: " + accountNumber));
    //         document.add(new Paragraph("Transaction Id: "+ transactionId));
    //         document.add(new Paragraph("Amount: $" + amount));
    //         document.add(new Paragraph("Date: " + transactionDate));  // Use passed date
    //         document.close();
    
    //         // Return the byte array
    //         return out.toByteArray(); 
    //     } catch (Exception e) {
    //         throw new RuntimeException("Error generating receipt PDF", e);
    //     }
    // }
    public byte[] generatePdfFromHtml(String templateName) throws IOException{
        
        // String cssPath = new ClassPathResource("static/css/receipt.css").getURL().toString();
        // String jsPath = new ClassPathResource("static/javascript/receipt.js").getURL().toString();
        // String processedHtml = templateName
        //     .replace("@{/css/receipt.css}", cssPath)
        //     .replace("@{/javascript/receipt.js}", jsPath);
        String cssContent = new String(new ClassPathResource("static/css/receipt.css").getInputStream().readAllBytes());

        // Replace the <link> with inline CSS
        String processedHtml = templateName.replace(
            "<link rel=\"stylesheet\" th:href=\"@{/css/receipt.css}\" href=\"/css/receipt.css\">",
            "<style>" + cssContent + "</style>"
        );

        // Convert the rendered HTML to PDF
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            HtmlConverter.convertToPdf(processedHtml, out);
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error while generating PDF", e);
        }
    }
    
}

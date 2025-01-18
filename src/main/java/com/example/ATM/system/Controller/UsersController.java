package com.example.ATM.system.Controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.ATM.system.Services.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/atm")
public class UsersController {
    @Autowired 
    private UsersService usersService;
    private final SpringTemplateEngine templateEngine;

    public UsersController(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
    
    @GetMapping("/main/balance")
    public String CheckBalance(@RequestParam String AccountNumber, Model model) {
        Double balance = usersService.getBalance(AccountNumber);
        model.addAttribute("accountNumber", AccountNumber);
        model.addAttribute("balance", balance);
        return "check-balance";
    }
    @GetMapping("/main/withdraw")
    public String withdrawString() {
        return "Withdraw";
    }
    @PostMapping("/main/withdraw")
    public ResponseEntity<Map<String, String>> withdrawMoney(@RequestBody Map<String, Object> request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        double balance = usersService.getBalance(username);
        System.out.println("account number: "+username);
        String time = java.time.LocalDate.now().toString();
        double amount = Double.parseDouble(request.get("amount").toString());
        String transactionId = "TXN" + UUID.randomUUID().toString().substring(0, 8);
        Map<String, String> response = new HashMap<>();
        if(balance>amount && balance>0){
            response.put("status", "success");
        }
        else{
            response.put("status", "Failed! Insufficient Balance");
        }
        response.put("amount", String.valueOf(amount));
        response.put("accountNumber", username);
        response.put("transactionId", transactionId);
        response.put("transactionDate", time);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/main/withdraw/receipt")
    public String receiptPage(@RequestParam String status,@RequestParam String amount,
    @RequestParam String accountNumber,
    @RequestParam String transactionId,
    @RequestParam String transactionDate,Model model) {
        // System.out.println("Flash Attributes: " + model.asMap());
        model.addAttribute("status", status);
        model.addAttribute("amount", amount);
        model.addAttribute("accountNumber", accountNumber);
        model.addAttribute("transactionId", transactionId);
        model.addAttribute("transactionDate", transactionDate);
        return "receipt"; 
    }
    @GetMapping("/main/withdraw/receipt/download")
    public ResponseEntity<byte[]> downloadReceipt(@RequestParam String status,@RequestParam String amount,
    @RequestParam String accountNumber,
    @RequestParam String transactionId,
    @RequestParam String transactionDate) throws IOException{
        // byte[] pdfBytes = usersService.generateReceipt(accountNumber, amount, transactionDate, status, transactionId);
        Context context = new Context();
        context.setVariable("status", status);
        context.setVariable("amount", amount);
        context.setVariable("accountNumber", accountNumber);
        context.setVariable("transactionId", transactionId);
        context.setVariable("transactionDate", transactionDate);

        String htmlContent = templateEngine.process("receipt", context);
        // Render Thymeleaf template to HTML
        byte[] pdfBytes = usersService.generatePdfFromHtml(htmlContent);

        // Return the PDF as a downloadable response
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=receipt.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}

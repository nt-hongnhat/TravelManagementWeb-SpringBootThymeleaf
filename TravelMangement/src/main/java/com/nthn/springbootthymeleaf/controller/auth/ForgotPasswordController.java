package com.nthn.springbootthymeleaf.controller.auth;

import com.nthn.springbootthymeleaf.utils.Utility;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {
//    @Autowired
//    private JavaMailSender mailSender;
//    @Autowired
//    private AccountService accountService;
//
//    @GetMapping("/forgot-password")
//    public String showForgotPasswordForm() {
//        return "views/auth/forgot-password";
//    }
//
//    @PostMapping("/forgot-password")
//    public String processForgotPassword(HttpServletRequest request, Model model) {
//        String email = request.getParameter("email");
//        String token = RandomString.make(30);
//
//        try {
//            accountService.updateResetPasswordToken(token, email);
//            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
//            sendEmail(email, resetPasswordLink);
//            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
//
//        } catch (AccountNotFoundException ex) {
//            model.addAttribute("error", ex.getMessage());
//        } catch (UnsupportedEncodingException | MessagingException e) {
//            model.addAttribute("error", "Error while sending email");
//        }
//
//        return "views/auth/forgot-password";
//    }
//
//    public void sendEmail(String recipientEmail, String link)
//            throws MessagingException, UnsupportedEncodingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        helper.setFrom("hongnhat1121@gmail.com", "Shopme Support");
//        helper.setTo(recipientEmail);
//
//        String subject = "Here's the link to reset your password";
//
//        String content = "<p>Hello,</p>"
//                + "<p>You have requested to reset your password.</p>"
//                + "<p>Click the link below to change your password:</p>"
//                + "<p><a href=\"" + link + "\">Change my password</a></p>"
//                + "<br>"
//                + "<p>Ignore this email if you do remember your password, "
//                + "or you have not made the request.</p>";
//
//        helper.setSubject(subject);
//
//        helper.setText(content, true);
//
//        mailSender.send(message);
//    }
//
//
//    @GetMapping("/reset-password")
//    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
//        Account account = accountService.getByResetPasswordToken(token);
//        model.addAttribute("token", token);
//
//        if (account == null) {
//            model.addAttribute("message", "Invalid Token");
//            return "redirect:/reset-password?error";
//        }
//
//        return "views/auth/reset-password";
//    }
//
//    @PostMapping("/reset-password")
//    public String processResetPassword(HttpServletRequest request, Model model) {
//        String token = request.getParameter("token");
//        String password = request.getParameter("password");
//
//        Account account = accountService.getByResetPasswordToken(token);
//        model.addAttribute("title", "Reset your password");
//
//        if (account == null) {
//            model.addAttribute("message", "Invalid Token");
//            return "redirect:/reset-password?error";
//        } else {
//            accountService.updatePassword(account, password);
//
//            model.addAttribute("message", "You have successfully changed your password.");
//        }
//
//        return "redirect:/reset-password?error";
//    }
}

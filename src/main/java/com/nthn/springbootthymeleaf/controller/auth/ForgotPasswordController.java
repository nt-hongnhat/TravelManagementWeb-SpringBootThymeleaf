package com.nthn.springbootthymeleaf.controller.auth;

import com.nthn.springbootthymeleaf.constants.EndpointConstants;
import com.nthn.springbootthymeleaf.entity.Account;
import com.nthn.springbootthymeleaf.service.AccountService;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ForgotPasswordController {
	
	private final AccountService accountService;
	
	private final JavaMailSender mailSender;
	
	@GetMapping(EndpointConstants.FORGOT_PASSWORD)
	public String showForgotPasswordForm() {
		
		return "views/auth/forgot-password";
	}
	
	@PostMapping(EndpointConstants.FORGOT_PASSWORD)
	public String processForgotPassword(HttpServletRequest request, Model model) {
		
		final String email = request.getParameter(AttributeName.EMAIL);
		final String token = RandomString.make(30);
		
		try {
			accountService.updateResetPasswordToken(token, email);
			sendEmail(email, Utility.getSiteEndpointConstants(request) + "/reset_password?token=" + token);
			model.addAttribute(AttributeName.MESSAGE,
					"We have sent a reset password link to your email. Please check.");
		} catch (AccountNotFoundException ex) {
			model.addAttribute(AttributeName.ERROR, ex.getMessage());
		} catch (UnsupportedEncodingException | MessagingException e) {
			model.addAttribute(AttributeName.ERROR, "Error while sending email");
		}
		
		return "views/auth/forgot-password";
	}
	
	public void sendEmail(String recipientEmail, String link)
			throws MessagingException, UnsupportedEncodingException {
		
		final MimeMessage message = mailSender.createMimeMessage();
		final MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("hongnhat1121@gmail.com", "Shopme Support");
		helper.setTo(recipientEmail);
		helper.setSubject("Here's the link to reset your password");
		helper.setText("<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + link
				+ "\">Change my password</a></p>" + "<br>"
				+ "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>", true);
		
		mailSender.send(message);
	}
	
	@GetMapping(EndpointConstants.RESET_PASSWORD)
	public String showResetPasswordForm(@Param(value = AttributeName.TOKEN) String token, Model model) {
		
		final Account account = accountService.getByResetPasswordToken(token);
		model.addAttribute(AttributeName.TOKEN, token);
		
		if (Objects.isNull(account)) {
			model.addAttribute(AttributeName.MESSAGE, "Invalid Token");
			return "redirect:/reset-password?error";
		}
		
		return "views/auth/reset-password";
	}
	
	@PostMapping(EndpointConstants.RESET_PASSWORD)
	public String processResetPassword(HttpServletRequest request, Model model) {
		
		final Account account = accountService.getByResetPasswordToken(request.getParameter(AttributeName.TOKEN));
		model.addAttribute(AttributeName.TITLE, "Reset your password");
		
		if (Objects.isNull(account)) {
			model.addAttribute(AttributeName.MESSAGE, "Invalid Token");
			return "redirect:/reset-password?error";
		}
		accountService.updatePassword(account, request.getParameter(AttributeName.PASSWORD));
		model.addAttribute(AttributeName.MESSAGE, "You have successfully changed your password.");
		return "redirect:/reset-password?success";
		
	}
}

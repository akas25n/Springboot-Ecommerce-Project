package com.lot.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.lot.model.MailRequest;
import com.lot.model.MailResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service("emailService")
public class EmailService {

	
	private JavaMailSender mailSender;
	
	@Autowired
	private Configuration config;
	
	@Autowired
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Async
	public void sendEmail(SimpleMailMessage email) {
		mailSender.send(email);
	}
	
	//#####################################################################################################
	public MailResponse sendEmail_reg(MailRequest request, Map<String, Object> model) {
		
		MailResponse mailResponse = new MailResponse();
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			
			Template template = config.getTemplate("email-registration.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			
			helper.setTo(request.getTo());
			helper.setText(html,true);
			helper.setSubject(request.getSubject());
			helper.setFrom(request.getFrom());
			mailSender.send(message);
			
			
			mailResponse.setMessage("Mail sent to : " + request.getTo());
			mailResponse.setStatus(Boolean.TRUE);
			
		} catch (MessagingException | IOException | TemplateException e) {
			mailResponse.setMessage("Mail sending failure : " + e.getMessage());
			mailResponse.setStatus(Boolean.FALSE);
		}

		return mailResponse;

	}
	
	//#####################################################################################################
	public MailResponse sendEmail_confirm_order(MailRequest request, Map<String, Object> model) {
		
		MailResponse mailResponse = new MailResponse();
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			
			Template template = config.getTemplate("email-confirm-order_data.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			
			helper.setTo(request.getTo());
			helper.setText(html,true);
			helper.setSubject(request.getSubject());
			helper.setFrom(request.getFrom());
			mailSender.send(message);
			
			mailResponse.setMessage("Mail sent to : " + request.getTo());
			mailResponse.setStatus(Boolean.TRUE);
			
		} catch (MessagingException | IOException | TemplateException e) {
			mailResponse.setMessage("Mail sending failure : " + e.getMessage());
			mailResponse.setStatus(Boolean.FALSE);
		}
		
		
		return mailResponse;
			
		
	}
	
	//#####################################################################################################
	public MailResponse sendEmail_confirm_account(MailRequest request, Map<String, Object> model) {
		
		MailResponse mailResponse = new MailResponse();
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			
			Template template = config.getTemplate("confirm-account.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			
			helper.setTo(request.getTo());
			helper.setText(html,true);
			helper.setSubject(request.getSubject());
			helper.setFrom(request.getFrom());
			mailSender.send(message);
			
			mailResponse.setMessage("Mail sent to : " + request.getTo());
			mailResponse.setStatus(Boolean.TRUE);
			
		} catch (MessagingException | IOException | TemplateException e) {
			mailResponse.setMessage("Mail sending failure : " + e.getMessage());
			mailResponse.setStatus(Boolean.FALSE);
		}
		
		
		return mailResponse;
			
		
	}
	
	//#####################################################################################################
	public MailResponse sendEmailResetPass(MailRequest request, Map<String, Object> model) {
		
		MailResponse mailResponse = new MailResponse();
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			
			Template template = config.getTemplate("reset-password-link.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			
			helper.setTo(request.getTo());
			helper.setText(html,true);
			helper.setSubject(request.getSubject());
			helper.setFrom(request.getFrom());
			mailSender.send(message);
			
			
			mailResponse.setMessage("Mail sent to : " + request.getTo());
			mailResponse.setStatus(Boolean.TRUE);
			
		} catch (MessagingException | IOException | TemplateException e) {
			mailResponse.setMessage("Mail sending failure : " + e.getMessage());
			mailResponse.setStatus(Boolean.FALSE);
		}
		
		
		return mailResponse;
			
		
	}
	
}// end of service

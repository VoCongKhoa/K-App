package di.fa.kanotification.service;

import di.fa.kanotification.rabbitmq.queue.message.SendMailRegisterModuleMessage;
import di.fa.kanotification.repository.TemplateRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MailService {

    final JavaMailSender mailSender;

    final TemplateRepository templateRepository;

    public void sendMailRegisterModule(SendMailRegisterModuleMessage message, String templateCode) {
        var template = templateRepository.findByTemplateCode(templateCode).orElseThrow();
        var mimeMessage = mailSender.createMimeMessage();
        var subject = "Welcome to K Learning System!";
        var content = this.getEmailContent(
                template.getTemplateHtml(),
                String.format("%s %s", message.getFirstName(), message.getLastName()),
                message.getPassword());
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(message.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true); // Enable HTML content
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }

    private String getEmailContent(String templateHtml, String userName, String defaultPassword) {
        String resetPasswordLink = "https://yourdomain.com/reset-password"; // Replace with actual URL

        return templateHtml
                .replace("{{userName}}", userName)
                .replace("{{defaultPassword}}", defaultPassword)
                .replace("{{resetPasswordLink}}", resetPasswordLink)
                .replace("{{currentYear}}", String.valueOf(LocalDate.now().getYear()))
                ;
    }
}

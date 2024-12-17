package di.fa.kaauth.core;

import di.fa.kaauth.core.entity.UserEntity;
import di.fa.kaauth.core.repository.TemplateRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EmailService {

    final JavaMailSender mailSender;

    final TemplateRepository templateRepository;

    public void sendRegistrationEmail(UserEntity rootUser, String templateCode) {
        var template = templateRepository.findByTemplateCode(templateCode).orElseThrow();
        var message = mailSender.createMimeMessage();
        var subject = "Welcome to K Learning System!";
        var content = this.getEmailContent(
                template.getTemplateHtml(),
                String.format("%s %s", rootUser.getFirstName(), rootUser.getLastName()),
                rootUser.getPassword());
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(rootUser.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true); // Enable HTML content
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
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

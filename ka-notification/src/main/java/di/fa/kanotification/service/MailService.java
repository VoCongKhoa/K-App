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
//        var template = templateRepository.findByTemplateCode(templateCode).orElseThrow();
        var mimeMessage = mailSender.createMimeMessage();
        var subject = "Welcome to K Learning System!";
        var content = """
                <!DOCTYPE html>
                <html lang="en">
                                
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Register Module</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            margin: 0;
                            padding: 0;
                            background-color: #f4f4f4;
                        }
                                
                        .email-container {
                            max-width: 600px;
                            margin: 20px auto;
                            background-color: #ffffff;
                            border: 1px solid #dddddd;
                            border-radius: 8px;
                            padding: 20px;
                        }
                                
                        .header {
                            text-align: center;
                            padding-bottom: 20px;
                        }
                                
                        .header img {
                            max-width: 150px;
                            margin-bottom: 10px;
                        }
                                
                        .header .slogan {
                            font-size: 16px;
                            color: #666666;
                        }
                                
                        .content {
                            font-size: 14px;
                            line-height: 1.6;
                            color: #333333;
                        }
                                
                        .content h1 {
                            font-size: 18px;
                            color: #333333;
                            text-align: center;
                        }
                                
                        .footer {
                            text-align: center;
                            margin-top: 20px;
                            font-size: 12px;
                            color: #999999;
                        }
                                
                        .btn {
                            display: inline-block;
                            background-color: #007bff;
                            color: #ffffff;
                            padding: 10px 20px;
                            border-radius: 4px;
                            text-decoration: none;
                            font-weight: bold;
                        }
                    </style>
                </head>
                                
                <body>
                    <div class="email-container">
                        <!-- Header -->
                        <div class="header">
                            <img src="https://via.placeholder.com/150" alt="Company Logo"> <!-- Replace with your company logo URL -->
                            <div class="slogan">K-Learning System</div>
                        </div>
                                
                        <!-- Content -->
                        <div class="content">
                            <h1>Welcome! Your Registration Was Successful</h1>
                            <p>Dear <b>{{userName}}</b>,</p>
                            <p>Thank you for registering an account with our system. Below are your login details:</p>
                            <ul>
                                <li><b>Username:</b> {{userName}}</li>
                                <li><b>Password:</b> {{defaultPassword}}</li>
                            </ul>
                            <p><b>Note:</b> For security reasons, you are required to change your password during your first login.</p>
                            <p>
                                To reset your password, click <a href="{{resetPasswordLink}}" class="btn">Reset Password</a>.
                            </p>
                        </div>
                                
                        <!-- Footer -->
                        <div class="footer">
                            &copy; {{currentYear}} K-Learning System. All rights reserved.
                        </div>
                    </div>
                </body>
                                
                </html>
                """;
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

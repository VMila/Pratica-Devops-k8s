package service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailService {

    public static void sendWelcomeEmail(String to) {
         System.getLogger("emdiajd").log(System.Logger.Level.INFO, "dentro doe mail");
        Properties props = new Properties();
        props.put("mail.smtp.host", System.getenv("MAIL_HOST")); 
        props.put("mail.smtp.port", "1025"); 

        Session session = Session.getInstance(props);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("no-reply@suaapp.com"));
            message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(to)
            );
            message.setSubject("Bem-vindo!");
            message.setText("Obrigado por se cadastrar em nossa plataforma!");

            Transport.send(message);
            System.out.println("E-mail enviado com sucesso para " + to);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
package popr.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import popr.model.Provider;
import popr.model.ServiceOrder;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void sendEmail(String mailContent, String providerEmail) {

        MimeMessagePreparator preparator = getMessagePreparator(mailContent, providerEmail);

        try {
            mailSender.send(preparator);
            System.out.println("Message Send");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private MimeMessagePreparator getMessagePreparator(String mailContent, String providerEmail) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom("obslugaawarii231@gmail.com");
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(providerEmail));
                mimeMessage.setText(mailContent);
                mimeMessage.setSubject("Przypisano zlecenie");
            }
        };
        return preparator;
    }

}

package com.caddy.erasxchange.services;

import com.caddy.erasxchange.models.Event;
import com.caddy.erasxchange.models.users.User;
import com.caddy.erasxchange.repositories.user.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.Instant;

@Service
public class EventService {
    private final UserRepository<User> userRepository;
    private final JavaMailSender mailSender;

    public EventService(UserRepository<User> userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }


    public void sendEvent(User user, String title, String desc) throws MessagingException, UnsupportedEncodingException {
        user.getEvents().add(new Event(Instant.now(), title, desc));
        sendMail(user, user.getEvents().get(user.getEvents().size()-1));
        userRepository.save(user);
    }

    private void sendMail(User user, Event event) throws MessagingException, UnsupportedEncodingException {
        if (user.getEmail() == null || user.getEmail().endsWith("@example.com"))
            return;

        String toAddress = user.getEmail();
        String fromAddress = "ceren.akyar@ug.bilkent.edu.tr";
        String senderName = "ErasXchange";
        String subject = event.getTitle();
        String content = "Dear [[name]],<br>"
                + event.getContents();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFirstName() + " " + user.getLastName());

        helper.setText(content, true);

        mailSender.send(message);
    }
}

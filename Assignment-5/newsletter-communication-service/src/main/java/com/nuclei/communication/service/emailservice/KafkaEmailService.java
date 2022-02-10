package com.nuclei.communication.service.emailservice;

import com.nuclei.communication.constants.KafkaConstantsUtils;
import com.nuclei.communication.dto.request.EmailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
@Component
public class KafkaEmailService {
  
  @Value("${spring.mail.username}")
  private String mailUsername;
  
  @Value("${spring.mail.password}")
  private String mailPassword;
  
  @Value("${spring.mail.host}")
  private String mailHost;
  
  @Value("${spring.mail.port}")
  private int mailPort;
  
  @KafkaListener(
      topics = KafkaConstantsUtils.KAFKA_OTP_TOPIC_NAME,
      groupId = KafkaConstantsUtils.KAFKA_EMAIL_GROUP_NAME
  )
  void otpListener(EmailDto emailDto) {
    sendMail(emailDto);
  }
  
  @KafkaListener(
      topics = KafkaConstantsUtils.KAFKA_NOTIFICATION_TOPIC_NAME,
      groupId = KafkaConstantsUtils.KAFKA_EMAIL_GROUP_NAME
  )
  void notificationListener(EmailDto emailDto) {
    sendMail(emailDto);
  }
  
  @KafkaListener(
      topics = KafkaConstantsUtils.KAFKA_SUBSCRIPTION_TOPIC_NAME,
      groupId = KafkaConstantsUtils.KAFKA_EMAIL_GROUP_NAME
  )
  void subscriptionListener(EmailDto emailDto) {
    sendMail(emailDto);
  }
  
  private void sendMail(EmailDto emailDto) {
    
    Properties prop = new Properties();
    prop.put("mail.smtp.host", mailHost);
    prop.put("mail.smtp.port", mailPort);
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.ssl.trust", mailHost);
    prop.put("mail.smtp.socketFactory.port", "465");
    prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    prop.put("mail.transport.protocol", "smtp");
    prop.put("mail.smtp.starttls.enable", "true");
    
    Session session = Session.getInstance(prop,
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(mailUsername, mailPassword);
          }
        });
    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(mailUsername));
      message.setRecipients(
          Message.RecipientType.TO,
          InternetAddress.parse(emailDto.getEmailId())
      );
      message.setSubject(emailDto.getSubject());
      message.setText(emailDto.getBody());
  
      Transport.send(message);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
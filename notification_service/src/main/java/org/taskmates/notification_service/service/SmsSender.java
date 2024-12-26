package org.taskmates.notification_service.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SmsSender {

    @Value("${sms.accountSid}")
    private static String ACCOUNT_SID;

    @Value("${sms.authToken}")
    private static String AUTH_TOKEN;

    @Value("${sms.fromNumber}")
    private static String FROM_NUMBER;

    @PostConstruct
    public void init() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }


    @KafkaListener(topics = "send-notification-sms", groupId = "notification-service")
    public static void sendSms(String phoneNumber, String message) {
        Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(FROM_NUMBER),
                message
        ).create();
    }


}

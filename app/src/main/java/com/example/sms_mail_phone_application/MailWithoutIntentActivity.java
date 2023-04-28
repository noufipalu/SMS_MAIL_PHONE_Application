package com.example.sms_mail_phone_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailWithoutIntentActivity extends AppCompatActivity {

    EditText to, sub, body;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_without_intent);

        to = findViewById(R.id.toEmail);
        sub = findViewById(R.id.subject);
        body = findViewById(R.id.body);
        send = findViewById(R.id.sendEmail);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String senderEmail = "noufiyanazrinpn@gmail.com";
                    String password = "Noufiya@8714";
                    String receiverEmail = to.getText().toString();
                    String subject = sub.getText().toString();
                    String Body = body.getText().toString();

                    String host = "smtp.gmail.com";

                    Properties properties = System.getProperties();
                    properties.put("mail.smtp.host", host);
                    properties.put("mail.smtp.port", "465");
                    properties.put("mail.smtp.ssl.enable", "true");
                    properties.put("mail.smtp.auth", "true");

                    Session session = Session.getInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(senderEmail, password);
                        }
                    });

                    MimeMessage mimeMessage = new MimeMessage(session);
                    mimeMessage.addRecipients(Message.RecipientType.TO, new InternetAddress(receiverEmail).toString());

                    mimeMessage.setSubject(subject);
                    mimeMessage.setText(Body);

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Transport.send(mimeMessage);
                            }catch (MessagingException e){
                                e.printStackTrace();
                            }
                        }
                    });

                    thread.start();

                }catch (AddressException e){
                    e.printStackTrace();
                }catch (MessagingException me){
                    me.printStackTrace();
                }
            }
        });
    }
}
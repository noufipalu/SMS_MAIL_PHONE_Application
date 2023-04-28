package com.example.sms_mail_phone_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MailWithIntentActivity extends AppCompatActivity {

    EditText to, sub, msg;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_with_intent);

        to = findViewById(R.id.to);
        sub = findViewById(R.id.sub);
        msg = findViewById(R.id.msgs);
        send = findViewById(R.id.sendMsg);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
                to.setText("");
                sub.setText("");
                msg.setText("");
            }
        });
    }

    private void sendMail() {
        String To = to.getText().toString();
        String[] recipients = To.split(",");
        String Sub = sub.getText().toString();
        String body = msg.getText().toString();

        Intent intentMail = new Intent(Intent.ACTION_SEND);
        intentMail.putExtra(Intent.EXTRA_EMAIL, recipients);
        intentMail.putExtra(Intent.EXTRA_SUBJECT, Sub);
        intentMail.putExtra(Intent.EXTRA_TEXT, body);

        intentMail.setType("message/rfc822");

        startActivity(Intent.createChooser(intentMail, "Choose a client"));
    }
}
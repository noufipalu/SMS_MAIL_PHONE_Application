package com.example.sms_mail_phone_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CallActivity extends AppCompatActivity {

    static int PERMISSION_CODE = 100;
    EditText number;
    Button call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        
        number = findViewById(R.id.numb);
        call = findViewById(R.id.call);
        
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentcCall = new Intent(Intent.ACTION_DIAL);
                String phone = number.getText().toString();

                if (phone.trim().isEmpty()){
                    Toast.makeText(CallActivity.this, "Enter Your Mobile Number", Toast.LENGTH_SHORT).show();
                }else {
                    intentcCall.setData(Uri.parse("tel:" + phone));
                }

                if (ActivityCompat.checkSelfPermission(CallActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    requestPermission();
                    Toast.makeText(CallActivity.this, "Please Grant Permission", Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(intentcCall);
                }
            }
        });
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(CallActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
    }
}
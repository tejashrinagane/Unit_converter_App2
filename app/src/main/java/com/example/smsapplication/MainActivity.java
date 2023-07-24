package com.example.smsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText phone, message;
    Button Send;

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();
        eventHandler();

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            requestSmsPermission();
        } else {
            //grant ,yuo can start your task heere
            eventHandler();
        }
    }

        protected void initialization () {
        phone = findViewById(R.id.editTextPhone);
        message = findViewById(R.id.editTextMessage);
        Send = findViewById(R.id.buttonSend);
    }


    protected void eventHandler() {
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mob = phone.getText().toString().trim();
                String msg = message.getText().toString().trim();
                if (mob.isEmpty() || msg.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields can't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(mob, null, msg, null, null);
                }
                phone.setText(null);
                message.setText(null);

            }
        });
    }

    private void requestSmsPermission() {
        String permission= Manifest.permission.SEND_SMS;
        int grant= ContextCompat.checkSelfPermission(this,permission);
        if(grant!= PackageManager.PERMISSION_GRANTED){
            String[] permission_list=new String[1];
            permission_list[0]=permission;
            ActivityCompat.requestPermissions(this,permission_list,1);
        }
    }
}
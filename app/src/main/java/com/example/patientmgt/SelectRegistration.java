package com.example.patientmgt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SelectRegistration extends AppCompatActivity {
    private TextView patsignup, doctorsignup;

    private TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_registration);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SelectRegistration.this, login.class);
                startActivity(intent);
            }
        });

        patsignup = findViewById(R.id.patsignup);
        doctorsignup = findViewById(R.id.doctorsignup);

        patsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectRegistration.this, patientsignup.class);
                startActivity(intent);
            }
        });

        doctorsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectRegistration.this, docsignup.class);
                startActivity(intent);
            }
        });

    }
}
package com.example.patientmgt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class docsignup extends AppCompatActivity {
    private TextView signupQuestion;

    private TextInputEditText dregfullname, dregempnumber, dregphonenumber, dloginEmail, dloginPassword;
    private Spinner availabilitySpinner, departmentSpinner, specializationSpinner;
    private Button dsignupButton;

    private FirebaseAuth mAuth;
    private DatabaseReference userDatabaseRef;
    private ProgressDialog loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docsignup);

        signupQuestion = findViewById(R.id.signupQuestion);
        signupQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(docsignup.this, login.class);
                startActivity(intent);
            }
        });

        dregfullname = findViewById(R.id.dregfullname);
        dregempnumber = findViewById(R.id.dregempnumber);
        dregphonenumber = findViewById(R.id.dregphonenumber);
        dloginEmail = findViewById(R.id.dloginEmail);
        dloginPassword = findViewById(R.id.dloginPassword);
        availabilitySpinner = findViewById(R.id.availabilitySpinner);
        departmentSpinner = findViewById(R.id.departmentSpinner);
        specializationSpinner = findViewById(R.id.specializationSpinner);
        dsignupButton = findViewById(R.id.dsignupButton);

        loader = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        
        dsignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String demail = dloginEmail.getText().toString().trim();
                final String dfullname = dregfullname.getText().toString().trim();
                final String dempnumber = dregempnumber.getText().toString().trim();
                final String dphonenumber = dregphonenumber.getText().toString().trim();
                final String dpassword = dloginPassword.getText().toString().trim();
                final String dtimeavaliabe = availabilitySpinner.toString().trim();
                final String department = departmentSpinner.toString().trim();
                final String specialization = specializationSpinner.toString().trim();


                if (TextUtils.isEmpty(demail )){
                    dloginEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(dpassword)){
                    dloginPassword.setError("Password is required");
                    return;
                }
                if (TextUtils.isEmpty(dfullname)){
                    dregfullname.setError("Name is required");
                    return;
                }
                if (TextUtils.isEmpty(dempnumber)){
                    dregempnumber.setError("Idnumber required");
                    return;
                }
                if (TextUtils.isEmpty(dphonenumber)){
                    dregphonenumber.setError("phonenumber is required");
                    return;
                }
                else {
                    loader.setMessage("Registration in progress...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    mAuth.createUserWithEmailAndPassword(demail,dpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                doctor doc = new doctor(demail, dfullname, dempnumber, dphonenumber, dtimeavaliabe, department, specialization);

                                FirebaseDatabase.getInstance().getReference("Doctors")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(docsignup.this, "Doctor Successfully Registered", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(docsignup.this, "Failed to register Doctor", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                    Intent intent = new Intent(docsignup.this,
                            login.class);
                    startActivity(intent);
                    finish();

                    loader.dismiss();
                }
            }
        });


    }
}
package com.example.patientmgt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class patientsignup extends AppCompatActivity {

    private TextView loginQuestion;

    private TextInputEditText regfullname, regidnumber, regphonenumber, loginEmail, loginPassword;
    private Button signupButton;
    private ImageView profilepic;

    private FirebaseAuth mAuth;
    private DatabaseReference userDatabaseRef;
    private ProgressDialog loader;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientsignup);
        mAuth = FirebaseAuth.getInstance();

        fStore = FirebaseFirestore.getInstance();
        loginQuestion = findViewById(R.id.loginQuestion);
        loginQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(patientsignup.this, login.class);
            }
        });

        regfullname = findViewById(R.id.regfullname);
        regidnumber = findViewById(R.id.regidnumber);
        regphonenumber = findViewById(R.id.regphonenumber);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        signupButton = findViewById(R.id.signupButton);
        profilepic = findViewById(R.id.profilepic);

        loader = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = loginEmail.getText().toString().trim();
                final String password = loginPassword.getText().toString().trim();
                final String fullname = regfullname.getText().toString().trim();
                final String idnumber = regidnumber.getText().toString().trim();
                final String phonenumber = regphonenumber.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    loginEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    loginPassword.setError("Password is required");
                    return;
                }
                if (TextUtils.isEmpty(fullname)){
                    regfullname.setError("Name is required");
                    return;
                }
                if (TextUtils.isEmpty(idnumber)){
                    regidnumber.setError("Idnumber required");
                    return;
                }
                if (TextUtils.isEmpty(phonenumber)){
                    regphonenumber.setError("phonenumber is required");
                    return;
                }
                else {
                    loader.setMessage("Registration in progress...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                patient pat = new patient(email, fullname, idnumber, phonenumber);

                                FirebaseDatabase.getInstance().getReference("Patients")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(pat).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                                    Toast.makeText(patientsignup.this, "Patient Successfully Registered", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                    DocumentReference df = fStore.collection("Users").document(currentUser.getUid());
                                                    HashMap<String, Object> UserInfo = new HashMap<>();
                                                    UserInfo.put("Email", loginEmail.getText().toString());
                                                    UserInfo.put("Password", loginPassword.getText().toString());
                                                    UserInfo.put("Full Name", regfullname.getText().toString());

                                                    df.set(UserInfo);
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(patientsignup.this, "Failed to register Patient", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


//                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//
//                            if (!task.isSuccessful()){
//                                String error = task.getException().toString();
//                                Toast.makeText(patientsignup.this,
//                                        "Error Occured:" + error, Toast.LENGTH_LONG).show();
//
//                            }
//                            else {
//                                String currentUserId = mAuth.getCurrentUser().getUid();
//                                userDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
//
//                                //create a hashmap to capture all the details that the user input and save them to firebase
//                                HashMap userinfo = new HashMap();
//                                userinfo.put("name", fullname);
//                                userinfo.put("email", email);
//                                userinfo.put("idnumber", idnumber);
//                                userinfo.put("phonenumber", phonenumber);
//                                userinfo.put("type", "patient");
//
//                                userDatabaseRef.updateChildren(userinfo).addOnCompleteListener(new OnCompleteListener() {
//                                    @Override
//                                    public void onComplete(@NonNull Task task) {
//                                     if (task.isSuccessful()){
//                                         Toast.makeText(patientsignup.this,
//                                                 "Details set successfully", Toast.LENGTH_LONG).show();
//                                     } else {
//                                         Toast.makeText(patientsignup.this,
//                                                 task.getException().toString(), Toast.LENGTH_LONG).show();
//                                     }
//                                     finish();
//                                     loader.dismiss();
//                                    }
//                                });
//                            }
//                        }
//                    });
                    Intent intent = new Intent(patientsignup.this,
                            login.class);
                    startActivity(intent);
                    finish();

                    loader.dismiss();
                }

            }
        });

    }
}
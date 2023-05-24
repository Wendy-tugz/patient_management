package com.example.patientmgt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

public class register extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    private TextInputEditText personalname, personalid, personalnumber, personalEmail, insuarancename, maritalstatus, age, kids, allergies, medication, kinnumber;
    private Button registerButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        personalname = findViewById(R.id.personalname);
        personalid = findViewById(R.id.personalid);
        personalnumber = findViewById(R.id.personalnumber);
        personalEmail = findViewById(R.id.personalEmail);
        insuarancename = findViewById(R.id.insuarancename);
        maritalstatus = findViewById(R.id.maritalstatus);
        age = findViewById(R.id.age);
        kids = findViewById(R.id.kids);
        allergies = findViewById(R.id.allergies);
        medication = findViewById(R.id.medication);
        kinnumber = findViewById(R.id.kinnumber);
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);


    }
    private void registerUser() {
        String mail = Objects.requireNonNull(personalEmail.getText()).toString().trim();
        String name = Objects.requireNonNull(personalname.getText()).toString().trim();
        String identification = Objects.requireNonNull(personalid.getText()).toString().trim();
        String insurance = Objects.requireNonNull(insuarancename.getText()).toString().trim();
        String phone = Objects.requireNonNull(personalnumber.getText()).toString().trim();
        String maritalStatus = Objects.requireNonNull(maritalstatus.getText()).toString().trim();
        String patientAge = Objects.requireNonNull(age.getText()).toString().trim();
        String kinNumber = Objects.requireNonNull(kinnumber.getText()).toString().trim();
        String kidsNumber = Objects.requireNonNull(kids.getText()).toString().trim();
        String patientAllergies = Objects.requireNonNull(allergies.getText()).toString().trim();
        String patientMedication = Objects.requireNonNull(medication.getText()).toString().trim();

        if (mail.isEmpty()) {
            personalEmail.setError("Email required");
            personalEmail.requestFocus();
            return;
        }
        if (name.isEmpty()) {
            personalname.setError("first name required");
            personalname.requestFocus();
            return;
        }
        if (identification.isEmpty()) {
            personalid.setError("last name required");
            personalid.requestFocus();
            return;
        }
        if (insurance.isEmpty()) {
            insuarancename.setError("location required");
            insuarancename.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            personalnumber.setError("Phone number required");
            personalnumber.requestFocus();
            return;
        }
        if (maritalStatus.isEmpty()) {
            maritalstatus.setError("the password cannot be empty");
            maritalstatus.requestFocus();
            return;
        }
        if (patientAge.isEmpty()) {
            age.setError("the password cannot be empty");
            age.requestFocus();
            return;
        }
        if (kinNumber.isEmpty()) {
            kinnumber.setError("the password cannot be empty");
            kinnumber.requestFocus();
            return;
        }
        if (kidsNumber.isEmpty()) {
            kids.setError("the password cannot be empty");
            kids.requestFocus();
            return;
        }
        if (patientAllergies.isEmpty()) {
            allergies.setError("the password cannot be empty");
            allergies.requestFocus();
            return;
        }
        if (patientMedication.isEmpty()) {
            medication.setError("the password cannot be empty");
            medication.requestFocus();
            return;
        }
        HashMap <String, String> personalInfo = (HashMap<String, String>) new Personalinfo(mail, name, identification, insurance, phone, maritalStatus, patientAge,kinNumber,kidsNumber,patientAllergies, patientMedication).createHashmap();
        saveData(personalInfo);
    }

    @Override
    public void onClick(View v) {
        registerUser();
        Intent intent= new Intent(register.this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(register.this, "Personal Info Successfully Registered", Toast.LENGTH_SHORT).show();
    }



    public void saveData(HashMap<String, String> information){
        FirebaseUser rUser = mAuth.getCurrentUser();
        assert rUser != null;
        String userId = rUser.getUid();
        fStore.collection("PatientsInfo").document(userId).set(information).addOnCompleteListener(task -> Log.d("TAG",userId + "'s Data saved successfully"));
    }

}
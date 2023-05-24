package com.example.patientmgt;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.CollationElementIterator;

public class User {
    String mail, name, identification, insurance, phone, maritalStatus, patientAge, kinNumber, kidsNumber, patientAllergies, patientMedication;


    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getIdentification() {
        return identification;
    }

    public String getInsurance() {
        return insurance;
    }

    public String getPhone() {
        return phone;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public String getKinNumber() {
        return kinNumber;
    }

    public String getKidsNumber() {
        return kidsNumber;
    }

    public String getPatientAllergies() {
        return patientAllergies;
    }

    public String getPatientMedication() {
        return patientMedication;
    }
}

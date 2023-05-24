package com.example.patientmgt;

import java.util.HashMap;
import java.util.Map;

public class Personalinfo{
    String mail;
    String name ;
    String identification ;
    String insurance;
    String phone;
    String maritalStatus;
    String patientAge;
    String kinNumber;
    String kidsNumber;
    String patientAllergies;
    String patientMedication;
    private final Map<String, String> personalInfo = new HashMap<>();

    public Personalinfo(String mail,
    String name ,
    String identification ,
    String insurance,
    String phone,
    String maritalStatus,
    String patientAge,
    String kinNumber,
    String kidsNumber,
    String patientAllergies,
    String patientMedication) {
        this.name = name;
        this.mail = mail;
        this.identification = identification;
        this.insurance = insurance;
        this.phone = phone;
        this.maritalStatus = maritalStatus;
        this.patientAge = patientAge;
        this.kinNumber = kinNumber;
        this.kidsNumber = kidsNumber;
        this.patientAllergies = patientAllergies;
        this.patientMedication = patientMedication;
    }

    public Map<String, String> createHashmap(){
        personalInfo.put("name", this.name);
        personalInfo.put("mail", this.mail);
        personalInfo.put("id", this.identification);
        personalInfo.put("insurance", this.insurance);
        personalInfo.put("phone number", this.phone);
        personalInfo.put("marital status", this.maritalStatus);
        personalInfo.put("Age", this.patientAge);
        personalInfo.put("Kids", this.kidsNumber);
        personalInfo.put("Next of kin's number", this.kinNumber);
        personalInfo.put("Allergies", this.patientAllergies);
        personalInfo.put("Medication", this.patientMedication);
        return personalInfo;
    }

}

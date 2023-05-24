package com.example.patientmgt;

import com.google.android.material.textfield.TextInputEditText;

public class patient {
    public String pfullname;
    public String pemail;
    public String pidnumber;
    public  String pphonenumber;

    public patient(String pfullname, String pemail, String pidnumber, String pphonenumber) {
        this.pfullname = pfullname;
        this.pemail = pemail;
        this.pidnumber = pidnumber;
        this.pphonenumber = pphonenumber;
    }

    public patient() {

    }
}


package com.example.patientmgt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class appointments extends AppCompatActivity {
    CalendarView calenderview;
    TextView myDate;
    FirebaseDatabase database;

    private TextInputEditText appfullname, appnumber;
    private Button bookButton;
    private TimePickerDialog timePickerDialog;
    private Button buttontime;

    FirebaseFirestore fStore;

    private FirebaseAuth mAuth;
//    private DatabaseReference userDatabaseRef;
//    private ProgressDialog loader;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        appfullname = findViewById(R.id.appfullname);
        appnumber = findViewById(R.id.appfullname);
        bookButton = findViewById(R.id.bookButton);
        buttontime = findViewById(R.id.buttontime);
        database=FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        calenderview = (CalendarView) findViewById(R.id.calenderview);
        myDate = (TextView) findViewById(R.id.myDate);

        //time picker
        initTimePicker();
        buttontime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timePickerDialog.show();
            }
        });


//        loader = new ProgressDialog(this);
//        mAuth = FirebaseAuth.getInstance();

        calenderview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                myDate.setText(date);

            }
        });


        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                final String bname = appfullname.getText().toString().trim();
//                final String bnumber = appnumber.getText().toString().trim();
//                final String calender = String.valueOf(calenderview.getDateTextAppearance());
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                HashMap<String, Object> UserInfo = new HashMap<>();
                UserInfo.put("Date",myDate.getText().toString());
                DocumentReference df = fStore.collection("PatientsInfo").document(currentUser.getUid());
                df.update("Date", myDate.getText().toString());
                df.update(UserInfo);

                Intent intent= new Intent(appointments.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(appointments.this, "Patient Appointment Booked", Toast.LENGTH_SHORT).show();


            }
        });

    }
    //time picker
    public void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                buttontime.setText(hourOfDay+":"+minute);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);
    }




}

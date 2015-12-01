package com.example.shojib.project_moon.Doctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.shojib.project_moon.R;

public class DisplayDoctors extends Activity {

    private DoctorDataBaseQuery doctorDataBaseQuery;
    private DoctorModule doctorModule;

    private EditText doctorName;
    private EditText dr_phoneNumber;
    private EditText dr_address;
    private EditText appointment_date;
    private EditText appointment_time;
    //private CheckBox setreminder;


    private EditText dr_Specialty;

    private long pID=1;
    private long dID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_profile);


        doctorName = (EditText) findViewById(R.id.doctorNameEditText);
        dr_phoneNumber = (EditText) findViewById(R.id.dr_phoneEditText);
        dr_address = (EditText) findViewById(R.id.dr_AddressEditText);
        dr_Specialty = (EditText) findViewById(R.id.SpecialtyEditText);
        appointment_date =(EditText)findViewById(R.id.Appointment_Date);
        appointment_time =(EditText)findViewById(R.id.Appointment_Time);
        //setreminder = (CheckBox)findViewById(R.id.appiontment_reminder);


       Button saveDoctorsButton = (Button)findViewById(R.id.saveDoctorButton);

        doctorDataBaseQuery = new DoctorDataBaseQuery(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dID = Long.parseLong(getIntent().getStringExtra("did"));
        DoctorModule doctorModule  = doctorDataBaseQuery.getSingleDoctorById(dID);

            String dName = doctorModule.getDoctorName();
            String dPhone = doctorModule.getPhone();
            String dAddress = doctorModule.getAddress();
            String dSpecialty = doctorModule.getSpeciality();
            String dAppointmentDate = doctorModule.getAppointmentDate();
            String dAppointmentTime = doctorModule.getAppointmentTime();
           // String setreminder1 = doctorModule.getReminder();

            pID = doctorModule.getUserId();

            doctorName.setText(dName);
            dr_phoneNumber.setText(dPhone);
            dr_address.setText(dAddress);
            dr_Specialty.setText(dSpecialty);
            appointment_date.setText(dAppointmentDate);
            appointment_time.setText(dAppointmentTime);
            //setreminder.setText(setreminder1);


           saveDoctorsButton = (Button) findViewById(R.id.saveDoctorButton);


            saveDoctorsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Editable dName1 = doctorName.getText();
                    final Editable dPhone1 = dr_phoneNumber.getText();
                    final Editable dAddress1 = dr_address.getText();
                    final Editable dSpecialty1 = dr_Specialty.getText();
                    final Editable dAppointmentDate1 = appointment_date.getText();
                    final Editable dAppointmentTime1 = appointment_time.getText();
                    //final Editable setreminder2 = dr_address.getText();

                    try {
                        if (!TextUtils.isEmpty(dName1) && !TextUtils.isEmpty(dSpecialty1) && !TextUtils.isEmpty(dPhone1) && !TextUtils.isEmpty(dAddress1) && !TextUtils.isEmpty(dAppointmentDate1)&& !TextUtils.isEmpty(dAppointmentTime1)) {

                            doctorDataBaseQuery.updateDoctorByDoctorById(dID, dName1.toString(), dSpecialty1.toString(), dPhone1.toString(), dAddress1.toString(), dAppointmentDate1.toString(), dAppointmentTime1.toString(),pID);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "You Must Fill all Fields!", Toast.LENGTH_LONG).show();

                        }

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "You Must Fill all Fields!", Toast.LENGTH_LONG).show();
                    }
               }
            });
        }
    }
}

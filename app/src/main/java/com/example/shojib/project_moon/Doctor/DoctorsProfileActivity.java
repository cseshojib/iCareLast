package com.example.shojib.project_moon.Doctor;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.shojib.project_moon.CustomOnItemSelectedListener;
import com.example.shojib.project_moon.R;


public class DoctorsProfileActivity extends Activity {

    EditText doctorName;
    EditText dr_phoneNumber;
    EditText dr_address;
    private Spinner dr_specialtySpinner1;

    String Name;
    String phoneNumber;
    String address;
    private Button saveDoctorsButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_profile);

        doctorName = (EditText) findViewById(R.id.doctorNameEditText);
        dr_phoneNumber = (EditText) findViewById(R.id.dr_phoneEditText);
        dr_address = (EditText) findViewById(R.id.dr_AddressEditText);

        saveDoctorsButton = (Button)findViewById(R.id.saveDoctorButton);

        saveDoctorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name = doctorName.getText().toString();
                phoneNumber = dr_phoneNumber.getText().toString();
                address = dr_address.getText().toString();





            }
        });


    }




//spinner code start

    public void addListenerOnSpinnerItemSelection() {
        dr_specialtySpinner1  = (Spinner) findViewById(R.id.dr_specialtySpinner1);
        dr_specialtySpinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        dr_specialtySpinner1 = (Spinner) findViewById(R.id.dr_specialtySpinner1);

        saveDoctorsButton = (Button) findViewById(R.id.saveDoctorButton);


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();
        return true;
    }


}
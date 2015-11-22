package com.example.shojib.project_moon.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shojib.project_moon.CustomOnItemSelectedListener;
import com.example.shojib.project_moon.DBHelper;
import com.example.shojib.project_moon.R;


public class DoctorsProfileActivity extends Activity {

    EditText doctorName;
    EditText dr_phoneNumber;
    EditText dr_address;
    private Spinner dr_specialtySpinner1;

    String Name;
    String phoneNumber;
    String address;
    private Button button_Save_Doctors;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_profile);

        doctorName = (EditText) findViewById(R.id.doctorNameEditText);
        dr_phoneNumber = (EditText) findViewById(R.id.dr_phoneEditText);
        dr_address = (EditText) findViewById(R.id.dr_AddressEditText);


        Name = doctorName.getText().toString();
        phoneNumber = dr_phoneNumber.getText().toString();
        address = dr_address.getText().toString();

    }




//spinner code start

    public void addListenerOnSpinnerItemSelection() {
        dr_specialtySpinner1  = (Spinner) findViewById(R.id.dr_specialtySpinner1);
        dr_specialtySpinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        dr_specialtySpinner1 = (Spinner) findViewById(R.id.dr_specialtySpinner1);

        button_Save_Doctors = (Button) findViewById(R.id.button_Save_Doctors);


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();
        return true;
    }


}
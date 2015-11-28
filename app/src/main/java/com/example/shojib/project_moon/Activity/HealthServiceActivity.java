package com.example.shojib.project_moon.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.shojib.project_moon.Doctor.DoctorsActivity;
import com.example.shojib.project_moon.Medication.Medicine.MedicationActivity;
import com.example.shojib.project_moon.R;
import com.example.shojib.project_moon.Vaccine.Vaccination.VaccinationActivity;


public class HealthServiceActivity extends Activity {



    Button health;
    Button doctors;
    Button medicine;
    Button vaccination;
    Button diet;
    Button hospital;
    Button exit;
long pID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_service);


        health = (Button) findViewById(R.id.healthButton);
        doctors = (Button) findViewById(R.id.doctorButton);
        medicine = (Button) findViewById(R.id.medicineButton);
        vaccination= (Button) findViewById(R.id.vaccinationButton);
        diet = (Button) findViewById(R.id.dietButton);
        hospital = (Button) findViewById(R.id.hospitalButton);
        exit = (Button) findViewById(R.id.exitButton);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pID = Long.parseLong(getIntent().getStringExtra("pid"));
        }

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthServiceActivity.this, RbsActivity.class);
                intent.putExtra("pid",String.valueOf(pID));
                startActivity(intent);

            }
        });
        doctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthServiceActivity.this, DoctorsActivity.class);
                intent.putExtra("pid",String.valueOf(pID));
                startActivity(intent);

            }
        });

        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthServiceActivity.this, MedicationActivity.class);
                intent.putExtra("pid",String.valueOf(pID));
                startActivity(intent);

            }
        });
        vaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthServiceActivity.this, VaccinationActivity.class);
                intent.putExtra("pid",String.valueOf(pID));
                startActivity(intent);

            }
        });
        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthServiceActivity.this, dietAndFitness.class);
                intent.putExtra("pid",String.valueOf(pID));
                startActivity(intent);

            }
        });
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthServiceActivity.this, HospitalActivity.class);
                intent.putExtra("pid",String.valueOf(pID));
                startActivity(intent);

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_health_service, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

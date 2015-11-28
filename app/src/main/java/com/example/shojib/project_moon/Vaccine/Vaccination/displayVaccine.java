package com.example.shojib.project_moon.Vaccine.Vaccination;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.shojib.project_moon.R;

import java.util.Calendar;

/**
 * Created by Shojib on 11/27/2015.
 */
public class displayVaccine extends Activity implements View.OnClickListener{

    private ImageButton ib;
    private Calendar cal;
    private int day;
    private int month;
    private int year;
    // private TextView dateTextView;

    private EditText reminder;
    private EditText vaccineName;
    private EditText vaccineReason;


    private VaccinationDataBaseQuery vaccinationDataBaseQuery;
    private VaccinationModule vaccinationModule;
    private  long vID=0;
    private  long pID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccination22);

        ib = (ImageButton) findViewById(R.id.button_datePick);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        reminder = (EditText) findViewById(R.id.vaccinationDateEditView);
        ib.setOnClickListener(this);




        vaccineName = (EditText)findViewById(R.id.editText_VaccineName);
        vaccineReason = (EditText)findViewById(R.id.editText_reasons);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            vID = Long.parseLong(getIntent().getStringExtra("vuid"));
            //vaccinationModule = vaccinationDataBaseQuery.getAllVaccine(vID);
            //medicationModule = MedicationDataBaseQuery.getSingleProfileById(pID);

            String vaccineName1 = vaccinationModule.getVaccineName();
            String vaccineReason1 = vaccinationModule.getVaccineReason();
            int reminder1 = vaccinationModule.getReminder();


            vaccineName.setText(vaccineName1);
            vaccineReason.setText(vaccineReason1);
            //reminder.setText(String.valueOf(reminder1));
            reminder.setText(reminder1);}}
    @Override
    public void onClick(View v) {

    }
}

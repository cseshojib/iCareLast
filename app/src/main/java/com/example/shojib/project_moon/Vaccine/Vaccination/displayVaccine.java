package com.example.shojib.project_moon.Vaccine.Vaccination;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.shojib.project_moon.Medication.Medicine.MedicationModule;
import com.example.shojib.project_moon.R;
import com.example.shojib.project_moon.Vaccine.VaccineDate.VaccineDateModule;

import java.util.Calendar;

public class displayVaccine extends Activity implements View.OnClickListener {

    private ImageButton ib;
    private Calendar cal;
    private int day;
    private int month;
    private int year;
    private EditText reminder;
    private EditText vaccineName;
    private EditText vaccineReason;


    private VaccinationDataBaseQuery vaccinationDataBaseQuery;
    private VaccinationModule vaccinationModule;
    private  long vID;
    private  long pID=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccination22);

        ib = (ImageButton) findViewById(R.id.button_datePick);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        ib.setOnClickListener(this);


        vaccineName = (EditText) findViewById(R.id.editText_VaccineName);
        vaccineReason = (EditText) findViewById(R.id.editText_reasons);
        reminder = (EditText) findViewById(R.id.vaccinationDateEditView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            vID = Long.parseLong(getIntent().getStringExtra("vid"));
            VaccinationModule vaccinationModule = vaccinationDataBaseQuery.getSingleVaccineById(vID);

            String vaccineName1 = vaccinationModule.getVaccineName();
            String vaccineReason1 = vaccinationModule.getVaccineReason();
            String reminder1 = vaccinationModule.getReminder();

            pID = vaccinationModule.getUserId();

            vaccineName.setText(vaccineName1);
            vaccineReason.setText(vaccineReason1);
            reminder.setText(reminder1);
        }

        Button saveButton = (Button) findViewById(R.id.saveVaccine_button);

        saveButton .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                final Editable vName1 = vaccineName.getText();
                final Editable vReason1 = vaccineReason.getText();
                final Editable reminder1 = reminder.getEditableText();

                try {
                    if (!TextUtils.isEmpty(vName1) && !TextUtils.isEmpty(vReason1)) {
                        vaccinationDataBaseQuery.updateVaccineByVaccinById(vID, vName1.toString(), vReason1.toString(), pID, reminder1.toString());
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


    @Override
    public void onClick(View v) {
        showDialog(0);
    }
    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            reminder.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };
}

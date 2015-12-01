package com.example.shojib.project_moon.Vaccine.Vaccination;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.shojib.project_moon.R;

import java.util.Calendar;


public class addVaccination22  extends Activity implements View.OnClickListener {


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
    private long vID;
    private long pID;

    String flag1 = null;
    String flag2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccination22);
        // mDateButton = (Button) findViewById(R.id.date_button);

        ib = (ImageButton) findViewById(R.id.button_datePick);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

       reminder = (EditText) findViewById(R.id.vaccinationDateEditView);
        ib.setOnClickListener(this);

        vaccineName = (EditText)findViewById(R.id.editText_VaccineName);
        vaccineReason = (EditText)findViewById(R.id.editText_reasons);


        vaccinationDataBaseQuery= new VaccinationDataBaseQuery(this);
        Intent vEIntent = getIntent();
        flag2 = vEIntent.getStringExtra("vid");
        flag1 = vEIntent.getStringExtra("pid");

        if (flag2 != null) {

            vID = Long.parseLong(getIntent().getStringExtra("vuid"));

            vaccinationModule = vaccinationDataBaseQuery.getSingleVaccineById(vID);



       // vaccinationModule = vaccinationDataBaseQuery.getAllVaccine(vID);
        //medicationModule = MedicationDataBaseQuery.getSingleProfileById(pID);

        String vaccineName1 = vaccinationModule.getVaccineName();
        String vaccineReason1 = vaccinationModule.getVaccineReason();
        String reminder1 = vaccinationModule.getReminder();

            pID=vaccinationModule.getUserId();

        vaccineName.setText(vaccineName1);
        vaccineReason.setText(vaccineReason1);
        reminder.setText(reminder1);

        Button b = (Button) findViewById(R.id.saveVaccine_button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                final Editable vaccineName1   = vaccineName.getText();
                final Editable vaccineReason1 = vaccineReason.getText();
                final Editable reminder1  =  reminder.getText();

                try {
                    if (!TextUtils.isEmpty(vaccineName1) && !TextUtils.isEmpty(vaccineReason1) && !TextUtils.isEmpty(reminder1))
                    {vaccinationDataBaseQuery.updateVaccineByVaccinById(vID, vaccineName1.toString(), vaccineReason1.toString(), pID, reminder1.toString());
                    finish();}


                    else
                    {
                        Toast.makeText(getApplicationContext(), "Your Vaccine Added ", Toast.LENGTH_LONG).show();

                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Your Vaccine Added", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    else {
            pID=Long.parseLong(getIntent().getStringExtra("pid"));

            final Editable vaccineName1   = vaccineName.getText();
            final Editable vaccineReason1 = vaccineReason.getText();
            final Editable reminder1  =  reminder.getText();

            Button b = (Button) findViewById(R.id.saveVaccine_button);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {

                        System.out.println(vaccineName1.toString()+vaccineReason1.toString()+reminder1.toString());
                        if (!TextUtils.isEmpty(vaccineName1) && !TextUtils.isEmpty(vaccineReason1) && !TextUtils.isEmpty(reminder1))

                        {

                            vaccinationDataBaseQuery.createNewVaccine(vaccineName1.toString(), vaccineReason1.toString(), pID, reminder1.toString());
                            Toast.makeText(getApplicationContext(), "Vaccine Added Successfully!", Toast.LENGTH_LONG).show();
                            finish();
                        }


                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Your Vaccine Added", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_vaccination22, menu);
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

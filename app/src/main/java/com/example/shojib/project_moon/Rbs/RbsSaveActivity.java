package com.example.shojib.project_moon.Rbs;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.shojib.project_moon.Medication.Medicine.MedicationDataBaseQuery;
import com.example.shojib.project_moon.Medication.Medicine.MedicationModule;
import com.example.shojib.project_moon.R;

import java.util.Calendar;


public class RbsSaveActivity extends Activity implements View.OnClickListener {

    private TextView date;
    private TextView time;

    private EditText rbsUnit;
    private EditText dateEditText;


    Button rbsSaveButton;

    private RbsDatabaseQuery rbsDatabaseQuery;
    private RbsModule rbsModule;
    private ImageButton dateImageButton;
    private Calendar cal1;
    private int day;
    private int month;
    private int year;

    private ImageButton timeImageButton;
    private Calendar cal2;
    private int hour;
    private int min;


    private long pID=1;

    private long rID;
    String flag1 = null;
    String flag2 = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rbs__save);


        date = (TextView) findViewById(R.id.rbsDateTextView);
        time = (TextView) findViewById(R.id.rbsTimeTextView);
        rbsUnit = (EditText) findViewById(R.id.rbsEditText);


        dateImageButton = (ImageButton) findViewById(R.id.imageButton_date);
        timeImageButton = (ImageButton) findViewById(R.id.imageButton_time);

        dateImageButton.setOnClickListener(this);
        timeImageButton.setOnClickListener(this);


        rbsDatabaseQuery = new RbsDatabaseQuery(this);
        Intent mEIntent = getIntent();
        flag2 = mEIntent.getStringExtra("rid");
        flag1 = mEIntent.getStringExtra("pid");

        if (flag2 != null) {

            rID = Long.parseLong(getIntent().getStringExtra("mid"));

            rbsModule = rbsDatabaseQuery.getSingleRbsById(rID);

            pID = rbsModule.getUserId();

            float RBSUnit = rbsModule.getRbsUnit();
            String RBSDate = rbsModule.getRbsDate();
            String RBStime = rbsModule.getRbsTime();


            rbsUnit.setText(String.valueOf(RBSUnit));
            date.setText(RBSDate);
            time.setText(RBStime);


            rbsSaveButton = (Button) findViewById(R.id.rbsSaveButton);
            rbsSaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Editable RBSUnit1 = rbsUnit.getText();
                    final Editable RBSDate1 = (Editable) date.getText();
                    final Editable RBStime1 = (Editable) time.getText();


                    try {
                        if (!TextUtils.isEmpty(RBSUnit1) && !TextUtils.isEmpty(RBSDate1) && !TextUtils.isEmpty(RBStime1)) {
                            rbsDatabaseQuery.updateRbsByRbsId(rID, Float.valueOf(RBSUnit1.toString()), RBSDate1.toString(), RBStime1.toString(), pID);
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
            else {

                pID=Long.parseLong(getIntent().getStringExtra("pid"));
                final Editable RBSUnit1 = rbsUnit.getText();
                final Editable RBSDate1 = (Editable) date.getText();
                final Editable RBStime1 = (Editable) time.getText();

                Button sameMedicineButton = (Button) findViewById(R.id.saveMedicine_button);

                sameMedicineButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            System.out.println(RBSUnit1.toString()+RBSDate1.toString()+RBStime1.toString());
                            if (!TextUtils.isEmpty(RBSUnit1) && !TextUtils.isEmpty(RBSDate1)&& !TextUtils.isEmpty(RBStime1) )
                            {
                                rbsDatabaseQuery.createNewRbs(Float.valueOf(RBSUnit1.toString()) , RBSDate1.toString(), RBStime1.toString(),pID);
                                finish();

                                Toast.makeText(getApplicationContext(), "RBS Added Successfully!", Toast.LENGTH_LONG).show();

                                finish();
                            }
                        }
                        catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "You Must Fill all Fields!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

        }

    @Override
    public void onClick(View v) {
        if (v == dateImageButton) {

            // Process to get Current Date
            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            date.setText(dayOfMonth + "-"
                                    + (monthOfYear + 1) + "-" + year);

                        }
                    },year, month, day);
            dpd.show();
        }
        if (v == timeImageButton)
        {

            // Process to get Current Time
            final Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            min = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            // Display Selected time in textbox
                            time.setText(hourOfDay + ":" + minute);
                        }
                    }, hour, min, false);
            tpd.show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rbs__save, menu);
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



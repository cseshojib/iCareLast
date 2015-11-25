package com.example.shojib.project_moon;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Shojib on 11/22/2015.
 */
public class rbs_date extends Activity implements View.OnClickListener {
    private ImageButton ib1;
    private Calendar cal1;
    private int day;
    private int month;
    private int year;
    private TextView et1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rbs__save);
        // mDateButton = (Button) findViewById(R.id.date_button);


        ib1 = (ImageButton) findViewById(R.id.imageButton_date);
        cal1 = Calendar.getInstance();
        day = cal1.get(Calendar.DAY_OF_MONTH);
        month = cal1.get(Calendar.MONTH);
        year = cal1.get(Calendar.YEAR);
        et1 = (TextView) findViewById(R.id.rbsDateTextView);
        ib1.setOnClickListener(this);



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
            et1.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };



}

package com.example.shojib.project_moon;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Shojib on 11/22/2015.
 */
public class rbs_time  extends Activity implements View.OnClickListener {
    private ImageButton ib;
    private Calendar cal;
    private int hour;
    private int min;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rbs__save);


        ib = (ImageButton) findViewById(R.id.imageButton_time);
        cal = Calendar.getInstance();
        hour = cal.get(Calendar.HOUR_OF_DAY);
        min = cal.get(Calendar.MINUTE);
        et = (EditText) findViewById(R.id.editText_time);
        ib.setOnClickListener(this);



    }
    @Override
    public void onClick(View v) {
        showDialog(0);
    }
    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new TimePickerDialog(this, timePickerListener, hour, min, false);
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            int hour;
            String am_pm;
            if (hourOfDay > 12) {
                hour = hourOfDay - 12;
                am_pm = "PM";
            } else {
                hour = hourOfDay;
                am_pm = "AM";
            }
            et.setText(hour + " : " + minute + " " + am_pm);
        }
    };}

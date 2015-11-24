package com.example.shojib.project_moon.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.shojib.project_moon.R;

import java.util.Calendar;

public class add_vaccine extends Activity implements View.OnClickListener {

// this class should be deleted ....


    private ImageButton ib;
    private Calendar cal;
    private int day;
    private int month;
    private int year;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vaccine);
        // mDateButton = (Button) findViewById(R.id.date_button);
        ib = (ImageButton) findViewById(R.id.button_datePick);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        et = (EditText) findViewById(R.id.editText_date);
        ib.setOnClickListener(this);
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
            et.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };

}
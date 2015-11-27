package com.example.shojib.project_moon.Medication.Medicine;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.shojib.project_moon.R;


public class addMedicine extends Activity implements OnClickListener {

    private ImageButton ib;
    private Calendar cal;
    private int hour;
    private int min;
    private EditText et;
    private EditText medicineName;
    private EditText medicineReason;
    private CheckBox reminder;

    private MedicationDataBaseQuery medicationDataBaseQuery;
    private MedicationModule medicationModule;
    private  long pID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_medicine);


        ib = (ImageButton) findViewById(R.id.button_timePick);
        cal = Calendar.getInstance();
        hour = cal.get(Calendar.HOUR_OF_DAY);
        min = cal.get(Calendar.MINUTE);
        et = (EditText) findViewById(R.id.editText_time);
        ib.setOnClickListener(this);


        medicineName = (EditText)findViewById(R.id.editText_medicineName);
        medicineReason = (EditText)findViewById(R.id.editText_reasons);
        reminder = (CheckBox)findViewById(R.id.checkBox);



        medicationDataBaseQuery= new MedicationDataBaseQuery(this);
        pID = Long.parseLong(getIntent().getStringExtra("puid"));


        //medicationModule = MedicationDataBaseQuery.getSingleProfileById(pID);

        String medicineName = medicationModule.getMedicineName();
        String medicineReason = medicationModule.getMedicineReason();
        int reminder = medicationModule.getReminder();


        /*medicineName.setText(medicineName);
        medicineReason.setText(medicineReason);
        reminder.setText(reminder);*/




        Button b = (Button) findViewById(R.id.saveMedicine_button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*final Editable medicineName =medicineName.getText();
                final Editable medicineReason =medicineReason.getText();
                final Editable reminder = reminder.getText();*/







                try {
                    //if (!TextUtils.isEmpty(medicineName) && !TextUtils.isEmpty(medicineReason) && !TextUtils.isEmpty(reminder))

                   {
                       // medicationDataBaseQuery.updateProfileByProfileId(pID,medicineName.toString(), medicineReason.toString(), reminder.getText().toString());
                        finish();
                    }
                    //else
                    {
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
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pop_up_medicine, menu);
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
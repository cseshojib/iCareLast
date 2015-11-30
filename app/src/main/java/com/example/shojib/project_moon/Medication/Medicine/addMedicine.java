package com.example.shojib.project_moon.Medication.Medicine;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.shojib.project_moon.R;


public class addMedicine extends Activity implements OnClickListener {


    private MedicationDataBaseQuery medicationDataBaseQuery;
    private MedicationModule medicationModule;
    private ImageButton ib;
    private Calendar cal;
    private int hour;
    private int min;
    private EditText reminder;
    private EditText medicineName;
    private EditText medicineReason;

    private long pID=1;

    private long mID;
    String flag1 = null;
    String flag2 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_medicine);


        ib = (ImageButton) findViewById(R.id.button_timePick);
        cal = Calendar.getInstance();
        hour = cal.get(Calendar.HOUR_OF_DAY);
        min = cal.get(Calendar.MINUTE);
        reminder = (EditText) findViewById(R.id.timeTextView);
        medicineName = (EditText) findViewById(R.id.editText_medicineName);
        medicineReason = (EditText) findViewById(R.id.editText_reasons);


        ib.setOnClickListener(this);

        medicationDataBaseQuery = new MedicationDataBaseQuery(this);
        Intent mEIntent = getIntent();
        flag2 = mEIntent.getStringExtra("mid");
        flag1 = mEIntent.getStringExtra("pid");

        if (flag2 != null) {

            mID = Long.parseLong(getIntent().getStringExtra("mid"));

            medicationModule = medicationDataBaseQuery.getSingleMedicineById(mID);


            String mName = medicationModule.getMedicineName();
            String mReason = medicationModule.getMedicineReason();
            String mReminderTime = medicationModule.getReminder();
            pID=medicationModule.getUserId();

            medicineName.setText(mName);
            medicineReason.setText(mReason);
            reminder.setText(mReminderTime);

            Button saveMedicineButton = (Button) findViewById(R.id.saveMedicine_button);

            saveMedicineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                    final Editable mName1 = medicineName.getText();
                    final Editable mReason1 = medicineReason.getText();
                    final Editable reminder1 =  reminder.getText();


                    try {
                        if (!TextUtils.isEmpty(mName1) && !TextUtils.isEmpty(mReason1) ) {
                            medicationDataBaseQuery.updateMedicineByMedicineId(mID, mName1.toString(), mReason1.toString(), pID, reminder1.toString());
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "You Must Fill all Fields!", Toast.LENGTH_LONG).show();

                        }

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "You Must Fill all Fields!", Toast.LENGTH_LONG).show();
                    }

                }
            });
        } else {

            pID=Long.parseLong(getIntent().getStringExtra("pid"));
            final Editable mName1 = medicineName.getText();
            final Editable mReason1 = medicineReason.getText();
            final Editable reminder1 = reminder.getText();

            Button sameMedicineButton = (Button) findViewById(R.id.saveMedicine_button);

            sameMedicineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        System.out.println(mName1.toString()+mReason1.toString());
                        if (!TextUtils.isEmpty(mName1) && !TextUtils.isEmpty(mReason1) )
                        {
                            medicationDataBaseQuery.createNewMedication(mName1.toString(), mReason1.toString(), pID, reminder1.toString());
                           // medicationDataBaseQuery.createNewMedication("Napa","Jor", pID, "11.30");
                            Toast.makeText(getApplicationContext(), "Medicine Added Successfully!", Toast.LENGTH_LONG).show();

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
            reminder.setText(hour + " : " + minute + " " + am_pm);
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
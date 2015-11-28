package com.example.shojib.project_moon.Medication.Medicine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.shojib.project_moon.Activity.HealthServiceActivity;
import com.example.shojib.project_moon.GeneralProfile.GeneralProfileDataBaseQuery;
import com.example.shojib.project_moon.GeneralProfile.GeneralProfileModule;
import com.example.shojib.project_moon.Medication.MedicationTime.MedicineTimeModule;
import com.example.shojib.project_moon.R;

import java.util.Calendar;


public class displayMedicine extends Activity implements View.OnClickListener{

    int from_Where_I_Am_Coming = 0;

    private MedicationDataBaseQuery mMedicationDataBaseQuery;
    private MedicationModule medicationModule;

    private ImageButton ib;
    private Calendar cal;
    private int hour;
    private int min;

    private EditText medicineName;
    private EditText medicineReason;
    private EditText reminder;

    private  long mID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_medicine);


        ib = (ImageButton) findViewById(R.id.button_timePick);
        cal = Calendar.getInstance();
        hour = cal.get(Calendar.HOUR_OF_DAY);
        min = cal.get(Calendar.MINUTE);
        reminder = (EditText) findViewById(R.id.editText_time);
        ib.setOnClickListener(this);

        medicineName = (EditText)findViewById(R.id.editText_medicineName);
        medicineReason = (EditText)findViewById(R.id.editText_reasons);

        // starting From this line, code will be changed for new DB design.

        mMedicationDataBaseQuery = new MedicationDataBaseQuery(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mID = Long.parseLong(getIntent().getStringExtra("mid"));
            MedicationModule medicationModule = mMedicationDataBaseQuery.getSingleMedicineById(mID);

            String mName = medicationModule.getMedicineName();
            String mReason = medicationModule.getMedicineReason();
            String mReminderTime = medicationModule.getReminder();

            medicineName.setText(mName);
            medicineReason.setText(mReason);
            reminder.setText(mReminderTime);






        }
    }

    @Override
    public void onClick(View v) {


    }
}


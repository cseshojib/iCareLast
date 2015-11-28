package com.example.shojib.project_moon.Doctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shojib.project_moon.R;


public class DoctorsProfileActivity extends Activity implements View.OnClickListener {


    private DoctorDataBaseQuery doctorDataBaseQuery;
    private DoctorModule doctorModule;

    EditText doctorName;
    EditText dr_phoneNumber;
    EditText dr_address;
    EditText appointment_date;
    EditText appointment_time;
    CheckBox setreminder;


   EditText dr_Specialty;



    //private Button saveDoctorsButton;

    private long pID=1;
    private long dID;
    String flag1 = null;
    String flag2 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_profile);

        doctorName = (EditText) findViewById(R.id.doctorNameEditText);
        dr_phoneNumber = (EditText) findViewById(R.id.dr_phoneEditText);
        dr_address = (EditText) findViewById(R.id.dr_AddressEditText);
        dr_Specialty = (EditText) findViewById(R.id.SpecialtyEditText);
        appointment_date =(EditText)findViewById(R.id.Appointment_Date);
        appointment_time =(EditText)findViewById(R.id.Appointment_Time);
        setreminder = (CheckBox)findViewById(R.id.appiontment_reminder);


        //saveDoctorsButton = (Button)findViewById(R.id.saveDoctorButton);

        doctorDataBaseQuery = new DoctorDataBaseQuery(this);
        Intent mEIntent = getIntent();
        flag2 = mEIntent.getStringExtra("did");
        flag1 = mEIntent.getStringExtra("pid");

        if (flag2 != null)
        {

            dID = Long.parseLong(getIntent().getStringExtra("did"));

            doctorModule = doctorDataBaseQuery.getSingleDoctorById(dID);


            String dName = doctorModule.getDoctorName();
            String dPhone = doctorModule.getPhone();
            String dAddress = doctorModule.getAddress();
            String dSpecialty = doctorModule.getSpeciality();
            String dAppointmentDate = doctorModule.getAppointmentDate();
            String dAppointmentTime = doctorModule.getAppointmentTime();
            String setreminder1 = doctorModule.getReminder();

            pID = doctorModule.getUserId();

            doctorName.setText(dName);
            dr_phoneNumber.setText(dPhone);
            dr_address.setText(dAddress);
            dr_Specialty.setText(dSpecialty);
            appointment_date.setText(dAppointmentDate);
            appointment_time.setText(dAppointmentTime);
            setreminder.setText(setreminder1);

            Button saveMedicineButton = (Button) findViewById(R.id.saveDoctorButton);

            saveMedicineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                    final Editable dName1 = doctorName.getText();
                    final Editable dPhone1 = dr_phoneNumber.getText();
                    final Editable dAddress1 = dr_address.getText();
                    final Editable dSpecialty1 = doctorName.getText();
                    final Editable dAppointmentDate1 = dr_phoneNumber.getText();
                    final Editable dAppointmentTime1 = dr_address.getText();
                    final Editable setreminder2 = dr_address.getText();



                    try {
                        if (!TextUtils.isEmpty(dName1) && !TextUtils.isEmpty(dSpecialty1) && !TextUtils.isEmpty(dPhone1) && !TextUtils.isEmpty(dAddress1) && !TextUtils.isEmpty(dAppointmentDate1)&& !TextUtils.isEmpty(dAppointmentTime1)&& !TextUtils.isEmpty(setreminder2)) {

                            doctorDataBaseQuery.updateDoctorByDoctorById(dID, dName1.toString(), dSpecialty1.toString(), dPhone1.toString(), dAddress1.toString(), dAppointmentDate1.toString(), dAppointmentTime1.toString(), setreminder2.toString(),pID);
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
            else
        {

                pID=Long.parseLong(getIntent().getStringExtra("pid"));
            final Editable dName1 = doctorName.getText();
            final Editable dPhone1 = dr_phoneNumber.getText();
            final Editable dAddress1 = dr_address.getText();
            final Editable dSpecialty1 = doctorName.getText();
            final Editable dAppointmentDate1 = dr_phoneNumber.getText();
            final Editable dAppointmentTime1 = dr_address.getText();
            final Editable setreminder2 = dr_address.getText();

                Button sameMedicineButton = (Button) findViewById(R.id.saveDoctorButton);

                sameMedicineButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            System.out.println(dName1.toString()+dSpecialty1.toString()+dPhone1.toString()+dAddress1.toString()+dAppointmentDate1.toString()+dAppointmentTime1.toString()+setreminder2.toString());
                            if (!TextUtils.isEmpty(dName1) && !TextUtils.isEmpty(dSpecialty1) && !TextUtils.isEmpty(dPhone1) && !TextUtils.isEmpty(dAddress1) && !TextUtils.isEmpty(dAppointmentDate1)&& !TextUtils.isEmpty(dAppointmentTime1)&& !TextUtils.isEmpty(setreminder2)) {

                                doctorDataBaseQuery.updateDoctorByDoctorById(dID, dName1.toString(), dSpecialty1.toString(), dPhone1.toString(), dAddress1.toString(), dAppointmentDate1.toString(), dAppointmentTime1.toString(), setreminder2.toString(),pID);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doctors_profile, menu);
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


    @Override
    public void onClick(View v) {

    }
}
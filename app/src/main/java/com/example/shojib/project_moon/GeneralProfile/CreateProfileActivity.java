package com.example.shojib.project_moon.GeneralProfile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shojib.project_moon.Activity.HealthServiceActivity;
import com.example.shojib.project_moon.R;


public class CreateProfileActivity extends Activity {
    int from_Where_I_Am_Coming = 0;

    private GeneralProfileDataBaseQuery mGeneralProfileDataBaseQuery;
    private GeneralProfileModule generalProfileModule;
    private EditText name ;
    private EditText age;
    private EditText bloodgp;
    private EditText gender;
    private EditText heightEditText;
    private EditText weightEditText;
    private EditText bloodPressureEditText;
    private EditText diseaseEditText;


    private  long pID;
    String flag=null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

        name = (EditText) findViewById(R.id.editText_name);
        age =(EditText) findViewById(R.id.editText_age);
        bloodgp =(EditText) findViewById(R.id.editText_bloodgp);
        gender = (EditText) findViewById(R.id.editText_gender);
        heightEditText=(EditText)findViewById(R.id.editText_height);
        weightEditText=(EditText)findViewById(R.id.editText_weight);
        bloodPressureEditText=(EditText)findViewById(R.id.editText_bloodPressure);
        diseaseEditText=(EditText)findViewById(R.id.editText_disease);



        // starting From this line, code will be changed for new DB design.

        mGeneralProfileDataBaseQuery=new GeneralProfileDataBaseQuery(this);
        Button b2 = (Button)findViewById(R.id.button_add_Addi_healthinfo);
        b2.setVisibility(View.GONE);

        Intent mEIntent = getIntent();
        flag = mEIntent.getStringExtra("puid");

        if (flag != null) {

            pID = Long.parseLong(getIntent().getStringExtra("puid"));
            generalProfileModule = mGeneralProfileDataBaseQuery.getSingleProfileById(pID);

            String nam = generalProfileModule.getProfileName();
            String age1 = generalProfileModule.getAge() + "";
            String blgp = generalProfileModule.getBloodGroup();
            String gend = generalProfileModule.getGender();

            float height = generalProfileModule.getHeight();
            float weight = generalProfileModule.getWeight();
            final float bloodPressure = generalProfileModule.getBloodPressure();
            String disease = generalProfileModule.getDisease();

            name.setText(nam);
            age.setText(age1);
            bloodgp.setText(blgp);
            gender.setText(gend);
            heightEditText.setText(String.valueOf(height));
            weightEditText.setText(String.valueOf(weight));
            bloodPressureEditText.setText(String.valueOf(bloodPressure));
            diseaseEditText.setText(disease);

            Button b = (Button) findViewById(R.id.button_save);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Editable nam1 =name.getText();
                    final Editable age1 =age.getText();
                    final Editable blgp = bloodgp.getText();
                    final Editable gend = gender.getText();
                    final Editable height = heightEditText.getText();
                    final Editable weight = weightEditText.getText();
                    final Editable bloodPressure = bloodPressureEditText.getText();
                    final Editable disease = diseaseEditText.getText();

                    try {
                        if (!TextUtils.isEmpty(nam1) && !TextUtils.isEmpty(age1) && !TextUtils.isEmpty(blgp) && !TextUtils.isEmpty(gend)) {
                            mGeneralProfileDataBaseQuery.updateProfileByProfileId(pID,nam1.toString(), age.getText().toString(), blgp.toString(), gend.toString(),Float.valueOf(height.toString()), Float.valueOf(weight.toString()), Float.valueOf(bloodPressure.toString()), disease.toString());
                            //mGeneralProfileDataBaseQuery.updateProfileByProfileId(pID,nam1.toString(), age.getText().toString(), blgp.toString(), gend.toString(), height.parseFloat("5.8"), weight.parseFloat("165"), bloodPressure.parseFloat("85"), disease.toString());
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "You Must Fill all Fields!", Toast.LENGTH_LONG).show();

                        }

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "You Must Fill all Fields!", Toast.LENGTH_LONG).show();
                    }

                }
            });

            Button b3 = (Button) findViewById(R.id.button_add_Addi_healthinfo);
            b3.setVisibility(View.GONE);




        }
        else {
            final Editable nam1 =name.getText();
            final Editable age1 =age.getText();
            final Editable blgp = bloodgp.getText();
            final Editable gend = gender.getText();

            final Editable height = heightEditText.getText();
            final Editable weight = weightEditText.getText();
            final Editable bloodPressure = bloodPressureEditText.getText();
            final Editable disease = diseaseEditText.getText();

            Button b = (Button)findViewById(R.id.button_save);


            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (!TextUtils.isEmpty(nam1) && !TextUtils.isEmpty(age1) && !TextUtils.isEmpty(blgp) && !TextUtils.isEmpty(gend) )
                        {
                            mGeneralProfileDataBaseQuery.createNewProfile(nam1.toString(), age.getText().toString(), blgp.toString(), gend.toString(), Float.valueOf(height.toString()), Float.valueOf(weight.toString()), Float.valueOf(bloodPressure.toString()), disease.toString());
                            //mGeneralProfileDataBaseQuery.createNewProfile(nam1.toString(), age.getText().toString(), blgp.toString(), gend.toString(), Float.parseFloat("5.8"), Float.parseFloat("165"), Float.parseFloat("85"), "n/a");
                            finish();
                        }
                    }
                    catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "You Must Fill all Fields!", Toast.LENGTH_LONG).show();
                    }

                }
            });

            Button b3 = (Button)findViewById(R.id.button_add_Addi_healthinfo);
            b3.setVisibility(View.GONE);

            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CreateProfileActivity.this, HealthServiceActivity.class);
                    startActivity(intent);

                }
            });

        }
    }
}


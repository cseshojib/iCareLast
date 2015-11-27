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
    private EditText height;
    private EditText weight;
    private EditText bloodPressure;
    private EditText disease;


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
        height=(EditText)findViewById(R.id.editText_height);
        weight=(EditText)findViewById(R.id.editText_weight);
        bloodPressure=(EditText)findViewById(R.id.editText_bloodPressure);
        disease=(EditText)findViewById(R.id.editText_disease);



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

            /*height.setText(height);

            weight.setText(weight);

            bloodPressure.setText(bloodPressure);

            disease.setText(disease);*/





            Button b = (Button) findViewById(R.id.button_save);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    final Editable nam1 =name.getText();
                    final Editable age1 =age.getText();
                    final Editable blgp = bloodgp.getText();
                    final Editable gend = gender.getText();
                    /*final Editable height;
                    final Editable weight = weight.getText();
                    final Editable bloodPressure = bloodPressure.getText();
                    final Editable disease = disease.getText();*/





                    try {
                        if (!TextUtils.isEmpty(nam1) && !TextUtils.isEmpty(age1) && !TextUtils.isEmpty(blgp) && !TextUtils.isEmpty(gend)) {
                            mGeneralProfileDataBaseQuery.updateProfileByProfileId(pID,nam1.toString(), age.getText().toString(), blgp.toString(), gend.toString(), Float.parseFloat("5.8"), Float.parseFloat("165"), Float.parseFloat("85"), "n/a");
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

            /*final Editable height = height.getText();
            final Editable weight = weight.getText();
            final Editable bloodPressure = bloodPressure.getText();
            final Editable disease = disease.getText();*/

            Button b = (Button)findViewById(R.id.button_save);


            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (!TextUtils.isEmpty(nam1) && !TextUtils.isEmpty(age1) && !TextUtils.isEmpty(blgp) && !TextUtils.isEmpty(gend) )
                        {
                            mGeneralProfileDataBaseQuery.createNewProfile(nam1.toString(), age.getText().toString(), blgp.toString(), gend.toString(), Float.parseFloat("5.8"), Float.parseFloat("165"), Float.parseFloat("85"), "n/a");
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


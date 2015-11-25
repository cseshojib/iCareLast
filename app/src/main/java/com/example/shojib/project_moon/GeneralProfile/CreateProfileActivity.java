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



    private  long pID;
    String flag=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

        name = (EditText) findViewById(R.id.editText_name);
        age =(EditText) findViewById(R.id.editText_age);
        bloodgp =(EditText) findViewById(R.id.editText_bloodgp);
        gender = (EditText) findViewById(R.id.editText_gender);

        // starting From this line, code will be changed for new DB design.

        mGeneralProfileDataBaseQuery=new GeneralProfileDataBaseQuery(this);
        Button b2 = (Button)findViewById(R.id.button2);
        b2.setVisibility(View.GONE);

        Intent mEIntent = getIntent();
        flag = mEIntent.getStringExtra("puid");

        if (flag != null ) {

            pID = Long.parseLong(getIntent().getStringExtra("puid"));
            generalProfileModule = mGeneralProfileDataBaseQuery.getSingleProfileById(pID);

            String nam = generalProfileModule.getProfileName();
            String age1 = generalProfileModule.getAge() + "";
            String blgp = generalProfileModule.getBloodGroup();
            String gend = generalProfileModule.getGender();



            name.setText(nam);


            age.setText(age1);


            bloodgp.setText(blgp);


            gender.setText(gend);




            Button b = (Button) findViewById(R.id.button_save);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    final Editable nam1 =name.getText();
                    final Editable age1 =age.getText();
                    final Editable blgp = bloodgp.getText();
                    final Editable gend = gender.getText();



                    try {
                        if (!TextUtils.isEmpty(nam1) && !TextUtils.isEmpty(age1) && !TextUtils.isEmpty(blgp) && !TextUtils.isEmpty(gend)) {
                            mGeneralProfileDataBaseQuery.updateProfileByProfileId(pID,nam1.toString(), age.getText().toString(), blgp.toString(), gend.toString(), Float.parseFloat("5.8"), Float.parseFloat("58.6"), Float.parseFloat("55.0"), "n/a");
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

            Button b = (Button)findViewById(R.id.button_save);


            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (!TextUtils.isEmpty(nam1) && !TextUtils.isEmpty(age1) && !TextUtils.isEmpty(blgp) && !TextUtils.isEmpty(gend)) {
                            mGeneralProfileDataBaseQuery.createNewProfile(nam1.toString(), age.getText().toString(), blgp.toString(), gend.toString(), Float.parseFloat("5.8"), Float.parseFloat("58.6"), Float.parseFloat("55.0"), "n/a");
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


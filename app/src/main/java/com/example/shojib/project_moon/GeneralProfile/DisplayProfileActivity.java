package com.example.shojib.project_moon.GeneralProfile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shojib.project_moon.Activity.HealthServiceActivity;
import com.example.shojib.project_moon.R;


public class DisplayProfileActivity extends Activity {
    int from_Where_I_Am_Coming = 0;

    private GeneralProfileDataBaseQuery mGeneralProfileDataBaseQuery;
    private GeneralProfileModule generalProfileModule;
    private EditText name;
    private EditText age;
    private EditText bloodgp;
    private EditText gender;
    private EditText height;
    private EditText weight;
    private EditText bloodPressure;
    private EditText disease;


    private long pID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

        name = (EditText) findViewById(R.id.editText_name);
        age = (EditText) findViewById(R.id.editText_age);
        bloodgp = (EditText) findViewById(R.id.editText_bloodgp);
        gender = (EditText) findViewById(R.id.editText_gender);

        // starting From this line, code will be changed for new DB design.

        mGeneralProfileDataBaseQuery = new GeneralProfileDataBaseQuery(this);
        Button b2 = (Button) findViewById(R.id.button_add_Addi_healthinfo);
        b2.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pID = Long.parseLong(getIntent().getStringExtra("pid"));
            generalProfileModule = mGeneralProfileDataBaseQuery.getSingleProfileById(pID);

            String nam = generalProfileModule.getProfileName();
            String age1 = generalProfileModule.getAge() + "";
            String blgp = generalProfileModule.getBloodGroup();
            String gend = generalProfileModule.getGender();
            float height = generalProfileModule.getHeight();
            float weight = generalProfileModule.getWeight();
            float bloodPressure = generalProfileModule.getBloodPressure();
            String disease = generalProfileModule.getDisease();

            Button b = (Button) findViewById(R.id.button_save);
            b.setVisibility(View.INVISIBLE);

            name.setText(nam);


            age.setText(age1);


            bloodgp.setText(blgp);


            gender.setText(gend);

            /*height.setText(height);

            weight.setText(weight);

            bloodPressure.setText(bloodPressure);

            disease.setText(disease);*/



            Button b3 = (Button) findViewById(R.id.button_add_Addi_healthinfo);
            b3.setVisibility(View.VISIBLE);

            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DisplayProfileActivity.this, HealthServiceActivity.class);
                    startActivity(intent);

                }
            });

        }
    }
}


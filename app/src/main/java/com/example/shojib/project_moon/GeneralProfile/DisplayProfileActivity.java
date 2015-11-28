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
        height = (EditText) findViewById(R.id.editText_height);
        weight = (EditText) findViewById(R.id.editText_weight);
        bloodPressure = (EditText) findViewById(R.id.editText_bloodPressure);
        disease = (EditText) findViewById(R.id.editText_disease);

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
            float height1 = generalProfileModule.getHeight();
            float weight1 = generalProfileModule.getWeight();
            float bloodPressure1 = generalProfileModule.getBloodPressure();
            String disease1 = generalProfileModule.getDisease();

            Button b = (Button) findViewById(R.id.button_save);
            b.setVisibility(View.INVISIBLE);



            name.setText(nam);
            age.setText(age1);
            bloodgp.setText(blgp);
            gender.setText(gend);
            height.setText(String.valueOf(height1));
            weight.setText(String.valueOf(weight1));
            bloodPressure.setText(String.valueOf(bloodPressure1));
            disease.setText(disease1);



            Button b3 = (Button) findViewById(R.id.button_add_Addi_healthinfo);
            b3.setVisibility(View.VISIBLE);

            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DisplayProfileActivity.this, HealthServiceActivity.class);
                    intent.putExtra("pid",String.valueOf(pID));
                    startActivity(intent);

                }
            });

        }
    }
}


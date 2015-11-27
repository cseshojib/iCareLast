package com.example.shojib.project_moon.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shojib.project_moon.GeneralProfile.GeneralProfileDataBaseQuery;
import com.example.shojib.project_moon.GeneralProfile.GeneralProfileModule;
import com.example.shojib.project_moon.R;


public class DisplayContactActivity extends Activity {
    int from_Where_I_Am_Coming = 0;

    private GeneralProfileDataBaseQuery mGeneralProfileDataBaseQuery;
    private GeneralProfileModule generalProfileModule;
    private EditText name ;
    private EditText age;
    private EditText bloodgp;
    private EditText gender;



    private  long pID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

        name = (EditText) findViewById(R.id.editText_name);
        age =(EditText) findViewById(R.id.editText_age);
        bloodgp =(EditText) findViewById(R.id.editText_bloodgp);
        gender = (EditText) findViewById(R.id.editText_gender);

        // starting From this line, code will be changed for new DB design.

       mGeneralProfileDataBaseQuery=new GeneralProfileDataBaseQuery(this);
        Button b2 = (Button)findViewById(R.id.button_add_Addi_healthinfo);
     b2.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            pID = extras.getLong("pid");
            generalProfileModule=mGeneralProfileDataBaseQuery.getSingleProfileById(pID);

                String nam =generalProfileModule.getProfileName();
                String age1 =generalProfileModule.getAge()+"";
                String blgp = generalProfileModule.getBloodGroup();
                String gend = generalProfileModule.getGender();

                Float height = generalProfileModule.getHeight();
                Float weight= generalProfileModule.getWeight();
                Float bloodPressure = generalProfileModule.getBloodPressure();
                String disease = generalProfileModule.getDisease();

                Button b = (Button)findViewById(R.id.button_save);
                b.setVisibility(View.INVISIBLE);

                name.setText((CharSequence)nam);
                name.setFocusable(false);
                name.setClickable(false);

                age.setText((CharSequence)age1);
                age.setFocusable(false);
                age.setClickable(false);

                bloodgp.setText((CharSequence)blgp);
                bloodgp.setFocusable(false);
                bloodgp.setClickable(false);

                gender.setText((CharSequence)gend);
                gender.setFocusable(false);
                gender.setClickable(false);

               /* height.setText((CharSequence)height);
                height.setFocusable(false);
                height.setClickable(false);

                weight.setText((CharSequence)weight);
                weight.setFocusable(false);
                weight.setClickable(false);

                disease.setText((CharSequence)disease);
                disease.setFocusable(false);
                disease.setClickable(false);*/


                Button b3 = (Button)findViewById(R.id.button_add_Addi_healthinfo);
                b3.setVisibility(View.VISIBLE);

                b3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DisplayContactActivity.this, HealthServiceActivity.class);
                        startActivity(intent);

                    }
                });

        }
    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        Bundle extras = getIntent().getExtras();
//
//        if(extras !=null)
//        {
//            int Value = extras.getInt("id");
//            if(Value>0){
//                getMenuInflater().inflate(R.menu.display_contact, menu);
//            }
//
//            else{
//                getMenuInflater().inflate(R.menu.menu_main, menu);
//            }
//        }
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        super.onOptionsItemSelected(item);
//        switch(item.getItemId())
//        {
//            case R.id.Edit_Contact:
//                Button b = (Button)findViewById(R.id.button1);
//                b.setVisibility(View.VISIBLE);
//                name.setEnabled(true);
//                name.setFocusableInTouchMode(true);
//                name.setClickable(true);
//
//                age.setEnabled(true);
//                age.setFocusableInTouchMode(true);
//                age.setClickable(true);
//
//                bloodgp.setEnabled(true);
//                bloodgp.setFocusableInTouchMode(true);
//                bloodgp.setClickable(true);
//
//                gender.setEnabled(true);
//                gender.setFocusableInTouchMode(true);
//                gender.setClickable(true);
//
//
//
//                return true;
//            case R.id.Delete_Contact:
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setMessage(R.string.deleteContact)
//                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                mydb.deleteContact(id_To_Update);
//                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                                startActivity(intent);
//                            }
//                        })
//                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // User cancelled the dialog
//                            }
//                        });
//                AlertDialog d = builder.create();
//                d.setTitle("Are you sure");
//                d.show();
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//
//        }
//    }
//
//    public void run(View view)
//    {
//        Bundle extras = getIntent().getExtras();
//        if(extras !=null)
//        {
//            int Value = extras.getInt("id");
//            if(Value>0){
//                if(mydb.updateContact(id_To_Update,name.getText().toString(), age.getText().toString(), bloodgp.getText().toString(), gender.getText().toString())){
//                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                    startActivity(intent);
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
//                }
//            }
//            else{
//                if(mydb.insertContact(name.getText().toString(), age.getText().toString(), bloodgp.getText().toString(), gender.getText().toString())){
//                    Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
//                }
//
//                else{
//                    Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
//                }
//                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(intent);
//            }
//        }
//    }
}
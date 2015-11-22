package com.example.shojib.project_moon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class display_contact extends Activity {
    int from_Where_I_Am_Coming = 0;
    private DBHelper mydb ;

    TextView name ;
    TextView age;
    TextView bloodgp;
    TextView gender;
    TextView weight;
    TextView address;
    TextView marital;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);
        name = (TextView) findViewById(R.id.editText_name);
       age =(TextView) findViewById(R.id.editText_age);
        bloodgp =(TextView) findViewById(R.id.editText_bloodgp);
        gender = (TextView) findViewById(R.id.editText_gender);
        //weight =(TextView) findViewById(R.id.editText_weight);
        //address =(TextView) findViewById(R.id.editText_address);
       /// marital =(TextView) findViewById(R.id.editText_marital);

        mydb = new DBHelper(this);


        Button b2 = (Button)findViewById(R.id.button2);
        b2.setVisibility(View.INVISIBLE);




        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int Value = extras.getInt("id");

            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String nam = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
                String age1 = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_AGE));
                String blgp = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_BLOOD));
                String gend = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_GENDER));
                String weig = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_WEIGHT));
                String addrs = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_ADDRESS));
                String bia = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_MARITAL));

                if (!rs.isClosed())
                {
                    rs.close();
                }
                Button b = (Button)findViewById(R.id.button1);
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

                weight.setText((CharSequence)weig);
                weight.setFocusable(false);
                weight.setClickable(false);

                address.setText((CharSequence)addrs);
                address.setFocusable(false);
                address.setClickable(false);

                marital.setText((CharSequence)bia);
                marital.setFocusable(false);
                marital.setClickable(false);


                Button b3 = (Button)findViewById(R.id.button2);
                b3.setVisibility(View.VISIBLE);

                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(display_contact.this, health_service.class);
                        startActivity(intent);

                    }
                });

                            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();

        if(extras !=null)
        {
            int Value = extras.getInt("id");
            if(Value>0){
                getMenuInflater().inflate(R.menu.display_contact, menu);
            }

            else{
                getMenuInflater().inflate(R.menu.menu_main, menu);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.Edit_Contact:
                Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.VISIBLE);
                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);

                age.setEnabled(true);
                age.setFocusableInTouchMode(true);
                age.setClickable(true);

                bloodgp.setEnabled(true);
                bloodgp.setFocusableInTouchMode(true);
                bloodgp.setClickable(true);

                gender.setEnabled(true);
                gender.setFocusableInTouchMode(true);
                gender.setClickable(true);

                weight.setEnabled(true);
                weight.setFocusableInTouchMode(true);
                weight.setClickable(true);

                address.setEnabled(true);
                address.setFocusableInTouchMode(true);
                address.setClickable(true);

                marital.setEnabled(true);
                marital.setFocusableInTouchMode(true);
                marital.setClickable(true);

                return true;
            case R.id.Delete_Contact:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteContact)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteContact(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void run(View view)
    {
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int Value = extras.getInt("id");
            if(Value>0){
                if(mydb.updateContact(id_To_Update,name.getText().toString(), age.getText().toString(), bloodgp.getText().toString(), gender.getText().toString(), weight.getText().toString(), address.getText().toString(), marital.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                if(mydb.insertContact(name.getText().toString(), age.getText().toString(), bloodgp.getText().toString(), gender.getText().toString(), weight.getText().toString(),address.getText().toString(), marital.getText().toString())){
                    Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
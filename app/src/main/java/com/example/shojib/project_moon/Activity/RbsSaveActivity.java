package com.example.shojib.project_moon.Activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shojib.project_moon.R;


public class RbsSaveActivity extends ActionBarActivity {

    TextView date;
    TextView time;
    EditText rbsUnit;

    Button rbsSaveButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rbs__save);

        date = (TextView)findViewById(R.id.rbsDateTextView);
        time = (TextView)findViewById(R.id.rbsTimeTextView);
        rbsUnit = (EditText)findViewById(R.id.rbsEditText);
        rbsSaveButton = (Button) findViewById(R.id.rbsSaveButton);

        rbsSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                



            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rbs__save, menu);
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



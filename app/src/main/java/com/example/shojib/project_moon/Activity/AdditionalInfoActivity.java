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


public class AdditionalInfoActivity extends ActionBarActivity {

    public String height;
    public String weight;
    public String bloodPressure;
    public String extraIssue;

    EditText heightTextView;
    EditText weightTextView;
    EditText bloodPressureTextView;
    EditText extraIssueTextView;

    Button addInfoSaveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_info);

        heightTextView = (EditText)findViewById(R.id.editText_height);
        weightTextView = (EditText)findViewById(R.id.editText_weight);
        bloodPressureTextView =(EditText)findViewById(R.id.editText_BP);
        extraIssueTextView=(EditText)findViewById(R.id.editText_Ex);
        addInfoSaveButton = (Button)findViewById(R.id.add_health_info_button);




        addInfoSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height = heightTextView.getText().toString();
                weight = weightTextView.getText().toString();
                bloodPressure = bloodPressureTextView.getText().toString();
                extraIssue = extraIssueTextView.getText().toString();


            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_additional_info, menu);
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

package com.example.shojib.project_moon.Medication.Medicine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shojib.project_moon.R;


public class MedicationActivity extends Activity {

    //The "x" and "y" position of the "Show Button" on screen.

    Button addMedicineButton;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        addMedicineButton = (Button)findViewById(R.id.button_Add_Medicine);

        addMedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicationActivity.this, addMedicine.class);
                startActivity(intent);

            }
        });


    }

    // Get the x and y position after the button is draw on screen
// (It's important to note that we can't get the position in the onCreate(),
// because at that stage most probably the view isn't drawn yet, so it will return (0, 0))

}

package com.example.shojib.project_moon.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shojib.project_moon.R;


public class dietAndFitnessCalculation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_and_fitness_calculation);

        Button backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dietAndFitnessCalculation.this,HealthServiceActivity.class);
                startActivity(intent);
            }
        });
        Button calculateBMI = (Button)findViewById(R.id.bmiCalculateButton);

        calculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText weightText = (EditText)findViewById(R.id.weightText);
                EditText heightFeet = (EditText)findViewById(R.id.heightFeetEditText);
                EditText heightInch = (EditText)findViewById(R.id.heightInchEditText);
                EditText age =(EditText)findViewById(R.id.ageEditText);
                TextView resultText = (TextView)findViewById(R.id.bmiResultTextView);
                TextView calorieResult = (TextView)findViewById(R.id.calorieResultTextView);
                TextView  bmiResult = (TextView)findViewById(R.id.bmiResultTextView);
                float weight = Float.parseFloat(weightText.getText().toString());
                float heightInFeet = Float.parseFloat(heightFeet.getText().toString());
                float heightInInch = Float.parseFloat(heightInch.getText().toString());
                float age1 = Float.parseFloat(age.getText().toString());
                // calculate the bmi value

                float bmiValue = calculateBMI(weight, heightInFeet,heightInInch);
                float CalValue = calculateCalorie(weight, heightInFeet,heightInInch,age1);

                // interpret the meaning of the bmi value
                String bmiInterpretation = interpretBMI(bmiValue);
                // now set the value in the result text

                resultText.setText("Your BMI(Body Mass Index): " + bmiValue+ " Kg/m2 " + " - " + bmiInterpretation);
                calorieResult.setText("Calorie Need per Day: "+ CalValue + " kcal/day ");
            }
        });
    }
    private float calculateBMI (float weight, float heightFeet,float heightInchi) {

        //Body Mass Index or BMI = (weight in kilograms) ÷ (height in meters)² ≈
        //703 × (weight in pounds) ÷ (height in inches)²
        float totalLengthInMeter = (float) (0.3048*(heightFeet+ (heightInchi/12.0)));
        return (float) ( weight / (totalLengthInMeter * totalLengthInMeter));

    }

    private float calculateCalorie(float weight,float heightFeet,float heightInchi, float age1 )
    {
        //Calorie Needs per Day =66.67+(13.75*W)+(5*H)-(6.76*age)
        float totalLengthInMeter = (float) (0.3048*(heightFeet+ (heightInchi/12.0)));
        return (float) (66.67 + (13.75*weight) + (5.0*totalLengthInMeter)-6.76*age1);
    }


    // interpret what BMI means
    private String interpretBMI(float bmiValue) {

        if (bmiValue < 16)
        {
            return "Severely underweight!! ";
        }
        else if (bmiValue < 18.5)
        {

            return "Underweight";
        }
        else if ( bmiValue>18.5 && bmiValue < 24)
        {

            return "Normal";
        }
        else if (bmiValue < 30 && bmiValue >25 )
        {

            return "Overweight";
        }
        else
        {
            return "Obese";
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_diet_and_fitness_calculation, menu);
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

package com.example.shojib.project_moon.Vaccine.Vaccination;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.shojib.project_moon.Medication.Medicine.MedicationDataBaseQuery;
import com.example.shojib.project_moon.Medication.Medicine.MedicationListAdapter;
import com.example.shojib.project_moon.Medication.Medicine.MedicationModule;
import com.example.shojib.project_moon.Medication.Medicine.displayMedicine;
import com.example.shojib.project_moon.R;
import com.example.shojib.project_moon.Vaccine.VaccineDate.VaccineDateListAdapter;

import java.util.List;


public class VaccinationActivity extends Activity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{

    private ListView mListView;
    private VaccinationDataBaseQuery vaccinationDataBaseQuery;
    private VaccinationListAdapter mAdapter;
    private List<VaccinationModule> moduleList;
    long vID=0;

    Button addVaccineButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);

        addVaccineButton = (Button)findViewById(R.id.button_Add_Vaccine);

        addVaccineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VaccinationActivity.this, addVaccination22.class);
                startActivity(intent);

            }
        });

        mListView=(ListView)findViewById(R.id.listView2);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);

    }
    @Override
    protected void onResume() {

        forRefresh();
        super.onResume();
    }

    private void forRefresh()
    {
        vaccinationDataBaseQuery =new VaccinationDataBaseQuery(this);
        moduleList=vaccinationDataBaseQuery.getAllVaccine();
        if(moduleList!=null && !moduleList.isEmpty())
        {
            mAdapter=new VaccinationListAdapter(this, moduleList);
            mListView.setAdapter(mAdapter);
            //contextRegister();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        VaccinationListAdapter vaccinationListAdapter;
        vaccinationListAdapter= (VaccinationListAdapter)parent.getAdapter();
        Intent intent = new Intent(getApplicationContext(),displayMedicine.class);
        intent.putExtra("vid", String.valueOf(vaccinationListAdapter.getItemId(position)));
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        VaccinationListAdapter vaccinationListAdapter;

        vaccinationListAdapter=(VaccinationListAdapter)parent.getAdapter();

        vID = vaccinationListAdapter.getItemId(position);

        return false;
    }




   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vaccination, menu);
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

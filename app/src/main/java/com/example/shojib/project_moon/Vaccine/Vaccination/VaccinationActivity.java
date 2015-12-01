package com.example.shojib.project_moon.Vaccine.Vaccination;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.shojib.project_moon.Activity.HealthServiceActivity;
import com.example.shojib.project_moon.Medication.Medicine.addMedicine;
import com.example.shojib.project_moon.Medication.Medicine.displayMedicine;
import com.example.shojib.project_moon.R;

import java.util.List;


public class VaccinationActivity extends Activity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
    public final static String EXTRA_MESSAGE = "MESSAGE";

    private ListView vListView;
    private VaccinationDataBaseQuery vaccinationDataBaseQuery;
    private VaccinationListAdapter vAdapter;
    private List<VaccinationModule> moduleList;
    long eVID=0;

    Button addVaccineButton;

    long pID;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);

        Button backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VaccinationActivity.this,HealthServiceActivity.class);
                startActivity(intent);
            }
        });

        vListView=(ListView)findViewById(R.id.listView2);
        vListView.setOnItemClickListener(this);
        vListView.setOnItemLongClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pID = Long.parseLong(getIntent().getStringExtra("pid"));
        }


        addVaccineButton = (Button)findViewById(R.id.button_Add_Vaccine);

        addVaccineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VaccinationActivity.this, addVaccination22.class);
                intent.putExtra("pid", String.valueOf(pID));
                startActivity(intent);
            }
        });



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
            vAdapter=new VaccinationListAdapter(this, moduleList);
            vListView.setAdapter(vAdapter);
            contextRegister();
        }
        System.out.println("modulelist");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        VaccinationListAdapter vaccinationListAdapter;
        vaccinationListAdapter= (VaccinationListAdapter)parent.getAdapter();
        Intent intent = new Intent(getApplicationContext(),displayVaccine.class);
        intent.putExtra("vid", String.valueOf(vaccinationListAdapter.getItemId(position)));
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        VaccinationListAdapter vaccinationListAdapter;

        vaccinationListAdapter=(VaccinationListAdapter)parent.getAdapter();

        eVID = vaccinationListAdapter.getItemId(position);

        return false;
    }

    /** This will be invoked when an item in the listview is long pressed */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_vaccination, menu);
        menu.setHeaderTitle("Select Menu ");
    }
    private void contextRegister ()
    {
        registerForContextMenu(vListView);
    }
    /** This will be invoked when a menu item is selected */



    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()){
            case R.id.action_UpdateP:
                Intent HIntent=new Intent(VaccinationActivity.this,addVaccination22.class);
                HIntent.putExtra("vid",String.valueOf(eVID));
                startActivity(HIntent);
                break;
            case R.id.action_DeleteP:
                new AlertDialog.Builder(VaccinationActivity.this)
                        .setTitle("Delete Vaccine?")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                vaccinationDataBaseQuery.vaccineDeletByVaccinId(eVID);
                                forRefresh();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return false;
        }
        return true;
    }


    /*
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
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK)
        {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }*/
}

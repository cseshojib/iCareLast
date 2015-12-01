package com.example.shojib.project_moon.Medication.Medicine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.shojib.project_moon.Activity.HealthServiceActivity;
import com.example.shojib.project_moon.R;

import java.util.List;


public class MedicationActivity extends AppCompatActivity implements OnItemClickListener,AdapterView.OnItemLongClickListener {
    public final static String EXTRA_MESSAGE = "MESSAGE";

    private ListView mListView;
    private MedicationDataBaseQuery medicationDataBaseQuery;
    private MedicationListAdapter mAdapter;
    private List<MedicationModule> moduleList;
    long eMID = 0;

    Button addMedicineButton;
    long pID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        Button backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicationActivity.this,HealthServiceActivity.class );
                startActivity(intent);
            }
        });


        mListView = (ListView) findViewById(R.id.medicineListView);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pID = Long.parseLong(getIntent().getStringExtra("pid"));
        }
        addMedicineButton = (Button) findViewById(R.id.button_Add_Medicine);
        addMedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicationActivity.this, addMedicine.class);
                intent.putExtra("pid", String.valueOf(pID));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        forRefresh();

    }
    private void forRefresh()
    {
        medicationDataBaseQuery =new MedicationDataBaseQuery(this);
        moduleList=medicationDataBaseQuery.getAllMedicine();
        if(moduleList!=null && !moduleList.isEmpty())
        {   //System.out.println("modulelist not null");
            mAdapter=new MedicationListAdapter(this,moduleList);
            mListView.setAdapter(mAdapter);
            //contextRegister();
        }

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MedicationListAdapter medicationListAdapter;
        medicationListAdapter=(MedicationListAdapter)parent.getAdapter();
        Intent intent = new Intent(getApplicationContext(),displayMedicine.class);
        intent.putExtra("mid", String.valueOf(medicationListAdapter.getItemId(position)));
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        MedicationListAdapter medicationListAdapter;

        medicationListAdapter=(MedicationListAdapter)parent.getAdapter();

        eMID=medicationListAdapter.getItemId(position);
        return false;
    }

/*
    //@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_medication, menu);
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
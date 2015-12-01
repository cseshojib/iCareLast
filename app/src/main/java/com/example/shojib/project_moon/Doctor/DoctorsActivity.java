package com.example.shojib.project_moon.Doctor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.shojib.project_moon.Activity.HealthServiceActivity;
import com.example.shojib.project_moon.R;

import java.util.List;


public class DoctorsActivity extends AppCompatActivity implements OnItemClickListener,AdapterView.OnItemLongClickListener
{

    public final static String EXTRA_MESSAGE = "MESSAGE";

    private ListView mDListView;
    private DoctorDataBaseQuery doctorDataBaseQuery;
    private DoctorListAdapter mDoctorAdapter;
    private List<DoctorModule> doctorModuleList;
    long eDID=0;

    Button addDoctorButton;

    long pID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);


        mDListView = (ListView) findViewById(R.id.doctorsListView);
        mDListView.setOnItemClickListener(this);
        mDListView.setOnItemLongClickListener(this);

        Button backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorsActivity.this,HealthServiceActivity.class);
                startActivity(intent);
            }
        });



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pID = Long.parseLong(getIntent().getStringExtra("pid"));
        }

        addDoctorButton = (Button)findViewById(R.id.addNewDoctorButton);
        addDoctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorsActivity.this, DoctorsProfileActivity.class);
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
        doctorDataBaseQuery =new DoctorDataBaseQuery(this);
        doctorModuleList = doctorDataBaseQuery.getAllDoctor();
        if(doctorModuleList!=null && !doctorModuleList.isEmpty())
        {
            System.out.println("Module List not null");
            mDoctorAdapter=new DoctorListAdapter(this,doctorModuleList);
            mDListView.setAdapter(mDoctorAdapter);
            contextRegister();
        }
        System.out.println("doctorModulelist");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DoctorListAdapter doctorListAdapter;
        doctorListAdapter=(DoctorListAdapter)parent.getAdapter();
        Intent intent = new Intent(getApplicationContext(),DisplayDoctors.class);
        intent.putExtra("did", String.valueOf(doctorListAdapter.getItemId(position)));
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        DoctorListAdapter doctorListAdapter;

        doctorListAdapter=(DoctorListAdapter)parent.getAdapter();
        eDID=doctorListAdapter.getItemId(position);
        return false;
    }

    /** This will be invoked when an item in the listview is long pressed
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_doctors , menu);
        menu.setHeaderTitle("Select Menu ");
    }*/
    private void contextRegister ()
    {
        registerForContextMenu(mDListView);
    }
    /** This will be invoked when a menu item is selected */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()){
            case R.id.action_UpdateP:
                Intent HIntent=new Intent(DoctorsActivity.this,DoctorsProfileActivity.class);
                HIntent.putExtra("did",String.valueOf(eDID));
                startActivity(HIntent);
                break;
            case R.id.action_DeleteP:
                new AlertDialog.Builder(DoctorsActivity.this)
                        .setTitle("Delete Doctor?")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                doctorDataBaseQuery.DoctorDeletByDoctorId(eDID);
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
        getMenuInflater().inflate(R.menu.menu_doctors, menu);
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


*/

}




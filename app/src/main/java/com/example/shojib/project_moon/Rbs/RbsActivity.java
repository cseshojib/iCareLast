package com.example.shojib.project_moon.Rbs;

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
import android.widget.GridView;

import com.example.shojib.project_moon.Medication.Medicine.addMedicine;
import com.example.shojib.project_moon.R;

import java.util.List;


public class RbsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    Button rbsAddButton;

    public final static String EXTRA_MESSAGE = "MESSAGE";

    private GridView rbsGridView;
    private RbsDatabaseQuery rbsDatabaseQuery;
    private RbsListAdapter rAdapter;
    private List<RbsModule> moduleList;
    long eRID=0;
    long pID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rbs);
        rbsGridView = (GridView) findViewById(R.id.rbsGridView);
        rbsGridView.setOnItemClickListener(this);
        rbsGridView.setOnItemLongClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pID = Long.parseLong(getIntent().getStringExtra("pid"));
        }
        rbsAddButton = (Button) findViewById(R.id.addRbsButton);
        rbsAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RbsActivity.this, RbsSaveActivity.class);
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
            rbsDatabaseQuery  =new RbsDatabaseQuery(this);
            moduleList=rbsDatabaseQuery.getAllRbs();
            if(moduleList!=null && !moduleList.isEmpty())
            {   System.out.println("modulelist not null");
                rAdapter=new RbsListAdapter(this,moduleList);
                rbsGridView.setAdapter(rAdapter);
                contextRegister();
            }
            System.out.println("modulelist");
        }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        RbsListAdapter rbsListAdapter;

        rbsListAdapter=(RbsListAdapter)parent.getAdapter();

        eRID=rbsListAdapter.getItemId(position);
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_rbs, menu);
        menu.setHeaderTitle("Select Menu ");
    }

    private void contextRegister ()
    {
        registerForContextMenu(rbsGridView);
    }
    /** This will be invoked when a menu item is selected */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()){
            case R.id.action_UpdateP:
                Intent HIntent=new Intent(RbsActivity.this,RbsSaveActivity.class);
                HIntent.putExtra("rid",String.valueOf(eRID));
                startActivity(HIntent);
                break;
            case R.id.action_DeleteP:
                new AlertDialog.Builder(RbsActivity.this)
                        .setTitle("Delete RBS?")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                rbsDatabaseQuery.rbsDeletByRbsId(eRID);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rbs, menu);
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
    }

}

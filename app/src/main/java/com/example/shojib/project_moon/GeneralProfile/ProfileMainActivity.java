package com.example.shojib.project_moon.GeneralProfile;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.shojib.project_moon.Activity.DisplayContactActivity;
import com.example.shojib.project_moon.R;

import java.util.List;

public class ProfileMainActivity extends AppCompatActivity implements OnItemClickListener,AdapterView.OnItemLongClickListener {
    public final static String EXTRA_MESSAGE = "MESSAGE";


    private  ListView mListView;
    private GeneralProfileDataBaseQuery mGeneralProfileDataBaseQuery;
    private GeneralProfileListAdapter mAdapter;
    private List<GeneralProfileModule> moduleList;

    long ePID=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView=(ListView)findViewById(R.id.listView1);
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
        mGeneralProfileDataBaseQuery=new GeneralProfileDataBaseQuery(this);
        moduleList=mGeneralProfileDataBaseQuery.getAllProfile();
        if(moduleList!=null && !moduleList.isEmpty())
        {
            mAdapter=new GeneralProfileListAdapter(this,moduleList);
            mListView.setAdapter(mAdapter);
            contextRegister();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        GeneralProfileListAdapter mGeneralProfileListAdaptere;
        mGeneralProfileListAdaptere=(GeneralProfileListAdapter)parent.getAdapter();
        Intent intent = new Intent(getApplicationContext(),DisplayProfileActivity.class);
                intent.putExtra("pid", String.valueOf(mGeneralProfileListAdaptere.getItemId(position)));
                startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        GeneralProfileListAdapter mGeneralProfileListAdaptere;

        mGeneralProfileListAdaptere=(GeneralProfileListAdapter)parent.getAdapter();

        ePID=mGeneralProfileListAdaptere.getItemId(position);
        return false;

    }
    /** This will be invoked when an item in the listview is long pressed */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_main_for_context , menu);
        menu.setHeaderTitle("Select Menu ");
    }
    private void contextRegister ()
    {
        registerForContextMenu(mListView);
    }
    /** This will be invoked when a menu item is selected */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()){
            case R.id.action_UpdateP:
                Intent HIntent=new Intent(ProfileMainActivity.this,CreateProfileActivity.class);
                HIntent.putExtra("puid",String.valueOf(ePID));
                startActivity(HIntent);
                break;
            case R.id.action_DeleteP:
                new AlertDialog.Builder(ProfileMainActivity.this)
                        .setTitle("Delete Profile?")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                mGeneralProfileDataBaseQuery.profileDeletByProfileId(ePID);
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.item1:
                Intent intent = new Intent(getApplicationContext(),CreateProfileActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK)
        {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }
}
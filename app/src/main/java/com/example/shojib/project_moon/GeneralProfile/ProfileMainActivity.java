package com.example.shojib.project_moon.GeneralProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class ProfileMainActivity extends AppCompatActivity implements OnItemClickListener {
    public final static String EXTRA_MESSAGE = "MESSAGE";


    private  ListView mListView;
    private GeneralProfileDataBaseQuery mGeneralProfileDataBaseQuery;
    private GeneralProfileListAdapter mAdapter;
    private List<GeneralProfileModule> moduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView=(ListView)findViewById(R.id.listView1);
        mListView.setOnItemClickListener(this);

    }


    @Override
    protected void onResume() {
        mGeneralProfileDataBaseQuery=new GeneralProfileDataBaseQuery(this);
        moduleList=mGeneralProfileDataBaseQuery.getAllProfile();
        if(moduleList!=null && !moduleList.isEmpty())
        {
            mAdapter=new GeneralProfileListAdapter(this,moduleList);
            mListView.setAdapter(mAdapter);
        }



        super.onResume();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        GeneralProfileListAdapter mGeneralProfileListAdaptere;

        mGeneralProfileListAdaptere=(GeneralProfileListAdapter)parent.getAdapter();

        Intent intent = new Intent(getApplicationContext(),DisplayProfileActivity.class);

                intent.putExtra("pid", String.valueOf(mGeneralProfileListAdaptere.getItemId(position)));
                startActivity(intent);
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
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }


}
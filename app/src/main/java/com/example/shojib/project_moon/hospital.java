package com.example.shojib.project_moon;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class hospital extends Activity {

    //Button hospital;
    private WebView mWebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        //hospital = (Button) findViewById(R.id.button3);

       // hospital.setOnClickListener(new View.OnClickListener() {
            //@Override
           // public void onClick(View v) {
               // Intent intent = new Intent(hospital.this, MapsActivity.class);
                //startActivity(intent);



           // }
        //});

        mWebview = (WebView)findViewById(R.id.webView1);
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebview.loadUrl("https://www.google.com/maps");
        mWebview.setWebViewClient(new com.example.shojib.project_moon.MyAppWebViewClient()
        {
@Override
public void onPageFinished(WebView view,String url){
        //  findViewById(R.id.prcessbar1).setVisibility(View.GONE);

        findViewById(R.id.webView1).setVisibility(View.VISIBLE);



        }});


            }

    @Override
    public void onBackPressed()
    {
        if(mWebview.canGoBack())
            mWebview.goBack();
        else
            super.onBackPressed();
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hospital, menu);
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

    private class MyAppWebViewClient extends WebViewClient
    {

    }
}

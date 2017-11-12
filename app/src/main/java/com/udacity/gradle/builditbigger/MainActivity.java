package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.FetchData.EndpointsAsyncTask;
import com.vw.example.ayush.javajokes.Joker;
import com.vw.example.ayush.library_test.JokesDisplay;


public class MainActivity extends AppCompatActivity {
    public static final String joke_text="joketext";
    String resp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         MobileAds.initialize(this, getResources().getString(R.string.banner_ad_unit_id));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void tellJoke(View view) {

        EndpointsAsyncTask endpointsAsyncTask=new EndpointsAsyncTask(new EndpointsAsyncTask.OnTaskCompleted(){

            @Override
            public void setData(String text) {
               // Log.i("response_data",text);
                resp=text;

            }
        });
        endpointsAsyncTask.execute(new Pair<Context, String>(this, "abc"));
        Intent i=new Intent(this, JokesDisplay.class);
        i.putExtra(joke_text,resp);
        startActivity(i);
    }
}

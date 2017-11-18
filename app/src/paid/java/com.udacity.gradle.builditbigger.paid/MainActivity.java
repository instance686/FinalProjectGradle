package com.udacity.gradle.builditbigger.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.fetchdata.EndpointsAsyncTask;
import com.vw.example.ayush.library_test.JokesDisplay;


public class MainActivity extends AppCompatActivity{
    public static final String joke_text="joketext";
    String resp="";
    MainActivityFragment mainActivityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityFragment= (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);


    }

    @Override
    protected void onResume() {
        super.onResume();


            Log.i("PaidVersion:","Paid Activity Launched");



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
            public void setData(final String text) {
                resp=text;
                mainActivityFragment.getProgressBar().setVisibility(View.GONE);

                Intent i = new Intent(MainActivity.this, JokesDisplay.class);
                i.putExtra(joke_text,text);
                startActivity(i);


            }
        });
        mainActivityFragment.getProgressBar().setVisibility(View.VISIBLE);
        endpointsAsyncTask.execute();

    }


}

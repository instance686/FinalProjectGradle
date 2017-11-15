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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.FetchData.EndpointsAsyncTask;
import com.vw.example.ayush.library_test.JokesDisplay;


public class MainActivity extends AppCompatActivity{
    public static final String joke_text="joketext";
    String resp="";
    MainActivityFragment mainActivityFragment;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityFragment= (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(BuildConfig.PAID_VERSION) {
            Log.i("paidVersion:","Paid Activity Launched");
        }
        else
        {
            Log.i("freeVersion:","Free Activity Launched");
            MobileAds.initialize(this, getResources().getString(R.string.banner_ad_unit_id));
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstetial_ad_id));
            mInterstitialAd.loadAd(new AdRequest.Builder().build());

        }
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
                if(!BuildConfig.PAID_VERSION){
                    if (mInterstitialAd.isLoaded()) {

                        mInterstitialAd.show();
                    } else {
                        Log.i("Not_Loaded", "The interstitial wasn't loaded yet.");
                    }
                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdLoaded() {
                            // Code to be executed when an ad finishes loading.
                            Log.i("Ad_Loaded","Ad Loaded");
                        }

                        @Override
                        public void onAdFailedToLoad(int errorCode) {
                            // Code to be executed when an ad request fails.
                            Log.i("Ad_Loaded_F","Ad Loading Failed");
                        }

                        @Override
                        public void onAdOpened() {
                            // Code to be executed when the ad is displayed.
                            Log.i("Ad_opened","Ad Opened");

                        }

                        @Override
                        public void onAdLeftApplication() {
                            // Code to be executed when the user has left the app.
                        }

                        @Override
                        public void onAdClosed() {
                            // Code to be executed when when the interstitial ad is closed.
                            Log.i("Ad_closed","Ad Closed");
                            Intent i=new Intent(MainActivity.this, JokesDisplay.class);
                            i.putExtra(joke_text,text);
                            startActivity(i);

                        }
                    });

                }
                else {
                    Intent i = new Intent(MainActivity.this, JokesDisplay.class);
                    i.putExtra(joke_text,text);
                    startActivity(i);
                }

            }
        });
        mainActivityFragment.getProgressBar().setVisibility(View.VISIBLE);
        endpointsAsyncTask.execute();

    }


}

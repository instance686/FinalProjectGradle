package com.udacity.gradle.builditbigger.paid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    private ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);


            Log.i("paidVersion:","Paid Version Fragment Launched");


        //  mAdView = (AdView) root.findViewById(R.id.adView);
        progressBar=(ProgressBar) root.findViewById(R.id.progressBar1);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."

        return root;
    }
    @Override
    public void onPause() {

        super.onPause();
    }


    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {

        super.onDestroy();
    }


    public ProgressBar getProgressBar(){
        return progressBar;
    }



}

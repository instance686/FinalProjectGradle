package com.udacity.gradle.builditbigger.free;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private AdView mAdView;
    private ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mAdView = (AdView) root.findViewById(R.id.adView);

           Log.i("freeVersion:","Free Version Fragment Launched");
            mAdView.setVisibility(View.VISIBLE);
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);

        //  mAdView = (AdView) root.findViewById(R.id.adView);
        progressBar=(ProgressBar) root.findViewById(R.id.progressBar1);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."

        return root;
    }
    @Override
    public void onPause() {

        if (mAdView != null) {
            mAdView.pause();
        }

        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();

            if (mAdView != null) {
                mAdView.resume();
            }

    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {

            if (mAdView != null) {
                mAdView.destroy();

        }
        super.onDestroy();
    }


    public ProgressBar getProgressBar(){
        return progressBar;
    }



}

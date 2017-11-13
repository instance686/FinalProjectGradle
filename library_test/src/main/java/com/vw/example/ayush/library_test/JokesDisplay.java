package com.vw.example.ayush.library_test;

import android.support.compat.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class JokesDisplay extends AppCompatActivity {
    TextView tv;
    public static final String joke_text="joketext";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes_display);
        TextView tv =findViewById(R.id.textView);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            tv.setText(extras.getString(joke_text));
        }
    }

    }

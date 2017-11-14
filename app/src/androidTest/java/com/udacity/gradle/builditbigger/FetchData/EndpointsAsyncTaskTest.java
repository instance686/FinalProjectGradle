package com.udacity.gradle.builditbigger.FetchData;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.v4.util.Pair;
import android.test.AndroidTestCase;
import android.util.Log;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;


/**
 * Created by ayush on 15/11/17.
 */
public class EndpointsAsyncTaskTest extends AndroidTestCase {
    String val=" ";

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    @Test
    public void testOnPostExecute() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
            new EndpointsAsyncTask(new EndpointsAsyncTask.OnTaskCompleted() {
                @Override
                public void setData(String text) {
                }
            }){
                @Override
                protected void onPostExecute(String result) {
                    val=result;
                }
            }.execute(new Pair<Context, String>(getContext(), " "));
            }
        });

        signal.await(10, TimeUnit.SECONDS);

        assertEquals("This is totally a funny joke",val.trim());
    }


}
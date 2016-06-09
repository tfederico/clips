package com.leaf.clips;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.content.LocalBroadcastManager;

import com.leaf.clips.model.beacon.MyBeacon;
import com.leaf.clips.model.beacon.MyBeaconImp;
import com.leaf.clips.presenter.HomeActivity;

import junit.framework.Assert;

import org.altbeacon.beacon.Beacon;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.LinkedList;
import java.util.PriorityQueue;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author Marco Zanella
 * @version 0.01
 * @since 0.01
 */
public class SystemTest12 {
    HomeActivity testActivity;

    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule =
            new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setUp() throws Exception {
        testActivity = mActivityRule.getActivity();
        Thread t = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1*1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        LinkedList<Long> l = new LinkedList<Long>();
                        l.add((long) 0);
                        l.add((long) 0);
                        l.add((long) 0);
                        l.add((long) 0);
                        l.add((long) 0);
                        l.add((long) 0);
                        l.add((long) 0);
                        l.add((long) 0);
                        MyBeacon b = new MyBeaconImp(new Beacon.Builder()
                                .setId1("19235dd2-574a-4702-a42e-caccac06e325")
                                .setId2("666").setId3("0").setDataFields(l).build());
                        PriorityQueue<MyBeacon> p = new PriorityQueue<>();
                        p.add(b);
                        Intent msg = new Intent("beaconsDetected");
                        msg.putExtra("queueOfBeacons", p);
                        for(int i = 0; i < 3; i++) {
                            LocalBroadcastManager.getInstance(testActivity).sendBroadcast(msg);
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                });
        t.start();
        Thread.sleep(5000);
        t.join();


        ConnectivityManager connectivityManager =
                (ConnectivityManager)testActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        Assert.assertTrue("il telefono deve essere scollegato da internet per eseguire il test",
                !(networkInfo != null && networkInfo.isConnected()));
    }

    @Test
    public void shouldAlertIfNoInternetOnHomeActivity() {
        onView(withText(R.string.ok)).perform(click());
    }

}

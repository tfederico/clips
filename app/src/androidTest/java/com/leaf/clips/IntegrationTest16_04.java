package com.leaf.clips;
/**
 * @author Federico Tavella
 * @version 0.02
 * @since 0.00
 */

import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import com.leaf.clips.presenter.LoggingActivity;
import com.leaf.clips.presenter.MainDeveloperActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

/**
 * Test che verifica l'apertura di tutte le activity apribili dalla LoggingActivity tramite un click
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class IntegrationTest16_04 extends InstrumentationTestCase{
    LoggingActivity testActivity;

    @Rule
    public ActivityTestRule<LoggingActivity> mActivityRule =
            new ActivityTestRule<>(LoggingActivity.class);

    @Before
    public void init() throws InterruptedException {
        testActivity = mActivityRule.getActivity();

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        if (Build.VERSION.SDK_INT >= 23) {

            UiObject locationPermissions = device.findObject(new UiSelector().text("Consenti"));
            if(locationPermissions.exists()){
                try{
                    locationPermissions.click();

                } catch (UiObjectNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        UiObject allowPermissions = device.findObject(new UiSelector().text("OK"));
        while (allowPermissions.exists()) {
            try {
                allowPermissions.click();
                UiObject activateLocation = device.findObject(new UiSelector().text("Non attiva"));
                if(activateLocation.exists() && !activateLocation.isChecked()){
                    activateLocation.click();
                    device.pressBack();
                }

            } catch (UiObjectNotFoundException e) {
                Log.e("TEST", "There is no permissions dialog to interact with ");
            }
        }

        if(Build.VERSION.SDK_INT >= 23){
            UiObject storagePermissions = device.findObject(new UiSelector().text("Consenti"));
            if(storagePermissions.exists()){
                try{
                    storagePermissions.click();
                } catch (UiObjectNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void shouldLaunchMainDeveloperIntent(){
        Intents.init();
        testActivity.findViewById(R.id.fab_stop_logging).callOnClick();
        intended(hasComponent(MainDeveloperActivity.class.getName()));
        Intents.release();
    }
}

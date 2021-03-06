package com.leaf.clips;
/**
 * @author Federico Tavella
 * @version 0.11
 * @since 0.00
 */

import android.os.Build;
import android.support.test.InstrumentationRegistry;

import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

import com.leaf.clips.presenter.HelpActivity;
import com.leaf.clips.presenter.HomeActivity;
import com.leaf.clips.presenter.LocalMapActivity;
import com.leaf.clips.presenter.MainDeveloperPresenter;
import com.leaf.clips.presenter.NearbyPoiActivity;
import com.leaf.clips.presenter.PoiActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;

import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.View;

/**
 * Test che verifica l'apertura di tutte le activity apribili dalla HomeActivity tramite un click
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class IntegrationTest16_01 extends InstrumentationTestCase{

    HomeActivity testActivity;

    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule =
            new ActivityTestRule<>(HomeActivity.class);

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
    public void shouldLaunchNearbyPOIIntent(){
        Intents.init();
        //making the button visible in order to click it
        testActivity.findViewById(R.id.fab_explore_button).setVisibility(View.VISIBLE);
        testActivity.findViewById(R.id.fab_explore_button).callOnClick();
        intended(hasComponent(NearbyPoiActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void shouldLaunchPOIIntent() {
        Intents.init();
        //making the button visible in order to click it
        testActivity.findViewById(R.id.fab_all_poi_button).setVisibility(View.VISIBLE);
        testActivity.findViewById(R.id.fab_all_poi_button).callOnClick();
        intended(hasComponent(PoiActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void shouldLaunchMapIntent() {
        Intents.init();
        onView(withId(R.id.drawer_layout_home)).perform(open());
        onView(withId(R.id.nav_view_home)).perform(NavigationViewActions.navigateTo(R.id.mapManager));
        intended(hasComponent(LocalMapActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void shouldLaunchShowHelpIntent(){
        Intents.init();
        onView(withId(R.id.drawer_layout_home)).perform(open());
        onView(withId(R.id.nav_view_home)).perform(NavigationViewActions.navigateTo(R.id.help));
        intended(hasComponent(HelpActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void shouldLaunchDeveloperIntent(){
        Intents.init();
        onView(withId(R.id.drawer_layout_home)).perform(open());
        onView(withId(R.id.nav_view_home)).perform(NavigationViewActions.navigateTo(R.id.nav_developer));
        intended(hasComponent(MainDeveloperPresenter.class.getName()));
        Intents.release();
    }

}

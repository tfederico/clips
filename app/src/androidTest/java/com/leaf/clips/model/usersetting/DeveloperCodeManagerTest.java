package com.leaf.clips.model.usersetting;
/**
 * @author Federico Tavella
 * @version 0.01
 * @since 0.00
 */

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit Test 2
 */

// TODO: 5/6/16 Fede, Controllare test che non andavano 

@RunWith(AndroidJUnit4.class)
@SmallTest
public class DeveloperCodeManagerTest {

    private DeveloperCodeManager dev;

    private static final String USER_PREFERENCES = "userKey"; // TODO: 01/05/2016 decidere la stringa

    @Before
    public void init(){
        dev = new DeveloperCodeManager(InstrumentationRegistry.getTargetContext().getSharedPreferences(USER_PREFERENCES, 0));
    }

    @Test
    public void shouldAcceptDeveloperCode(){
        Assert.assertTrue(dev.isValid("miriade"));
    }


    @Test
    public void shouldRejectDeveloperCode(){
        Assert.assertFalse(dev.isValid(""));
    }
}

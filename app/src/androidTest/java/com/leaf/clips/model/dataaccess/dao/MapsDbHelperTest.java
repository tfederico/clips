package com.leaf.clips.model.dataaccess.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.leaf.clips.presenter.MyApplication;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Marco Zanella
 * @version 0.01
 * @since 0.01
 */

/**
 * TU98 & TU100
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class MapsDbHelperTest extends InstrumentationTestCase {

    Context context;
    private SQLiteDatabase database;
    private MapsDbHelper dbHelper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        context = InstrumentationRegistry.getTargetContext();
        Assert.assertNotNull(context);
        dbHelper = new MapsDbHelper(context);
        Assert.assertNotNull(dbHelper);
        database = dbHelper.getWritableDatabase();
        Assert.assertNotNull(database);
    }

    @Test
    public void shouldCreateTheDatabase() {
        Cursor c = database.rawQuery("SELECT * FROM sqlite_master WHERE type='table'" +
                " AND name='" + PhotoContract.TABLE_NAME + "'", null);
        Assert.assertEquals(1, c.getCount());
        c = database.rawQuery("SELECT * FROM sqlite_master WHERE type='table'" +
                " AND name='" + RegionOfInterestContract.TABLE_NAME  + "'", null);
        Assert.assertEquals(1, c.getCount());
        c = database.rawQuery("SELECT * FROM sqlite_master WHERE type='table'" +
                " AND name='" + PointOfInterestContract.TABLE_NAME  + "'", null);
        Assert.assertEquals(1, c.getCount());
        c = database.rawQuery("SELECT * FROM sqlite_master WHERE type='table'" +
                " AND name='" + EdgeContract.TABLE_NAME  + "'", null);
        Assert.assertEquals(1, c.getCount());
        c = database.rawQuery("SELECT * FROM sqlite_master WHERE type='table'" +
                " AND name='" + EdgeTypeContract.TABLE_NAME  + "'", null);
        Assert.assertEquals(1, c.getCount());
        c = database.rawQuery("SELECT * FROM sqlite_master WHERE type='table'" +
                " AND name='" + CategoryContract.TABLE_NAME  + "'", null);
        Assert.assertEquals(1, c.getCount());
        c = database.rawQuery("SELECT * FROM sqlite_master WHERE type='table'" +
                " AND name='" + RoiPoiContract.TABLE_NAME  + "'", null);
        Assert.assertEquals(1, c.getCount());
        c = database.rawQuery("SELECT * FROM sqlite_master WHERE type='table'" +
                " AND name='" + BuildingContract.TABLE_NAME  + "'", null);
        Assert.assertEquals(1, c.getCount());
        c = database.rawQuery("SELECT * FROM sqlite_master WHERE type='trigger'" +
                " AND name='delete_empty_category'", null);
        Assert.assertEquals(1, c.getCount());
        c = database.rawQuery("SELECT * FROM sqlite_master WHERE type='trigger'" +
                " AND name='delete_empty_edgetype'", null);
        Assert.assertEquals(1, c.getCount());

    }

    @Test
    public void testGetRemoteDatabaseURL() {
        String remoteURL = MyApplication.getConfiguration().getRemoteDBPath();
        assertEquals(remoteURL,dbHelper.getRemoteDatabaseURL());
    }

}
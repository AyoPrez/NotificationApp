package com.ayoprez.castroapp.robolectric;

import android.support.v4.widget.DrawerLayout;

import com.ayoprez.castroapp.BuildConfig;
import com.ayoprez.castroapp.R;
import com.ayoprez.castroapp.ui.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static com.ayoprez.castroapp.robolectric.support.ResourceLocator.getString;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by ayo on 10.07.16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class MainActivityTest {

    private MainActivity activity;
    private DrawerLayout drawerLayout;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity.class);
        drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
    }

    @Test
    public void shouldNotBeNull(){
        assertNotNull(activity);
    }

    @Test
    public void shouldHaveTitle(){
        assertEquals(activity.getTitle().toString(), getString(R.string.app_name));
    }

    public void shouldHaveDrawerMenu(){
        drawerLayout.performClick();
        assertTrue(drawerLayout.isActivated());
    }

    public void shouldOpenFragment(){
        drawerLayout.performClick();
    }

}

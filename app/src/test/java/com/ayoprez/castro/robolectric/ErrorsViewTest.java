package com.ayoprez.castro.robolectric;

import com.ayoprez.castro.BuildConfig;
import com.ayoprez.castro.R;
import com.ayoprez.castro.ui.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static junit.framework.Assert.assertEquals;

/**
 * Created by ayo on 08.09.16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class ErrorsViewTest {

    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void shouldShowErrorMessage(){
        activity.showErrorMessage((byte)0);

        assertEquals(activity.getString(R.string.base_error_restful_images), ShadowToast.getTextOfLatestToast());
    }
}

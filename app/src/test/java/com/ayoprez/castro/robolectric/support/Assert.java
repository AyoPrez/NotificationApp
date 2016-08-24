package com.ayoprez.castro.robolectric.support;

import android.view.View;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by ayo on 10.07.16.
 */
public class Assert {

    public static void assertViewIsVisible(View view){
        assertNotNull(view);
        assertEquals(view.getVisibility(), View.VISIBLE);
    }
}

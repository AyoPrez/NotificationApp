package com.ayoprez.castroapp.robolectric.aboutUs;

import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayoprez.castroapp.BuildConfig;
import com.ayoprez.castroapp.R;
import com.ayoprez.castroapp.ui.fragments.aboutus.AboutUsFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static com.ayoprez.castroapp.robolectric.support.Assert.assertViewIsVisible;
import static com.ayoprez.castroapp.robolectric.support.ResourceLocator.getColor;
import static com.ayoprez.castroapp.robolectric.support.ResourceLocator.getDimen;
import static com.ayoprez.castroapp.robolectric.support.ResourceLocator.getViewParams;
import static com.ayoprez.castroapp.robolectric.support.ViewLocator.getTextView;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startVisibleFragment;


/**
 * Created by ayo on 10.07.16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class AboutUsFragmentTest {

    private AboutUsFragment aboutUsFragment;
    private TextView nameAboutUs;

    @Before
    public void setup(){
        aboutUsFragment = AboutUsFragment.getInstance();
        startVisibleFragment(aboutUsFragment);
        nameAboutUs = getTextView(aboutUsFragment.getActivity(), R.id.tv_name_aboutus);
    }

    @Test
    public void shouldNotBeNull(){
        assertNotNull(aboutUsFragment);
    }

    @Test
    public void shouldHaveVisibleTextViewName(){
        assertViewIsVisible(nameAboutUs);
        assertEquals(nameAboutUs.getText().toString(), "Castro Morales");
    }

    @Test
    public void shouldHaveTheRightParams(){
        LinearLayout.LayoutParams nameParams = getViewParams(nameAboutUs);
        assertEquals(nameParams.topMargin, getDimen(R.dimen.margin_10));
        assertEquals(nameParams.bottomMargin, getDimen(R.dimen.margin_none));
        assertEquals(nameParams.leftMargin, getDimen(R.dimen.margin_none));
        assertEquals(nameParams.rightMargin, getDimen(R.dimen.margin_none));
        assertEquals(nameAboutUs.getGravity(), Gravity.CENTER);
        assertEquals(nameParams.width, LinearLayout.LayoutParams.MATCH_PARENT);
        assertEquals(nameParams.height, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Test
    public void shouldHaveTheRightNameTextAttributes(){
        assertEquals(nameAboutUs.getCurrentTextColor(), getColor(R.color.colorPrimary));
        assertEquals((int)nameAboutUs.getTextSize(), getDimen(R.dimen.textsize_30));
        assertEquals(nameAboutUs.getTypeface().getStyle(), Typeface.BOLD);
    }


    //TODO test this
    /*
    *  <TextView
                android:id="@+id/tv_description_aboutus"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="30dp"
                android:textSize="18sp"
                android:text="Description"/>
    * */

    /*
    *
    *  <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal"
                    android:gravity="center">

                    <ImageButton
                        android:id="@+id/button_share_aboutus"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_margin="10dp"
                        android:src="@drawable/ic_menu_share"/>

                    <ImageButton
                        android:id="@+id/button_call_aboutus"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_margin="10dp"
                        android:src="@drawable/ic_menu_camera"/>

                    <ImageButton
                        android:id="@+id/button_mail_aboutus"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_margin="10dp"
                        android:src="@drawable/ic_menu_send"/>
                </LinearLayout>
    *
    * */

}

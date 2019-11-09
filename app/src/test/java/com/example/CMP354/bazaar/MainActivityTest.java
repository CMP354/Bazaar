package com.example.CMP354.bazaar;

import android.widget.ImageButton;

import com.example.CMP354.bazaar.Activities.MainActivity;
import com.example.CMP354.bazaar.Fragments.EventFragment;
import com.example.CMP354.bazaar.Fragments.HistoryFragment;
import com.example.CMP354.bazaar.Fragments.HomeFragment;
import com.example.CMP354.bazaar.Fragments.ProfileFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowApplication;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(RobolectricTestRunner.class)

public class MainActivityTest {
    private MainActivity activity;
    private ImageButton cameraBtn;
    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity.class);
        cameraBtn = (ImageButton) activity.findViewById(R.id.cameraBtn);
    }


    @Test
    public void fragmentNotNull() {
        ProfileFragment pFragment = new ProfileFragment();
        assertNotNull(pFragment);

        HomeFragment hFragment = new HomeFragment();
        assertNotNull(hFragment);

        HistoryFragment hisFragment = new HistoryFragment();
        assertNotNull(hisFragment);

        EventFragment eFragment = new EventFragment();
        assertNotNull(eFragment);

    }

    @Test
    public void viewScanQRActivityStartedOnClick() {
        cameraBtn.performClick();
        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity has started", application.getNextStartedActivity(), is(notNullValue()));

    }

}

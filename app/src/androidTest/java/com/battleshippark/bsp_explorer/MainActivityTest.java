package com.battleshippark.bsp_explorer;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends AndroidTestCase {
    private MainActivityModel activityModel;
    private MainActivityPresenter activityPresenter;

    private boolean isActivityFinished;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        activityModel = new MainActivityModel();
        activityPresenter = new MainActivityPresenter(new ActivityAccessible());
		activityPresenter.setActivityModel(activityModel);
    }

    @Test
    public void backPressedInShortDurationAtRoot() throws InterruptedException {
        activityPresenter.goToRoot();
        assertTrue(isAtRoot());

        activityPresenter.onBackPressed();

        Thread.sleep(1500);

        activityPresenter.onBackPressed();

        assertTrue(isActivityFinished);
    }

    @Test
    public void backPressedInLongDurationAtRoot() throws InterruptedException {
        activityPresenter.goToRoot();
        assertTrue(isAtRoot());

        activityPresenter.onBackPressed();

        Thread.sleep(2500);

        activityPresenter.onBackPressed();

        assertFalse(isActivityFinished);
    }

    @Test
    public void backPressedInShortDurationAtSdcard() throws InterruptedException {
        activityPresenter.goTo(new File("/sdcard"));

        activityPresenter.onBackPressed();
        assertTrue(isAtRoot());

        activityPresenter.onBackPressed();

        Thread.sleep(1500);

        activityPresenter.onBackPressed();

        Assert.assertTrue(isActivityFinished);
    }

    @Test
    public void backPressedInLongDurationAtSdcard() throws InterruptedException {
        activityPresenter.goTo(new File("/sdcard"));

        activityPresenter.onBackPressed();
        assertTrue(isAtRoot());

        activityPresenter.onBackPressed();

        Thread.sleep(2500);

        activityPresenter.onBackPressed();

        Assert.assertFalse(isActivityFinished);
    }

    private boolean isAtRoot() {
        if (activityModel.currentAbsolutePath.getAbsolutePath().equals(File.listRoots()[0].getAbsolutePath()))
            return true;
        return false;
    }

    private class ActivityAccessible implements MainActivityPresenter.ActivityAccessible {
		@Override
		public void cancelMultiSelectMode() {
		}

		@Override
        public void finish() {
            isActivityFinished = true;
        }

/*        @Override
        public void refresh() {
        }*/

/*        @Override
        public void showToast(int stringResId, int duration) {
        }*/
    }
}

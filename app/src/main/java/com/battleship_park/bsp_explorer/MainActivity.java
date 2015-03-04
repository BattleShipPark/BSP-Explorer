package com.battleship_park.bsp_explorer;

import android.app.Activity;
import android.os.Bundle;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity implements MainActivityPresenter.ActivityAccessible {
    private MainActivityPresenter activityPresenter;
    private MainActivityModel activityModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        activityPresenter = new MainActivityPresenter(this);
        activityModel = new MainActivityModel();
    }
}

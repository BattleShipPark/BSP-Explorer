package com.battleshippark.bsp_explorer;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
    @ViewById(R.id.topView)
    protected TextView topTextView;

    @ViewById(R.id.contentsView)
    protected RecyclerView contentsRecyclerView;

    @ViewById(R.id.bottomView)
    protected ViewGroup bottomViewGroup;

    private MainActivityPresenter activityPresenter;
    private MainActivityModel activityModel;
    private RecyclerView.Adapter contentsAdapter;

    @AfterViews
    protected void onViewCreated() {
        contentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        activityModel = new MainActivityModel();
        activityPresenter = new MainActivityPresenter(new ActivityAccessible(), activityModel);

        contentsAdapter = new MainActivityContentsAdapter(activityPresenter, activityModel);
        contentsRecyclerView.setAdapter(contentsAdapter);

        activityPresenter.goToRoot();
    }

    @Override
    public void onBackPressed() {
        activityPresenter.onBackPressed();
    }

    private class ActivityAccessible implements MainActivityPresenter.ActivityAccessible {
        @Override
        public void finish() {
            MainActivity.this.finish();
        }

        @Override
        public void refresh() {
            contentsAdapter.notifyDataSetChanged();
        }

        @Override
        public void showToast(int stringResId, int duration) {
            Toast.makeText(MainActivity.this, stringResId, duration).show();
        }
    }
}

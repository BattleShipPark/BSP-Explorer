package com.battleship_park.bsp_explorer;

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
public class MainActivity extends Activity implements MainActivityPresenter.ActivityAccessible {
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
        activityPresenter = new MainActivityPresenter(this, activityModel);

        contentsAdapter = new MainActivityContentsAdapter(activityPresenter, activityModel);
        contentsRecyclerView.setAdapter(contentsAdapter);

        activityPresenter.goToRoot();
    }

    @Override
    public void onBackPressed() {
        if (!activityPresenter.goToParent()) {
            if (activityPresenter.isReadyToFinish())
                super.onBackPressed();
            else {
                Toast toast = Toast.makeText(this, R.string.ready_to_finish_activity, Toast.LENGTH_SHORT);
                toast.show();

                activityPresenter.setReadyToFinish();
            }
        }
    }

    @Override
    public void refresh() {
        contentsAdapter.notifyDataSetChanged();
    }
}

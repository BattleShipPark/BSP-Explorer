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
    protected EmptyRecyclerView contentsRecyclerView;

    @ViewById(R.id.emptyTextView)
    protected TextView emptyTextView;

    @ViewById(R.id.bottomLayout)
    protected ViewGroup bottomViewGroup;

    private MainActivityPresenter activityPresenter;
    private RecyclerView.Adapter contentsAdapter;
    private MainActivityBottomController bottomController;

    @AfterViews
    protected void onViewCreated() {
        contentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        activityPresenter = new MainActivityPresenter(new ActivityAccessible(), MainActivityModel.getInstance());

        contentsAdapter = new MainActivityContentsAdapter(activityPresenter, MainActivityModel.getInstance());
        contentsRecyclerView.setAdapter(contentsAdapter);
        contentsRecyclerView.setEmptyView(emptyTextView);

        bottomController = new MainActivityBottomController(this, bottomViewGroup, BottomLayoutMode.NORMAL);

        activityPresenter.goToRoot();
    }

    @Override
    public void onBackPressed() {
        activityPresenter.onBackPressed();
    }

    protected void setBottomLayoutMode(BottomLayoutMode mode) {

    }

    public static enum BottomLayoutMode {
        NORMAL, MULTISELECT, COPIED
    }

    private class ActivityAccessible implements MainActivityPresenter.ActivityAccessible {

        @Override
        public void finish() {
            MainActivity.this.finish();
        }

        @Override
        public void refresh() {
            contentsAdapter.notifyDataSetChanged();

            topTextView.setText(MainActivityModel.getInstance().currentAbsolutePath.getAbsolutePath());
        }

        @Override
        public void showToast(int stringResId, int duration) {
            Toast.makeText(MainActivity.this, stringResId, duration).show();
        }

    }
}

package com.battleshippark.bsp_explorer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Assert;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
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

    @Override
    protected void restoreIntent(Intent intent) {

    }

    @Override
    protected void restoreInstanceState(Bundle savedInstanceState) {
        MainActivityModel.getInstance().restoreInstanceState(savedInstanceState);
    }

    @Override
    protected void saveInstanceState(Bundle outState) {
        MainActivityModel.getInstance().saveInstanceState(outState);
    }

    @AfterViews
    protected void onViewCreated() {
        setLayoutManager();

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

    void setLayoutManager() {
        switch (MainActivityModel.getInstance().viewMode) {
            case LIST:
                contentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case GRID:
                contentsRecyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.contents_span_count)));
                break;
            default:
                Assert.fail();
        }
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
            contentsRecyclerView.scrollToPosition(0);

            topTextView.setText(MainActivityModel.getInstance().currentAbsolutePath.getAbsolutePath());
        }

        @Override
        public void showToast(int stringResId, int duration) {
            Toast.makeText(MainActivity.this, stringResId, duration).show();
        }

    }
}

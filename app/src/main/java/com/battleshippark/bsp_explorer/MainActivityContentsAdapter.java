package com.battleshippark.bsp_explorer;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import junit.framework.Assert;

public class MainActivityContentsAdapter extends RecyclerView.Adapter<MainActivityContentsViewHolder> {
    private final MainActivityPresenter activityPresenter;
    private final MainActivityModel activityModel;

    public MainActivityContentsAdapter(MainActivityPresenter activityPresenter, MainActivityModel activityModel) {
        this.activityPresenter = activityPresenter;
        this.activityModel = activityModel;
    }

    @Override
    public MainActivityContentsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = null;

        switch (activityModel.viewMode) {
            case LIST:
                view = View.inflate(viewGroup.getContext(), R.layout.list_item_main, null);
                break;
            case GRID:
                view = View.inflate(viewGroup.getContext(), R.layout.grid_item_main, null);
                break;
            default:
                Assert.fail();
        }

        return MainActivityContentsViewHolder.create(view, activityPresenter);
    }

    @Override
    public void onBindViewHolder(MainActivityContentsViewHolder viewHolder, int i) {
        viewHolder.bind(activityModel, i);
    }


    @Override
    public int getItemCount() {
        return activityModel.currentChildrenAbsolutePath.size();
    }
}

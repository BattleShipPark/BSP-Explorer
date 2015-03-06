package com.battleshippark.bsp_explorer;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class MainActivityContentsAdapter extends RecyclerView.Adapter<MainActivityContentsViewHolder> {
    private final MainActivityPresenter activityPresenter;
    private final MainActivityModel activityModel;

    public MainActivityContentsAdapter(MainActivityPresenter activityPresenter, MainActivityModel activityModel) {
        this.activityPresenter = activityPresenter;
        this.activityModel = activityModel;
    }

    @Override
    public MainActivityContentsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.list_item_main, null);

        return new MainActivityContentsViewHolder(view, activityPresenter);
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

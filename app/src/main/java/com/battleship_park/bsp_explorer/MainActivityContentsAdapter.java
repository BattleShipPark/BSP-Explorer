package com.battleship_park.bsp_explorer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class MainActivityContentsAdapter extends RecyclerView.Adapter<MainActivityContentsViewHolder> {
    private final MainActivityModel activityModel;

    public MainActivityContentsAdapter(MainActivityModel activityModel) {
        this.activityModel = activityModel;
    }

    @Override
    public MainActivityContentsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.list_item_main, null);

        return new MainActivityContentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainActivityContentsViewHolder viewHolder, int i) {
        viewHolder.bind(activityModel, i);
    }


    @Override
    public int getItemCount() {
        return activityModel.currentChildren.length;
    }
}

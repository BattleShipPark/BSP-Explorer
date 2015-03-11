package com.battleshippark.bsp_explorer;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import junit.framework.Assert;

import org.lucasr.twowayview.ItemSelectionSupport;

import java.io.File;

public abstract class MainActivityContentsViewHolder extends RecyclerView.ViewHolder {
    protected File absolutePath;

    protected MainActivityContentsViewHolder(View itemView, final MainActivityPresenter activityPresenter) {
        super(itemView);

/*        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivityContentsViewHolder holder = (MainActivityContentsViewHolder) v.getTag();

                if (holder.absolutePath.isDirectory())
                    activityPresenter.goTo(holder.absolutePath);
            }
        });*/

        itemView.setTag(this);
    }

    public abstract void bind(MainActivityModel activityModel, ItemSelectionSupport itemSelection, int pos);

    public static MainActivityContentsViewHolder create(View itemView, MainActivityPresenter activityPresenter) {
        switch (MainActivityModel.getInstance().viewMode) {
            case LIST:
                return new MainActivityContentsListViewHolder(itemView, activityPresenter);
            case GRID:
                return new MainActivityContentsGridViewHolder(itemView, activityPresenter);
            default:
                Assert.fail();
                return null;
        }
    }
}

package com.battleshippark.bsp_explorer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MainActivityContentsGridViewHolder extends MainActivityContentsViewHolder {
    private ImageView iconImageView;
    private TextView fileNameTextView;

    public MainActivityContentsGridViewHolder(View itemView, MainActivityPresenter activityPresenter) {
        super(itemView, activityPresenter);

        iconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);
        fileNameTextView = (TextView) itemView.findViewById(R.id.fileNameTextView);
    }

    @Override
    public void bind(MainActivityModel activityModel, int pos) {
        File file = activityModel.currentChildrenAbsolutePath.get(pos);

        if (file.isDirectory())
            iconImageView.setImageResource(R.drawable.folder_100);
        else
            iconImageView.setImageResource(R.drawable.file_100);

        fileNameTextView.setText(file.getName());

        absolutePath = file.getAbsoluteFile();
    }
}

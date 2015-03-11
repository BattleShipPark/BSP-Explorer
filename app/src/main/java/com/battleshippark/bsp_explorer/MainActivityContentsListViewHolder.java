package com.battleshippark.bsp_explorer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainActivityContentsListViewHolder extends MainActivityContentsViewHolder {
    private ImageView iconImageView;
    private TextView fileNameTextView;
    private TextView fileSizeTextView;
    private TextView lastModifiedTextView;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

    public MainActivityContentsListViewHolder(View itemView, MainActivityPresenter activityPresenter) {
        super(itemView, activityPresenter);

        iconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);
        fileNameTextView = (TextView) itemView.findViewById(R.id.fileNameTextView);
        fileSizeTextView = (TextView) itemView.findViewById(R.id.fileSizeTextView);
        lastModifiedTextView = (TextView) itemView.findViewById(R.id.modifiedDateTextView);

    }

    @Override
    public void bind(MainActivityModel activityModel, int pos) {
        File file = activityModel.currentChildrenAbsolutePath.get(pos);

        if (file.isDirectory())
            iconImageView.setImageResource(R.drawable.folder_100);
        else
            iconImageView.setImageResource(R.drawable.file_100);

        fileNameTextView.setText(file.getName());
        fileSizeTextView.setText(String.valueOf(file.length()));
        lastModifiedTextView.setText(dateFormat.format(file.lastModified()));

        absolutePath = file.getAbsoluteFile();
    }
}

package com.battleship_park.bsp_explorer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainActivityContentsViewHolder extends RecyclerView.ViewHolder {
    private ImageView iconImageView;
    private TextView fileNameTextView;
    private TextView fileSizeTextView;
    private TextView lastModifiedTextView;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy.dd.MM");

    public MainActivityContentsViewHolder(View itemView) {
        super(itemView);

        iconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);
        fileNameTextView = (TextView) itemView.findViewById(R.id.fileNameTextView);
        fileSizeTextView = (TextView) itemView.findViewById(R.id.fileSizeTextView);
        lastModifiedTextView = (TextView) itemView.findViewById(R.id.modifiedDateTextView);
    }

    public void bind(MainActivityModel activityModel, int pos) {
        File file = new File(activityModel.currentChildren[pos]);
        fileNameTextView.setText(file.getName());
        fileSizeTextView.setText(String.valueOf(file.length()));
        lastModifiedTextView.setText(dateFormat.format(file.lastModified()));
    }
}
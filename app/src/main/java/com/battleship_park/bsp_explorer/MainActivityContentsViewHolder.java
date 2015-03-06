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

    private File absolutePath;

    public MainActivityContentsViewHolder(View itemView, final MainActivityPresenter activityPresenter) {
        super(itemView);

        iconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);
        fileNameTextView = (TextView) itemView.findViewById(R.id.fileNameTextView);
        fileSizeTextView = (TextView) itemView.findViewById(R.id.fileSizeTextView);
        lastModifiedTextView = (TextView) itemView.findViewById(R.id.modifiedDateTextView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivityContentsViewHolder holder = (MainActivityContentsViewHolder) v.getTag();

                if (holder.absolutePath.isDirectory())
                    activityPresenter.goTo(holder.absolutePath);
            }
        });

        itemView.setTag(this);
    }

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

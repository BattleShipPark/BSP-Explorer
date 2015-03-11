package com.battleshippark.bsp_explorer;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.lucasr.twowayview.ItemSelectionSupport;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainActivityContentsListViewHolder extends MainActivityContentsViewHolder {
    private final ImageView iconImageView;
    private final TextView fileNameTextView;
    private final TextView fileSizeTextView;
    private final TextView lastModifiedTextView;
    private final CheckBox checkBox;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

    public MainActivityContentsListViewHolder(View itemView, MainActivityPresenter activityPresenter) {
        super(itemView, activityPresenter);

        iconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);
        fileNameTextView = (TextView) itemView.findViewById(R.id.fileNameTextView);
        fileSizeTextView = (TextView) itemView.findViewById(R.id.fileSizeTextView);
        lastModifiedTextView = (TextView) itemView.findViewById(R.id.modifiedDateTextView);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
    }

    @Override
    public void bind(MainActivityModel activityModel, ItemSelectionSupport itemSelection, int pos) {
        File file = activityModel.currentChildrenAbsolutePath.get(pos);

        if (file.isDirectory())
            iconImageView.setImageResource(R.drawable.folder_100);
        else
            iconImageView.setImageResource(R.drawable.file_100);

        fileNameTextView.setText(file.getName());
        fileSizeTextView.setText(String.valueOf(file.length()));
        lastModifiedTextView.setText(dateFormat.format(file.lastModified()));

        if (itemSelection.getChoiceMode() == ItemSelectionSupport.ChoiceMode.MULTIPLE) {
            checkBox.setChecked(itemSelection.isItemChecked(pos));
            checkBox.setVisibility(View.VISIBLE);
        } else {
            checkBox.setVisibility(View.GONE);
        }

        absolutePath = file.getAbsoluteFile();
    }
}

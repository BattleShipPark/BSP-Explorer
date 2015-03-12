package com.battleshippark.bsp_explorer;

import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivityModel {
    private static final MainActivityModel INSTANCE = new MainActivityModel();
    private static final String KEY_VIEW_MODE = "viewMode";

    File currentAbsolutePath;
    List<File> currentChildrenAbsolutePath;
    ViewMode viewMode = ViewMode.LIST;
    BottomLayoutMode bottomLayoutMode = BottomLayoutMode.NORMAL;

    public MainActivityModel() {
        currentChildrenAbsolutePath = new ArrayList<>();
    }

    public static MainActivityModel getInstance() {
        return INSTANCE;
    }

    public void restoreInstanceState(Bundle savedInstanceState) {
        viewMode = (ViewMode) savedInstanceState.getSerializable(KEY_VIEW_MODE);
    }

    public void saveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_VIEW_MODE, viewMode);
    }

    public static enum BottomLayoutMode {
        NORMAL, MULTISELECT, COPIED
    }


    public static enum ViewMode {
        LIST, GRID
    }

}

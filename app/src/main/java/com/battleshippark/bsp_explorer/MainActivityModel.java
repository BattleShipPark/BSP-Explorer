package com.battleshippark.bsp_explorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivityModel {
    private static final MainActivityModel INSTANCE = new MainActivityModel();

    File currentAbsolutePath;
    List<File> currentChildrenAbsolutePath;
    ViewMode viewMode = ViewMode.LIST;

    public MainActivityModel() {
        currentChildrenAbsolutePath = new ArrayList<>();
    }

    public static MainActivityModel getInstance() {
        return INSTANCE;
    }

    public static enum ViewMode {
        LIST, GRID
    }

}

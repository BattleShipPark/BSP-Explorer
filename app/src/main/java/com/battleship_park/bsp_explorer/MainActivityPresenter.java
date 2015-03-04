package com.battleship_park.bsp_explorer;

import java.io.File;
import java.util.Arrays;

public class MainActivityPresenter {
    private final ActivityAccessible activityAccessible;
    private final MainActivityModel model;

    public MainActivityPresenter(ActivityAccessible activityAccessible, MainActivityModel activityModel) {
        this.activityAccessible = activityAccessible;
        this.model = activityModel;
    }

    public void showRoot() {
        model.currentPath = File.listRoots()[0];
        model.currentChildren = model.currentPath.list();
        Arrays.sort(model.currentChildren);

        activityAccessible.refresh();
    }

    public static interface ActivityAccessible {
        void refresh();
    }
}

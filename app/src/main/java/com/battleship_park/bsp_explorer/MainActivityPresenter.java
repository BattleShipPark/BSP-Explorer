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

    public void goTo(File absolutePath) {
        model.currentPath = absolutePath;
        model.currentChildren = absolutePath.list();
        Arrays.sort(model.currentChildren);

        activityAccessible.refresh();
    }

    public void goToRoot() {
        goTo(File.listRoots()[0]);
    }

    public static interface ActivityAccessible {
        void refresh();
    }
}

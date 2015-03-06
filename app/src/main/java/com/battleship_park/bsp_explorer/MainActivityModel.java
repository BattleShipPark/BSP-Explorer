package com.battleship_park.bsp_explorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivityModel {
    File currentAbsolutePath;
    List<File> currentChildrenAbsolutePath;

    public MainActivityModel() {
        currentChildrenAbsolutePath = new ArrayList<>();
    }
}

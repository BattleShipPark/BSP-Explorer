package com.battleshippark.bsp_explorer;

import android.os.Bundle;

import org.androidannotations.annotations.EBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@EBean
public class MainActivityModel {
	private static final String KEY_VIEW_MODE = "viewMode";

	File currentAbsolutePath;
	List<File> currentChildrenAbsolutePath;
	ViewMode viewMode = ViewMode.LIST;
	BottomLayoutMode bottomLayoutMode = BottomLayoutMode.NORMAL;

	private RxEventModel eventModel;

	public MainActivityModel() {
		currentChildrenAbsolutePath = new ArrayList<>();
	}

	public void restoreInstanceState(Bundle savedInstanceState) {
		viewMode = (ViewMode) savedInstanceState.getSerializable(KEY_VIEW_MODE);
	}

	public void saveInstanceState(Bundle outState) {
		outState.putSerializable(KEY_VIEW_MODE, viewMode);
	}

	public void setEventModel(RxEventModel eventModel) {
		this.eventModel = eventModel;
	}

	public enum BottomLayoutMode {
		NORMAL, MULTISELECT, COPIED
	}


	public enum ViewMode {
		LIST, GRID
	}

}

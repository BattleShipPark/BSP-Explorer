package com.battleshippark.bsp_explorer;

import android.content.Context;

import com.squareup.otto.Subscribe;

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

	EventModel eventModel;
	Context context;

	public MainActivityModel() {
		currentChildrenAbsolutePath = new ArrayList<>();
	}

	@Subscribe
	public void onRestoreInstanceState(EventModel.ActivityOnRestoreInstanceState state) {
		viewMode = (ViewMode) state.getSavedInstanceState().getSerializable(KEY_VIEW_MODE);
	}

	@Subscribe
	public void onSaveInstanceState(EventModel.ActivityOnSaveInstanceState state) {
		state.getOutState().putSerializable(KEY_VIEW_MODE, viewMode);
	}

	public void setEventModel(EventModel eventModel) {
		if (this.eventModel != null)
			this.eventModel.bus.unregister(this);

		this.eventModel = eventModel;

		eventModel.bus.register(this);
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public enum BottomLayoutMode {
		NORMAL, MULTISELECT, COPIED
	}


	public enum ViewMode {
		LIST, GRID
	}

}

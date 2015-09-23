package com.battleshippark.bsp_explorer;

import android.widget.Toast;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class MainActivityPresenter {
	private static final int INTERVAL_TO_FINISH = 2000;

	private final ActivityAccessible activityAccessible;
	private final MainActivityModel activityModel;

	private long timeToReadyToFinish = 0L;

	public MainActivityPresenter(ActivityAccessible activityAccessible, MainActivityModel activityModel) {
		this.activityAccessible = activityAccessible;
		this.activityModel = activityModel;
	}

	public void goTo(File absolutePath) {
		activityModel.currentAbsolutePath = absolutePath;

		activityModel.currentChildrenAbsolutePath.clear();

		String[] children = absolutePath.list();
		if (children != null) { /* maybe because of permission? (internal storage) */
			Arrays.sort(children, new Comparator<String>() {
				@Override
				public int compare(String lhs, String rhs) {
					return lhs.compareToIgnoreCase(rhs);
				}
			});


            /* relative path to absolute path */
			for (String path : children)
				activityModel.currentChildrenAbsolutePath.add(new File(absolutePath, path));
		}

		activityAccessible.refresh();
	}

	public void goToRoot() {
		goTo(File.listRoots()[0]);
	}

	public boolean goToParent() {
		if (null == activityModel.currentAbsolutePath.getParentFile())
			return false;

		goTo(activityModel.currentAbsolutePath.getParentFile());
		return true;
	}

	public void refreshDirectory() {
		goTo(activityModel.currentAbsolutePath);
	}

	public boolean isReadyToFinish() {
		if (GregorianCalendar.getInstance().getTimeInMillis() - timeToReadyToFinish <= INTERVAL_TO_FINISH)
			return true;
		return false;
	}

	public void setReadyToFinish() {
		timeToReadyToFinish = GregorianCalendar.getInstance().getTimeInMillis();
	}

	public void onBackPressed() {
		if (activityModel.bottomLayoutMode == MainActivityModel.BottomLayoutMode.NORMAL) {
			if (!goToParent()) {
				if (isReadyToFinish())
					activityAccessible.finish();
				else {
					activityAccessible.showToast(R.string.ready_to_finish_activity, Toast.LENGTH_SHORT);

					setReadyToFinish();
				}
			}
		} else {
			activityAccessible.cancelMultiSelectMode();
		}
	}

	public static interface ActivityAccessible {
		void cancelMultiSelectMode();

		void finish();

		void refresh();

		void showToast(int stringResId, int duration);
	}
}

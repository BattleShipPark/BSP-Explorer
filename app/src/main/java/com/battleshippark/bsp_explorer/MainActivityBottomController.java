package com.battleshippark.bsp_explorer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import junit.framework.Assert;

import java.io.File;

public class MainActivityBottomController {
	private final MainActivity activity;
	private final ViewGroup layout;
	private final MainActivityModel activityModel;

	public MainActivityBottomController(MainActivity activity, ViewGroup bottomViewGroup, MainActivityModel activityModel) {
		this.activity = activity;
		this.layout = bottomViewGroup;
		this.activityModel = activityModel;

		update();
	}

	protected void update() {
		layout.removeAllViews();

		View v;

		switch (activityModel.bottomLayoutMode) {
			case NORMAL:
				v = addView(R.drawable.plus_100, R.string.menu_new);
				v.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {

					}
				});

				v = addView(R.drawable.view_100, R.string.menu_view_mode);
				v.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						showViewModePopup();
					}
				});
				break;
			case MULTISELECT:
				v = addView(R.drawable.copy_100, R.string.menu_copy);
				v.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {

					}
				});

				v = addView(R.drawable.cut_100, R.string.menu_cut);
				v.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {

					}
				});

				v = addView(R.drawable.delete_100, R.string.menu_delete);
				v.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						deleteFiles();
					}
				});
				v = addView(R.drawable.cancel_100, R.string.menu_cancel);
				v.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						activity.cancelMultiSelectMode();
					}
				});
				break;
			case COPIED:
				v = addView(R.drawable.paste_100, R.string.menu_paste);
				v.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {

					}
				});

				v = addView(R.drawable.cancel_100, R.string.menu_cancel);
				v.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {

					}
				});
				break;
			default:
				Assert.fail();
		}
	}

	private void deleteFiles() {
		final SparseBooleanArray arr = activity.itemSelection.getCheckedItemPositions();

		new AlertDialog.Builder(activity).setMessage(activity.getResources().getQuantityString(R.plurals.confirm_delete, arr.size(), arr.size()))
				.setNegativeButton(android.R.string.no, null)
				.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						for (int index = 0; index < arr.size(); index++) {
							File file = activityModel.currentChildrenAbsolutePath.get(arr.keyAt(index));
							file.delete();
						}
						activity.activityPresenter.refreshDirectory();
						activity.cancelMultiSelectMode();
					}
				})
				.show();
	}

	private View addView(int drawableResId, int stringResId) {
		LinearLayout ll = (LinearLayout) View.inflate(activity, R.layout.menu_main, null);
		ll.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

		((ImageView) ll.findViewById(R.id.image)).setImageResource(drawableResId);
		((TextView) ll.findViewById(R.id.text)).setText(stringResId);

		layout.addView(ll);

		return ll;
	}

	private void showViewModePopup() {
		CharSequence[] items = new String[]{activity.getString(R.string.view_mode_list), activity.getString(R.string.view_mode_grid)};

		new AlertDialog.Builder(activity).setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
					case 0:
						activityModel.viewMode = MainActivityModel.ViewMode.LIST;
						activity.setLayoutManager();
						break;
					case 1:
						activityModel.viewMode = MainActivityModel.ViewMode.GRID;
						activity.setLayoutManager();
						break;
					default:
						Assert.fail();
				}
			}
		}).create().show();
	}
}

package com.battleshippark.bsp_explorer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import junit.framework.Assert;

public class MainActivityBottomController {
    private final MainActivity activity;
    private final ViewGroup layout;
    private final MainActivity.BottomLayoutMode mode;

    public MainActivityBottomController(MainActivity activity, ViewGroup bottomViewGroup, MainActivity.BottomLayoutMode mode) {
        this.activity = activity;
        this.layout = bottomViewGroup;
        this.mode = mode;

        update();
    }

    private void update() {
        layout.removeAllViews();

        View v;

        switch (mode) {
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
                        MainActivityModel.getInstance().viewMode = MainActivityModel.ViewMode.LIST;
                        activity.contentsRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
                        break;
                    case 1:
                        MainActivityModel.getInstance().viewMode = MainActivityModel.ViewMode.GRID;
                        activity.contentsRecyclerView.setLayoutManager(new GridLayoutManager(activity, activity.getResources().getInteger(R.integer.contents_span_count)));
                        break;
                    default:
                        Assert.fail();
                }
            }
        }).create().show();
    }
}

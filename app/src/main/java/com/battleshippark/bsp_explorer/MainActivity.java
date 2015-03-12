package com.battleshippark.bsp_explorer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Assert;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.lucasr.twowayview.ItemClickSupport;
import org.lucasr.twowayview.ItemSelectionSupport;
import org.lucasr.twowayview.TwoWayLayoutManager;
import org.lucasr.twowayview.widget.ListLayoutManager;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewById(R.id.topView)
    protected TextView topTextView;

    @ViewById(R.id.contentsView)
    protected EmptyRecyclerView contentsRecyclerView;

    @ViewById(R.id.emptyTextView)
    protected TextView emptyTextView;

    @ViewById(R.id.bottomLayout)
    protected ViewGroup bottomViewGroup;

    protected ItemSelectionSupport itemSelection;
    protected RecyclerView.Adapter contentsAdapter;

    protected MainActivityPresenter activityPresenter;
    private MainActivityBottomController bottomController;


    @Override
    protected void restoreIntent(Intent intent) {

    }

    @Override
    protected void restoreInstanceState(Bundle savedInstanceState) {
        MainActivityModel.getInstance().restoreInstanceState(savedInstanceState);
    }

    @Override
    protected void saveInstanceState(Bundle outState) {
        MainActivityModel.getInstance().saveInstanceState(outState);
    }

    @AfterViews
    protected void onViewCreated() {
        setLayoutManager();

        activityPresenter = new MainActivityPresenter(new ActivityAccessible(), MainActivityModel.getInstance());

        itemSelection = ItemSelectionSupport.addTo(contentsRecyclerView);

        contentsAdapter = new MainActivityContentsAdapter(activityPresenter, MainActivityModel.getInstance(), itemSelection);
        contentsRecyclerView.setAdapter(contentsAdapter);
        contentsRecyclerView.setEmptyView(emptyTextView);

        final ItemClickSupport itemClick = ItemClickSupport.addTo(contentsRecyclerView);
        itemClick.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView recyclerView, View view, int position, long id) {
                ItemSelectionSupport itemSelection = ItemSelectionSupport.from(contentsRecyclerView);
                if (itemSelection.getChoiceMode() == ItemSelectionSupport.ChoiceMode.MULTIPLE) {
                    boolean checked = itemSelection.isItemChecked(position);
                    itemSelection.setItemChecked(position, checked);
                    contentsAdapter.notifyDataSetChanged();
                } else {
                    MainActivityContentsViewHolder holder = (MainActivityContentsViewHolder) view.getTag();

                    if (holder.absolutePath.isDirectory())
                        activityPresenter.goTo(holder.absolutePath);
                }
            }
        });

        itemClick.setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(RecyclerView recyclerView, View view, int position, long id) {
                ItemSelectionSupport itemSelection = ItemSelectionSupport.from(contentsRecyclerView);
                if (itemSelection.getChoiceMode() != ItemSelectionSupport.ChoiceMode.MULTIPLE) {
                    setMultiSelectMode(position);
                }
                return true;
            }
        });

        bottomController = new MainActivityBottomController(this, bottomViewGroup);

        activityPresenter.goToRoot();
    }

    @Override
    public void onBackPressed() {
        activityPresenter.onBackPressed();
    }


    protected void setBottomLayoutMode(MainActivityModel.BottomLayoutMode mode) {
        MainActivityModel.getInstance().bottomLayoutMode = mode;
        bottomController.update();
    }

    protected void setLayoutManager() {
        switch (MainActivityModel.getInstance().viewMode) {
            case LIST:
                contentsRecyclerView.setLayoutManager(new ListLayoutManager(this, TwoWayLayoutManager.Orientation.VERTICAL));
                break;
            case GRID:
                contentsRecyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.contents_span_count)));
                break;
            default:
                Assert.fail();
        }
    }

    protected void cancelMultiSelectMode() {
        itemSelection.setChoiceMode(ItemSelectionSupport.ChoiceMode.NONE);
        itemSelection.clearChoices();
        contentsAdapter.notifyDataSetChanged();

        setBottomLayoutMode(MainActivityModel.BottomLayoutMode.NORMAL);
    }

    private void setMultiSelectMode(int position) {
        itemSelection.setChoiceMode(ItemSelectionSupport.ChoiceMode.MULTIPLE);
        itemSelection.setItemChecked(position, true);
        contentsAdapter.notifyDataSetChanged();

        setBottomLayoutMode(MainActivityModel.BottomLayoutMode.MULTISELECT);
    }

    private class ActivityAccessible implements MainActivityPresenter.ActivityAccessible {
        @Override
        public void cancelMultiSelectMode() {
            MainActivity.this.cancelMultiSelectMode();
        }

        @Override
        public void finish() {
            MainActivity.this.finish();
        }

        @Override
        public void refresh() {
            contentsAdapter.notifyDataSetChanged();
            contentsRecyclerView.scrollToPosition(0);

            topTextView.setText(MainActivityModel.getInstance().currentAbsolutePath.getAbsolutePath());
        }

        @Override
        public void showToast(int stringResId, int duration) {
            Toast.makeText(MainActivity.this, stringResId, duration).show();
        }

    }
}

package com.battleshippark.bsp_explorer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null)
            restoreIntent(getIntent());

        if (savedInstanceState != null)
            restoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveInstanceState(outState);
    }


    protected abstract void restoreInstanceState(Bundle savedInstanceState);

    protected abstract void restoreIntent(Intent intent);

    protected abstract void saveInstanceState(Bundle outState);
}

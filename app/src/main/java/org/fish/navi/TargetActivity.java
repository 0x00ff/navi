package org.fish.navi;

import android.support.v4.app.Fragment;

public class TargetActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TargetFragment();
    }
}


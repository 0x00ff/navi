package org.fish.navi;

import android.support.v4.app.Fragment;

import java.util.UUID;

public class TargetActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        UUID targetId = (UUID)getIntent()
                .getSerializableExtra(TargetFragment.EXTRA_TARGET_ID);
        return TargetFragment.newInstance(targetId);
    }
}


package org.fish.navi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

public abstract class SingleFragmentActivity extends ActionBarActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager manager = getSupportFragmentManager();
        Fragment targetFragment = manager.findFragmentById(R.id.activityContainer);

        if (targetFragment == null) {
            targetFragment = createFragment();
            manager.beginTransaction()
                    .add(R.id.activityContainer, targetFragment)
                    .commit();
        }
    }
}

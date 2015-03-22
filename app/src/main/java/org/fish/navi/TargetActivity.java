package org.fish.navi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


public class TargetActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        FragmentManager manager = getSupportFragmentManager();
        Fragment targetFragment = manager.findFragmentById(R.id.targetContainer);

        if (targetFragment == null) {
            targetFragment = new TargetFragment();
            manager.beginTransaction()
                   .add(R.id.targetContainer, targetFragment)
                   .commit();
        }
    }
}


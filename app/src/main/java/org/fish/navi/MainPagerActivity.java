package org.fish.navi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.security.InvalidParameterException;

public class MainPagerActivity extends FragmentActivity {
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewPager = new ViewPager(this);
        viewPager.setId(R.id.viewPager);
        setContentView(viewPager);

        FragmentManager manager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public int getCount() {
                return 3;
            }
            @Override
            public Fragment getItem(int pos) {
                switch (pos){
                    case 0: // List
                        return new TargetsFragment();
                    case 1: // navigation
                        return new TargetsFragment();
                    case 2: // map
                        return new TargetsFragment();
                    default:
                        throw new InvalidParameterException();
                }
            }
        });

        viewPager.setCurrentItem(1); // start from navigation
    }
}

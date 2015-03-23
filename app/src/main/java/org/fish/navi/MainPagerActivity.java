package org.fish.navi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.security.InvalidParameterException;

public class MainPagerActivity extends ActionBarActivity {
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
                        return new NavigationFragment();
                    case 2: // map
                        return new MapFragment();
                    default:
                        throw new InvalidParameterException();
                }
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) { }
            public void onPageScrolled(int pos, float posOffset, int posOffsetPixels) {
            }
            public void onPageSelected(int pos) {
                switch (pos){
                    case 0: // List
                        setTitle(getString(R.string.targets_title));
                        break;
                    case 1: // navigation
                        setTitle(getString(R.string.navigation_title));
                        break;
                    case 2: // map
                        setTitle(getString(R.string.map_title));
                        break;
                    default:
                        throw new InvalidParameterException();
                }
            }
        });

        viewPager.setCurrentItem(1); // start from navigation
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

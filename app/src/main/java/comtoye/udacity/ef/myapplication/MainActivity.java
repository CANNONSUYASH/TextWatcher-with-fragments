package comtoye.udacity.ef.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import comtoye.udacity.ef.myapplication.fragments.OneFragment;
import comtoye.udacity.ef.myapplication.fragments.TwoFragment;

public class MainActivity extends AppCompatActivity implements OneFragment.OnFragmentInteractionListener{

    static OneFragment oneFragment;
    static TwoFragment twoFragment;

    PagerAdapter pagerAdapter;
    ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();

        viewPager = (ViewPager)findViewById(R.id.viewPage);
        setupViewPage(viewPager);

        tabLayout = (TabLayout)findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void setupViewPage (ViewPager viewPager){
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new OneFragment(), "");
        pagerAdapter.addFragment(new TwoFragment(), "");
        viewPager.setAdapter(pagerAdapter);
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

    @Override
    public void onFragmentInteraction(String details) {
        pagerAdapter.onFragmentInteraction(details);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public static class PagerAdapter extends FragmentPagerAdapter implements OneFragment.OnFragmentInteractionListener{
        private final List<Fragment> tabList = new ArrayList<>();
        private final List<String> tabTitle = new ArrayList<>();

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return oneFragment;
                case 1:
                    return twoFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return " Page " + position;
        }

        public void addFragment(Fragment fragment, String title){
            tabList.add(fragment);
            tabTitle.add(title);
        }

        @Override
        public void onFragmentInteraction(String details) {
            twoFragment.onFragmentInteraction(details);
        }
    }


}

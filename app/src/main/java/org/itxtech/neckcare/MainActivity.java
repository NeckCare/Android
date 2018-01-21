package org.itxtech.neckcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import org.itxtech.neckcare.fragment.HardwareSettingsFragment;
import org.itxtech.neckcare.fragment.HomeFragment;
import org.itxtech.neckcare.fragment.ToolbarFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String LAUNCH_FRAGMENT = "org.itxtech.neckcare.MainActivity.LAUNCH_FRAGMENT";
    public static final int FRAGMENT_NONE = -1;
    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_HW_SETTINGS = 1;

    private ToolbarFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.textView_nav_version)).setText(getString(R.string.nav_version) + " " + BuildConfig.VERSION_NAME);
        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.textView_nav_git_commit)).setText(getString(R.string.nav_git_commit) + " " + BuildConfig.GIT_COMMIT);

        updateContent(getIntent());
    }

    private void updateContent(Intent intent){
        int fragment = intent.getIntExtra(LAUNCH_FRAGMENT, FRAGMENT_NONE);

        switch (fragment) {
            case FRAGMENT_HOME:
                switchFragment(HomeFragment.class);
                break;
            case FRAGMENT_HW_SETTINGS:
                switchFragment(HardwareSettingsFragment.class);
                break;
        }
        if (currentFragment == null) {
            switchFragment(HomeFragment.class);
        }
    }

    private void switchFragment(Class fragmentClass) {
        if (currentFragment == null || fragmentClass != currentFragment.getClass()) {
            try {
                ToolbarFragment fragment = (ToolbarFragment) fragmentClass.newInstance();
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.content, fragment).commit();
                currentFragment = fragment;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                switchFragment(HomeFragment.class);
                break;
            case R.id.nav_hw_settings:
                switchFragment(HardwareSettingsFragment.class);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

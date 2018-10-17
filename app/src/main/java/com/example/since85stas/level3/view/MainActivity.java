package com.example.since85stas.level3.view;

/*

 */

import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.bumptech.glide.Glide;
import com.example.since85stas.level3.R;
import com.example.since85stas.level3.other.CircleTransform;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // tags used to attach the fragments
    private static final String TAG_USER = "user";
    private static final String TAG_REPO = "repo";
    private static       String CURRENT_TAG = "user";

    // index to identify current nav menu item
    public static int mNavItemIndex = 0;

    // переменные для NavigationDrawer
    private NavigationView  mNavigationView;
    private DrawerLayout mDrawerLayout;
    private View mNavHeader;
    private Handler mHandler;
    private Toolbar mToolbar;
    private String[] activityTitles = {"User detail", "Repositories"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mHandler = new Handler();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        // setup nav view header, странно почему-то не загружается хидер, пока не могу понять
        // Navigation view header
        mNavHeader = mNavigationView.getHeaderView(0);
        TextView txtName = (TextView) mNavHeader.findViewById(R.id.name);
        TextView txtWebsite = (TextView) mNavHeader.findViewById(R.id.website);
        ImageView imgNavHeaderBg = (ImageView) mNavHeader.findViewById(R.id.img_header_bg);
        ImageView imgProfile = (ImageView) mNavHeader.findViewById(R.id.img_profile);

        // name, website
        txtName.setText("Stanislav Batura");
        txtWebsite.setText("stanislav.batura85@gmail.com");

//        Glide.with(this).load(R.drawable.drawer_back).into(imgNavHeaderBg);
//        Glide.with(this).load(R.drawable.cat_portrait_cute_animal)
//                .bitmapTransform(new CircleTransform(this))
//                .into(imgProfile);
        //loadNavHeader();

        // set view logic
        setUpNavigationView();

        if (savedInstanceState == null) {
            mNavItemIndex = 0;
            CURRENT_TAG = TAG_USER;
            loadHomeFragment();
        }
    }

    private void loadNavHeader() {

        // Navigation view header
        mNavHeader = mNavigationView.getHeaderView(0);
        TextView txtName = (TextView) mNavHeader.findViewById(R.id.name);
        TextView txtWebsite = (TextView) mNavHeader.findViewById(R.id.website);
        ImageView imgNavHeaderBg = (ImageView) mNavHeader.findViewById(R.id.img_header_bg);
        ImageView imgProfile = (ImageView) mNavHeader.findViewById(R.id.img_profile);
        // name, website
        txtName.setText("Stanislav Batura");
        txtWebsite.setText("stanislav.batura85@gmail.com");

        //imgNavHeaderBg.setImageResource(R.drawable.before_cookie);
        //imgNavHeaderBg.setImageResource(R.drawable.drawer_back);
        Glide.with(this).load(R.drawable.drawer_back).into(imgNavHeaderBg);

        Glide.with(this).load(R.drawable.cat_portrait_cute_animal)
                .bitmapTransform(new CircleTransform(this))
                .into(imgProfile);

        //mNavigationView.geth
    }

    // настраиваем NavigationDrawer
    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    case R.id.nav_user:
                        mNavItemIndex = 0;
                        CURRENT_TAG = TAG_USER;
                        break;
                    case R.id.nav_repositories:
                        mNavItemIndex = 1;
                        CURRENT_TAG   = TAG_REPO ;
                        break;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);
                loadHomeFragment();
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            mDrawerLayout.closeDrawers();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
//        Runnable mPendingRunnable = new Runnable() {
//            @Override
//            public void run() {
//                // update the main content by replacing fragments
//                MvpAppCompatFragment fragment = getHomeFragment();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
//                        android.R.anim.fade_out);
//                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
//                fragmentTransaction.commitAllowingStateLoss();
//            }
//        };

        Runnable  mPendingRunnable = () -> {
            // update the main content by replacing fragments
            MvpAppCompatFragment fragment = getHomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
            fragmentTransaction.commitAllowingStateLoss();
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        //Closing drawer on item click
        mDrawerLayout.closeDrawers();
    }

    private MvpAppCompatFragment getHomeFragment() {
        switch (mNavItemIndex) {
            case 0:
                // userDetail
                UserDetailFragment userDetailFragment = new UserDetailFragment();
                return userDetailFragment;
            case 1:
                // photos
                RepositoriesFragment repositoriesFragment = new RepositoriesFragment();
                return repositoriesFragment;
            default:
                return new UserDetailFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[mNavItemIndex]);
    }

    private void selectNavMenu() {
        mNavigationView.getMenu().getItem(mNavItemIndex).setChecked(true);
    }


}

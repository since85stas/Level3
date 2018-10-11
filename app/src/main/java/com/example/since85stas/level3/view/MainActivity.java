package com.example.since85stas.level3.view;

/*
    Стыдно конечно сдавать такой недоделанный код, но наверное лучше всетки сдать, что есть, чем
    вообще не сдавать. На выходных обещаю, все привести в более менее человеческий вид, сейчас
    банально не успел, так как долго возился с настройками.
 */

import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.since85stas.level3.R;

public class MainActivity extends MvpAppCompatActivity  {

    // переменные для NavigationDrawer
    private NavigationView  mNavigationView;
    private DrawerLayout mDrawerLayout;
    private View mNavHeader;
    private Toolbar mToolbar;

    /* Фрагменты пока задал через разметку, но подозреваю что это плохо,
       потом то переделую по нормальному :)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setupNavigationDrawer();
    }

    // настраиваем NavigationDrawer, пока заготовка
    private void setupNavigationDrawer() {
        Handler handler = new Handler();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        // get menu
        Menu menu = mNavigationView.getMenu();

        // initializing navigation menu
        //setUpNavigationView();
    }


}

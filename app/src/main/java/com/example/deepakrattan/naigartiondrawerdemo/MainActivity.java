package com.example.deepakrattan.naigartiondrawerdemo;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ListView navigationDrawerListView;
    private String[] drawerItems;
    private Toolbar toolbar;

    //To have hamburger icon ,we use ActionBarDrawerToggle
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewById
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationDrawerListView = (ListView) findViewById(R.id.navigationDrawerListView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //Drawer items
        drawerItems = new String[]{"Home", "Settings", "Profile", "Logout"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, drawerItems);
        navigationDrawerListView.setAdapter(adapter);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        // Setting ActionBarDrawerToggle on DrawerLayout
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        // Enabling Up navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Enabling Home button
        getSupportActionBar().setHomeButtonEnabled(true);


        //Setting item click listener for the ListView
        navigationDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = drawerItems[position];
                //Toast.makeText(MainActivity.this, item + " Selected ", Toast.LENGTH_LONG).show();

                selectItem(position);
                //closing the drawer
                drawerLayout.closeDrawer(navigationDrawerListView);
            }
        });

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Handling the touch event of app icon
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //Swaps Fragments in the main content view
    public void selectItem(int pos) {
        String item = drawerItems[pos];
        //Set Title
        setTitle(item);
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Toast.makeText(MainActivity.this, item + " Selected ", Toast.LENGTH_LONG).show();
        switch (pos) {
            case 0:
                fragment = new HomeFragment();
                fragmentTransaction.replace(R.id.contentFrame, fragment);
                fragmentTransaction.commit();
                break;
            case 1:
                fragment = new SettingFragment();
                fragmentTransaction.replace(R.id.contentFrame, fragment);
                fragmentTransaction.commit();
                break;
        }

    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        toolbar.setTitle(title);
    }
}

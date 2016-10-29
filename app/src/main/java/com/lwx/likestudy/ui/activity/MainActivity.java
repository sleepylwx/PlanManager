package com.lwx.likestudy.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.lwx.likestudy.R;
import com.lwx.likestudy.adapter.MainPagerAdapter;
import com.lwx.likestudy.ui.fragment.RecentFragment;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.lwx.likestudy.ui.fragment.Test;

import org.w3c.dom.Text;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationBar bottomNavigationBar;
    Toolbar toolbar;
    ViewPager viewPager;
    String[] titles;
    TextView frogView;
    boolean floatingButtonOpen = false;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("最近添加的计划");

        setSupportActionBar(toolbar);


        titles = getResources().getStringArray(R.array.titles);
        Fragment[] fragments = new Fragment[titles.length];
        Log.e("find",String.valueOf(titles.length));
        for(int i = 0; i < titles.length; ++i){

            fragments[i] = new RecentFragment();
        }
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(),titles,fragments);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount() - 1);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Empty
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Empty
            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationBar.selectTab(position);

            }
        });





        final FloatingActionsMenu fabMenu = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        fabMenu.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(floatingButtonOpen == false){

                    frogView.setVisibility(View.VISIBLE);

                    floatingButtonOpen = true;
                    fabMenu.toggle();
                }
                else{

                    frogView.setVisibility(View.GONE);
                    floatingButtonOpen = false;
                    fabMenu.collapse();
                }


            }
        });

        FloatingActionButton fabB = (FloatingActionButton) findViewById(R.id.action_b);
        fabB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,SetPlanActivity.class);
                startActivity(intent);
            }
        });

        frogView = (TextView)findViewById(R.id.frogview);
        frogView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                frogView.setVisibility(View.GONE);
                fabMenu.collapse();
                floatingButtonOpen = false;

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_bar);
        bottomNavigationBar.setAutoHideEnabled(false);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_main_nav_play_list,"最近"))
                .addItem(new BottomNavigationItem(R.drawable.ic_main_nav_local_files,"科目"))
                .addItem(new BottomNavigationItem(R.drawable.ic_main_nav_music,"方式"))
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {


                if(floatingButtonOpen == true){

                    frogView.setVisibility(View.GONE);
                    fabMenu.collapse();
                    floatingButtonOpen = false;
                }
                switch (position){

                    case 0:
                        toolbar.setTitle(titles[0]);
                        viewPager.setCurrentItem(position);
                        break;
                    case 1:
                        toolbar.setTitle(titles[1]);
                        viewPager.setCurrentItem(position);
                        break;
                    case 2:
                        toolbar.setTitle(titles[2]);
                        viewPager.setCurrentItem(position);
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        bottomNavigationBar.selectTab(0);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mode_self_learning) {

            Intent intent = new Intent(MainActivity.this,SelfLearingActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

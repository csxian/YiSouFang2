package com.example.jon.yisoufang2.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.jon.yisoufang2.R;
import com.example.jon.yisoufang2.adapter.DropMenuAdapter;
import com.example.jon.yisoufang2.adapter.RentDropMenuAdapter;
import com.example.jon.yisoufang2.db.RegionDAO;
import com.example.jon.yisoufang2.fragment.BMapFragment;
import com.example.jon.yisoufang2.fragment.RentHouseFragment;
import com.example.jon.yisoufang2.fragment.SecondHandHouseFragment;

import java.util.List;

import butterknife.ButterKnife;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tv_citySelect;
    private TextView tv_search;


    private FragmentManager fm;
    private RentHouseFragment rentHouseFragment;
    private SecondHandHouseFragment secondHandHouseFragment;
    private BMapFragment bMapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().findItem(R.id.nav_renthouse).setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);


        initView();

        //初始化租房界面
        fm = getFragmentManager();
        rentHouseFragment = new RentHouseFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frame_layout,rentHouseFragment);
        ft.commit();

    }

    private void initView(){
        tv_citySelect = (TextView) findViewById(R.id.tv_citySelect);
        tv_citySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CitySelecterActivity.class);
                startActivityForResult(intent,1);

            }
        });

        Intent intent = getIntent();
        String cityName = intent.getStringExtra("cityName");
        Log.d("cn",cityName);
        tv_citySelect.setText(cityName.trim());

        tv_search = (TextView) findViewById(R.id.search_tv);
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                intent.putExtra("cityName",tv_citySelect.getText().toString());
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String region = data.getStringExtra("cityName");//设置城市
                    tv_citySelect.setText(region);
                    int parent = RegionDAO.getParentIdByName(region);
                    List<String> list = RegionDAO.getCitybyParent(parent);
                    if(DropMenuAdapter.singleListView != null) {
                        DropMenuAdapter.singleListView.setList(list, -1);
                    }
                    if(RentDropMenuAdapter.singleListView != null) {
                        RentDropMenuAdapter.singleListView.setList(list, -1);
                    }
                    if(rentHouseFragment.isVisible()){
                        Log.d("fg","true");
                        FragmentTransaction ft = fm.beginTransaction();
                        rentHouseFragment = new RentHouseFragment();
                        ft.replace(R.id.frame_layout,rentHouseFragment);
                        ft.commit();
                    }
                    if(secondHandHouseFragment !=null && secondHandHouseFragment.isVisible()){
                        Log.d("fg","true");
                        FragmentTransaction ft = fm.beginTransaction();
                        secondHandHouseFragment = new SecondHandHouseFragment();
                        ft.replace(R.id.frame_layout,secondHandHouseFragment);
                        ft.commit();
                    }
                    if(bMapFragment != null && bMapFragment.isVisible()){
                        bMapFragment.updateDataCallback(region);
                    }
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction ft = fm.beginTransaction();
        hideAllFragment(ft);

        if (id == R.id.nav_renthouse) {
            if(rentHouseFragment != null){
                ft.show(rentHouseFragment);
            }else{
                rentHouseFragment = new RentHouseFragment();
                ft.add(R.id.frame_layout,rentHouseFragment);
            }
        } else if (id == R.id.nav_secondhand) {
            if(secondHandHouseFragment != null){
                ft.show(secondHandHouseFragment);
            }else{
                secondHandHouseFragment = new SecondHandHouseFragment();
                ft.add(R.id.frame_layout,secondHandHouseFragment);
            }

        } else if (id == R.id.nav_bmap) {
            if(bMapFragment != null){
                ft.show(bMapFragment);
            }else{
                bMapFragment = new BMapFragment();
                ft.add(R.id.frame_layout,bMapFragment);
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        ft.commit();
        return true;
    }

    private void hideAllFragment(FragmentTransaction ft){
        if(rentHouseFragment != null){
            ft.hide(rentHouseFragment);
        }
        if(secondHandHouseFragment != null){
            ft.hide(secondHandHouseFragment);
        }
        if(bMapFragment != null){
            ft.hide(bMapFragment);
        }
    }



//

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}

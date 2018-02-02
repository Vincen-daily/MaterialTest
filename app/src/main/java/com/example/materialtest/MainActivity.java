package com.example.materialtest;

import android.content.Context;
import android.support.annotation.NonNull;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private PicAdapter picAdapter;
    private String TAG="=========";

    private Pic [] pics={new Pic("car1",R.mipmap.images1),new Pic("car2",R.mipmap.images2),
            new Pic("car3",R.mipmap.images3),new Pic("car4",R.mipmap.images4),
            new Pic("car5",R.mipmap.images5),new Pic("car6",R.mipmap.images6),
            new Pic("car7",R.mipmap.images7),new Pic("car8",R.mipmap.images8),
            new Pic("car9",R.mipmap.images9),new Pic("car10",R.mipmap.images10)};

    List<Pic> picList=new ArrayList<>();

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                Toast.makeText(this,"backup",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this,"settings",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
                default:
                    break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate");

        toolbar=findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        floatingActionButton=findViewById(R.id.fab);
        recyclerView=findViewById(R.id.recycler);
        refreshLayout=findViewById(R.id.refresh);

        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(() -> refreshPic());
        initPics();
        setSupportActionBar(toolbar);
        navigationView.setItemIconTintList(null);
        ActionBar actionBar=getSupportActionBar();

        picAdapter=new PicAdapter(picList);
        GridLayoutManager layoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(picAdapter);

        floatingActionButton.setOnClickListener(v -> {
           // Toast.makeText(MainActivity.this,"fab",Toast.LENGTH_SHORT).show();
            Snackbar.make(v,"data deleted",Snackbar.LENGTH_SHORT)
                    .setAction("undo", v1 ->
                            Toast.makeText(MainActivity.this,"data restored",Toast.LENGTH_SHORT).show()).show();
        });
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.home);

        }


        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(item -> {

            drawerLayout.closeDrawers();
            return true;
        });


    }

    private void refreshPic() {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(() -> {
                initPics();
                picAdapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            });
        }).start();
    }

    private void initPics() {
        picList.clear();
        for (int i=0;i<30;i++){
            Random random=new Random();
            int index=random.nextInt(pics.length);
            picList.add(pics[index]);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"resume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"start");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"pause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"destroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"restart");
    }

}

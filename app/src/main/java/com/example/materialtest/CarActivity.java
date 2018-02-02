package com.example.materialtest;


import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class CarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView carView;
    private TextView car;
    private CollapsingToolbarLayout collapsingToolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        toolbar=findViewById(R.id.toolbar);
        car=findViewById(R.id.car_text);
        carView=findViewById(R.id.carImage);
        collapsingToolbar=findViewById(R.id.collapsing_toolbar);

        Intent intent=getIntent();
        String carName=intent.getStringExtra("carName");
        int carImage=intent.getIntExtra("carView",0);

        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(carName);
        Glide.with(this).load(carImage).into(carView);
        String carContent=useCarContent(carName);
        car.setText(carContent);



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String useCarContent(String carName) {
        StringBuilder carContent=new StringBuilder();
        for (int i=0;i<555;i++){
            carContent.append(carName);
        }
        return carContent.toString();

    }
}

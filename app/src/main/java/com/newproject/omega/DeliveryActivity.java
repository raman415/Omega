package com.newproject.omega;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class DeliveryActivity extends AppCompatActivity {
    
    private RecyclerView deliveryRecyclerView;
    private Button changeOrAddNewAddressBtn;
    public static final int select_address=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Delivery");


        deliveryRecyclerView = findViewById(R.id.delivery_reyclerview);
        changeOrAddNewAddressBtn = findViewById(R.id.change_or_add_addressBtn);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        deliveryRecyclerView.setLayoutManager(linearLayoutManager);


        List<CartItemModel> cartItemModelList = new ArrayList<>();

        cartItemModelList.add(new CartItemModel(0, R.mipmap.transiphonex, "iPhone x",
                2, "$1400", "$1700", 1, 0, 0));
        cartItemModelList.add(new CartItemModel(0, R.mipmap.transiphonex, "iPhone x",
                // testing regarding offers applied.
                0, "$1400", "$1700", 1, 1, 0));
        cartItemModelList.add(new CartItemModel(0, R.mipmap.transiphonex, "iPhone x",
                2, "$1400", "$1700", 1, 2, 0));
        cartItemModelList.add(new CartItemModel(1,"Price (3 items) ","$ 1800/-",
                "Free","$1800/-","" +
                "$100"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        deliveryRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        changeOrAddNewAddressBtn.setVisibility(View.VISIBLE);

        changeOrAddNewAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myAddressesIntent =
                        new Intent(DeliveryActivity.this,MyAddressesActivity.class);
                myAddressesIntent.putExtra("MODE_VALUE",select_address);
                startActivity(myAddressesIntent);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
      // Over here we have placed back button.
       if( id == android.R.id.home){
           finish();
           return true;
       }
        return super.onOptionsItemSelected(item);
    }

}

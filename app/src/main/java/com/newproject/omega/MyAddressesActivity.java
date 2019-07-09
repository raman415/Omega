package com.newproject.omega;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import static com.newproject.omega.DeliveryActivity.select_address;
import static com.newproject.omega.MyAccountFragment.MANAGE_ADDRESS;

public class MyAddressesActivity extends AppCompatActivity {

    private RecyclerView myAddressesRecyclerView;
    private static AddressesAdapter addressesAdapter;
    private Button deliverHereBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addresses);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Addresses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myAddressesRecyclerView = findViewById(R.id.my_addresses_recyclerview);
        deliverHereBtn = findViewById(R.id.my_deliver_here_btn);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myAddressesRecyclerView.setLayoutManager(linearLayoutManager);

        List<AddressesModel> addressesModelList = new ArrayList<>();
        addressesModelList.add(new AddressesModel("Anmol Devgan","1067 Ave. Belec, LaSalle, Montreal","H8N1R5",true));
        addressesModelList.add(new AddressesModel("Raman Kumar","1067 Ave. Belec, LaSalle, Montreal","H0N1R5",false));
        addressesModelList.add(new AddressesModel("Hanish Don","1067 Ave. Belec, LaSalle, Montreal","Z8N1R5",false ));
        addressesModelList.add(new AddressesModel("Mayank Khatri","1067 Ave. Belec, LaSalle, Montreal","H8N1R5",false));
        addressesModelList.add(new AddressesModel("IBM","1067 Ave. Belec, LaSalle, Montreal","H8N1R5",false));
        addressesModelList.add(new AddressesModel("Aman bhai","1067 Ave. Belec, LaSalle, Montreal","H8N7R5",false));
        addressesModelList.add(new AddressesModel("Baby Balu","1067 Ave. Belec, LaSalle, Montreal","H8N1R5",false));
        addressesModelList.add(new AddressesModel("Anmol Devgan","1067 Ave. Belec, LaSalle, Montreal","H8N1R5",false));

         int catchedModeValue = getIntent().getIntExtra("MODE_VALUE",-1);

         if (catchedModeValue == select_address){
             deliverHereBtn.setVisibility(View.VISIBLE);
         }else if (catchedModeValue == MANAGE_ADDRESS){
             deliverHereBtn.setVisibility(View.GONE);
         }

         addressesAdapter = new AddressesAdapter(addressesModelList,catchedModeValue);
        myAddressesRecyclerView.setAdapter(addressesAdapter);

        // this code simple disabled any default or customized animation.
        ((SimpleItemAnimator)myAddressesRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        // this method refresh full list
        addressesAdapter.notifyDataSetChanged();

    }

    public static void refreshItem(int deSelect,int select){
        //over here we have pass that position on which are refreshing the
        // we have refresh two itemLayout previous one and next one so we have to call this method 2 times
        addressesAdapter.notifyItemChanged(deSelect);
        addressesAdapter.notifyItemChanged(select);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

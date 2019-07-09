package com.newproject.omega;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {


    public MyAccountFragment() {
        // Required empty public constructor
    }
    // with help of below static variable we will find out the flow of the user.
    public static final int MANAGE_ADDRESS =1;

    private Button viewAllAddressesBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_account, container, false);
        viewAllAddressesBtn = view.findViewById(R.id.my_view_all_addresses_btn);
        viewAllAddressesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myAddressesIntent =
                        new Intent(getContext(),MyAddressesActivity.class);
                myAddressesIntent.putExtra("MODE_VALUE",MANAGE_ADDRESS);
                startActivity(myAddressesIntent);
            }
        });
        return view;
    }

}

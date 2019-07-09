package com.newproject.omega;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.RecursiveAction;

import static com.newproject.omega.DeliveryActivity.select_address;
import static com.newproject.omega.MyAccountFragment.MANAGE_ADDRESS;
import static com.newproject.omega.MyAddressesActivity.refreshItem;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.ViewHolder> {

    private List<AddressesModel> addressesModelList;
    private int MODE;
    private int preSelectedPosition = -1;

    public AddressesAdapter(List<AddressesModel> addressesModelList, int MODE) {
        this.addressesModelList = addressesModelList;
        this.MODE = MODE;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addresses_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String name = addressesModelList.get(position).getFullName();
        String address = addressesModelList.get(position).getAddress();
        String pincode = addressesModelList.get(position).getPincode();
        Boolean select = addressesModelList.get(position).isSelected();

        holder.setData(name, address, pincode, select, position);

    }

    @Override
    public int getItemCount() {
        return addressesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView fullName;
        private TextView address;
        private TextView pincode;
        private ImageView icon;
        private LinearLayout optionContainer;

        public ViewHolder(View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.add_name);
            address = itemView.findViewById(R.id.add_address);
            pincode = itemView.findViewById(R.id.add_pincode);
            icon = itemView.findViewById(R.id.add_icon_view);
            optionContainer = itemView.findViewById(R.id.add_option_container);
        }

        private void setData(String username, String userAddress, String userpinCode, Boolean selected, final int position) {
            fullName.setText(username);
            address.setText(userAddress);
            pincode.setText(userpinCode);

            if (MODE == select_address) {

                icon.setImageResource(R.mipmap.round_done_black_48dp);
                if (selected) {
                    icon.setVisibility(View.VISIBLE);
                    // Over here we are going to fetch previously selected Position.
                    preSelectedPosition = position;
                } else {
                    icon.setVisibility(View.GONE);
                }
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // We have done optimization of the code over here if the user clicks
                        // on this position again
                        // and again it will check condition if user is not on the currecnt itemView layout
                        // then run the code
                        if (preSelectedPosition != position) {
                            addressesModelList.get(position).setSelected(true);
                            addressesModelList.get(preSelectedPosition).setSelected(false);
                            // after this we have to refresh the layouts over here we have just changed the values.

                            // i have called this method from MyAddresses Activity
                            refreshItem(preSelectedPosition, position);
                            preSelectedPosition = position;
                        }

                    }
                });

            } else if (MODE == MANAGE_ADDRESS) {

                optionContainer.setVisibility(View.GONE);
                icon.setImageResource(R.mipmap.sharp_more_vert_black_48dp);
                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        optionContainer.setVisibility(View.VISIBLE);
                        refreshItem(preSelectedPosition,preSelectedPosition);

                        // this position we get from onBindViewHolder...
                        preSelectedPosition = position;
                    }
                });

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refreshItem(preSelectedPosition,preSelectedPosition);

                        preSelectedPosition = -1;
                        // as all the itemView layout were closed..

                    }
                });

            }

        }
    }
}

package com.example.foodlover.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodlover.Activites.Login;
import com.example.foodlover.Activites.Profile;
import com.example.foodlover.Activites.PurchaseHistory;
import com.example.foodlover.Activites.SignUp;
import com.example.foodlover.Activites.WishList;
import com.example.foodlover.R;

public class Account extends Fragment {
    TextView profile_tv, purchase_tv, wish_list_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        profile_tv = view.findViewById(R.id.account_profile_tv);
        purchase_tv = view.findViewById(R.id.account_purchase_history);
        wish_list_tv = view.findViewById(R.id.account_wish_list);


        profile_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Profile.class);
                startActivity(intent);
            }
        });
        purchase_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PurchaseHistory.class);
                startActivity(intent);
            }
        });
        wish_list_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WishList.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
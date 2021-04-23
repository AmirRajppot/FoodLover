package com.example.foodlover.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodlover.Adapters.CartAdapter;
import com.example.foodlover.Adapters.CategoryAdapter;
import com.example.foodlover.Models.CategoryModel;
import com.example.foodlover.Models.ProductModel;
import com.example.foodlover.R;

import java.util.ArrayList;

public class Cart extends Fragment {
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    private final ArrayList<ProductModel> cart_model = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView=view.findViewById(R.id.cart_rv);
        cart_model.add(new ProductModel(1,2000,R.drawable.salad,"Salad"));
        cart_model.add(new ProductModel(1,2000,R.drawable.pizza,"Salad"));
        cart_model.add(new ProductModel(1,2000,R.drawable.lisa,"Salad"));

        cartAdapter = new CartAdapter(cart_model, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(cartAdapter);
        return view;

    }
}
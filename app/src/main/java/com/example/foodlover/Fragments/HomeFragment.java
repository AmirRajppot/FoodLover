package com.example.foodlover.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodlover.Adapters.CategoryAdapter;
import com.example.foodlover.Adapters.DealAdapter;
import com.example.foodlover.Adapters.FamousAdapter;
import com.example.foodlover.Models.CategoryModel;
import com.example.foodlover.Models.DealModel;
import com.example.foodlover.Models.ProductModel;
import com.example.foodlover.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    RecyclerView category_recyclerView, famous_recyclerView, deal_recyclerView;
    CategoryAdapter categoryAdapter;
    FamousAdapter famousAdapter;
    DealAdapter dealAdapter;
    private final ArrayList<ProductModel> famous_model = new ArrayList<>();
    private final ArrayList<DealModel> deal_model = new ArrayList<>();
    private final ArrayList<CategoryModel> categoryModels = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        category_recyclerView = view.findViewById(R.id.category_rv);
        famous_recyclerView = view.findViewById(R.id.famous_rv);
        deal_recyclerView = view.findViewById(R.id.deal_rv);
//deal
        deal_model.add(new DealModel(1,  R.drawable.lisa,1220, "Pasta", "If you also have adequate knowledge about Different Mouth watering Foods and its materials that come with a different taste and regions."));
        deal_model.add(new DealModel(1,  R.drawable.pexcels,1220, "Burger", "If you also have adequate knowledge about Different Mouth watering Foods and its materials that come with a different taste and regions."));
        deal_model.add(new DealModel(1,  R.drawable.pizza, 1220,"Pizza", "If you also have adequate knowledge about Different Mouth watering Foods and its materials that come with a different taste and regions."));
        deal_model.add(new DealModel(1,  R.drawable.lisa,1220, "Macrooni", "If you also have adequate knowledge about Different Mouth watering Foods and its materials that come with a different taste and regions."));
        dealAdapter = new DealAdapter(deal_model, getActivity());
        deal_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        deal_recyclerView.setAdapter(dealAdapter);

//famous
        famous_model.add(new ProductModel(1, 1220, R.drawable.salad, "Pasta", "If you also have adequate knowledge about Different Mouth watering Foods and its materials that come with a different taste and regions."));
        famous_model.add(new ProductModel(1, 1220, R.drawable.pizza, "Pixel", "If you also have adequate knowledge about Different Mouth watering Foods and its materials that come with a different taste and regions."));
        famous_model.add(new ProductModel(1, 1220, R.drawable.lisa, "Pasta", "If you also have adequate knowledge about Different Mouth watering Foods and its materials that come with a different taste and regions."));
        famous_model.add(new ProductModel(1, 1220, R.drawable.pexcels, "Pasta", "If you also have adequate knowledge about Different Mouth watering Foods and its materials that come with a different taste and regions."));

        famousAdapter = new FamousAdapter(famous_model, getActivity());
        famous_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        famous_recyclerView.setAdapter(famousAdapter);
//category
        categoryModels.add(new CategoryModel(1, R.drawable.salad, "Pizza"));
        categoryModels.add(new CategoryModel(2, R.drawable.lisa, "Pizza"));
        categoryModels.add(new CategoryModel(3, R.drawable.pexcels, "Pizza"));
        categoryModels.add(new CategoryModel(4, R.drawable.pizza, "Pizza"));
        categoryModels.add(new CategoryModel(5, R.drawable.pexcels, "Pizza"));

        categoryAdapter = new CategoryAdapter(categoryModels, getActivity());
        category_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        category_recyclerView.setAdapter(categoryAdapter);
        return view;
    }
}
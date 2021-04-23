package com.example.foodlover.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodlover.Adapters.FamousAdapter;
import com.example.foodlover.Adapters.PurchaseAdapter;
import com.example.foodlover.Models.ProductModel;
import com.example.foodlover.Models.PurchaseModel;
import com.example.foodlover.R;

import java.util.ArrayList;

public class PurchaseHistory extends AppCompatActivity {
    RecyclerView recyclerView;
    PurchaseAdapter purchaseAdapter;
    ArrayList<PurchaseModel> purchaseModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);
        recyclerView = findViewById(R.id.purchase_rv);

        purchaseModels.add(new PurchaseModel(1, 2000, 3, "10/20/2021"));
        purchaseModels.add(new PurchaseModel(1, 2000, 4, "10/20/2021"));
        purchaseModels.add(new PurchaseModel(1, 2000, 7, "10/20/2021"));

        purchaseAdapter = new PurchaseAdapter(purchaseModels, PurchaseHistory.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(PurchaseHistory.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(purchaseAdapter);

    }
}
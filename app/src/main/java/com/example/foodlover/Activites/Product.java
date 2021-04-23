package com.example.foodlover.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;

import com.example.foodlover.R;
import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;

import java.util.ArrayList;

public class Product extends AppCompatActivity {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        fragmentManager = getSupportFragmentManager();
        final PaperOnboardingFragment paperOnboardingFragment = new PaperOnboardingFragment().newInstance(getDataForOnBoarding());
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.product_activity, paperOnboardingFragment);
        fragmentTransaction.commit();
    }

    private ArrayList<PaperOnboardingPage> getDataForOnBoarding() {
        PaperOnboardingPage page = new PaperOnboardingPage("Burger", "Food substance consisting essentially of protein carbohydrate fat, and other nutrients used in the body of an organism to sustain growth and vital",
                Color.parseColor("#ffb174"), R.drawable.salad, R.drawable.food);
        PaperOnboardingPage page2 = new PaperOnboardingPage("Burger", "Food substance consisting essentially of protein carbohydrate fat, and other nutrients used in the body of an organism to sustain growth and vital",
                Color.parseColor("#ffb174"), R.drawable.salad, R.drawable.food);
        PaperOnboardingPage page3 = new PaperOnboardingPage("Burger", "Food substance consisting essentially of protein carbohydrate fat, and other nutrients used in the body of an organism to sustain growth and vital",
                Color.parseColor("#ffb174"), R.drawable.salad, R.drawable.food);
        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(page);
        elements.add(page2);
        elements.add(page3);
        return elements;
    }
}
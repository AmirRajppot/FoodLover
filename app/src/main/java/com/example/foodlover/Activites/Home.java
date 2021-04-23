package com.example.foodlover.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.foodlover.Fragments.Account;
import com.example.foodlover.Fragments.Cart;
import com.example.foodlover.Fragments.HomeFragment;
import com.example.foodlover.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    private Fragment homeFragment = new HomeFragment();
    //    private Fragment categoriesFragment = new CategoriesFragment();
//    private Fragment searchFragment = new SearchFragment();
    private Fragment cartFragment = new Cart();
    private Fragment accountFragment = new Account();
    private static BottomNavigationView navView;
    private Fragment active = homeFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home_nv:
//                    title.setText(R.string.app_name);
                    loadFragment(homeFragment);
                    break;
                case R.id.cart_nv:
//                    title.setText("My Cart");
                    loadFragment(cartFragment);
                    break;
                case R.id.Account_nv:

                    loadFragment(accountFragment);
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navView = findViewById(R.id.bottom_nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment, "home").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, cartFragment, "cart").hide(cartFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, accountFragment, "account").hide(accountFragment).commit();

        loadFragment(homeFragment);

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {

//            if (fragment != homeFragment) {
//                cart.setVisibility(View.GONE);
//                search.setVisibility(View.GONE);
//            } else {
//                cart.setVisibility(View.VISIBLE);
//                search.setVisibility(View.VISIBLE);
//            }

//        if (fragment == cartFragment){
//            cartFragment = new CartFragment();
//            getSupportFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
//            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,cartFragment,"cart").hide(fragment).commitAllowingStateLoss();
//            getSupportFragmentManager().beginTransaction().hide(active).show(cartFragment).commitAllowingStateLoss();
//            active=cartFragment;
//        }else
            {
                getSupportFragmentManager().beginTransaction().hide(active).show(fragment).commit();
                active = fragment;
            }
        }
        return false;
    }

}
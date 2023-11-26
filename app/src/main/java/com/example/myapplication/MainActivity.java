// MainActivity.java
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(new MyPagerAdapter(this));


        TabLayout tabLayout = findViewById(R.id.tabs);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setIcon(R.drawable.ic_home);

                    break;
                case 1:
                    tab.setIcon(R.drawable.ic_search);

                    break;
                case 2:
                    tab.setIcon(R.drawable.ic_add_new);

                    break;
                case 3:
                    tab.setIcon(R.drawable.ic_messages);

                    break;
                case 4:
                    tab.setIcon(R.drawable.ic_profile);

                    break;
            }
        }).attach();

    }



    public class MyPagerAdapter extends FragmentStateAdapter {

        public MyPagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new SearchFragment();
                case 2:
                    return new AddNewFragment();
                case 3:
                    return new MessagesFragment();
                case 4:
                    return new ProfileFragment();
                default:
                    return null;
            }
        }



        @Override
        public int getItemCount() {
            return 5; // Le nombre total d'onglets
        }
    }
}

// MainActivity.java
package com.example.myapplication;

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


    private RecyclerView.Adapter adapterArticles;
    private RecyclerView recyclerViewArticles;

    private void initRecyclerView() {
        ArrayList<ArticlesDomain> items=new ArrayList<>();
        items.add(new ArticlesDomain("VTT BTWIN 6-9ans","Bonjour, je loue ce VTT Racing Boy de BTWIN, parfait pour les enfants de 6 à 9 ans.\" +\n" +
                "                \" Robuste, avec un design inspiré des vélos de course, il offre une expérience de conduite dynamique.\" +\n" +
                "                \" Facile à manœuvrer et à entretenir, il est prêt à accompagner votre petit aventurier sur tous les terrains. \" +\n" +
                "                \"En excellent état, prêt à rouler. Ne manquez pas cette opportunité !","btwin_racing_boy_vtt_200x133.jpg", 15, 20, 69));
        items.add(new ArticlesDomain("VTT TEST2","Bonjour, je loue ce VTT Racing Boy de BTWIN, parfait pour les enfants de 6 à 9 ans.\" +\n" +
                "                \" Robuste, avec un design inspiré des vélos de course, il offre une expérience de conduite dynamique.\" +\n" +
                "                \" Facile à manœuvrer et à entretenir, il est prêt à accompagner votre petit aventurier sur tous les terrains. \" +\n" +
                "                \"En excellent état, prêt à rouler. Ne manquez pas cette opportunité !","btwin_racing_boy_vtt_200x133.jpg", 15, 20, 150));
        items.add(new ArticlesDomain("VTT TEST3","Bonjour, je loue ce VTT Racing Boy de BTWIN, parfait pour les enfants de 6 à 9 ans.\" +\n" +
                "                \" Robuste, avec un design inspiré des vélos de course, il offre une expérience de conduite dynamique.\" +\n" +
                "                \" Facile à manœuvrer et à entretenir, il est prêt à accompagner votre petit aventurier sur tous les terrains. \" +\n" +
                "                \"En excellent état, prêt à rouler. Ne manquez pas cette opportunité !","btwin_racing_boy_vtt_200x133.jpg", 37, 10, 20));

        recyclerViewArticles=findViewById(R.id.view1);
        recyclerViewArticles.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        adapterArticles=new ArticlesListAdapter(items);
        recyclerViewArticles.setAdapter(adapterArticles);

    }

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

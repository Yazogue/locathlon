// HomeFragment.java
package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView.Adapter adapterArticles;
    private RecyclerView recyclerViewArticles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);
        initRecyclerView(rootView);
        return rootView;
    }

    public void initRecyclerView(View rootView) {
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

        recyclerViewArticles=rootView.findViewById(R.id.view1);

        if (recyclerViewArticles != null) {
            recyclerViewArticles.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            adapterArticles = new ArticlesListAdapter(items);
            recyclerViewArticles.setAdapter(adapterArticles);
        }
    }
}

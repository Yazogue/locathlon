// SearchFragment.java
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import android.widget.Button;
import android.widget.SearchView;

public class SearchFragment extends Fragment {

    public SearchFragment() {
        // Constructeur par défaut requis
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Utilisez le layout de votre fragment (search_fragment.xml dans cet exemple)
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Recherchez la SearchView dans le layout du fragment
        SearchView searchView = view.findViewById(R.id.searchView);

        // Recherchez le bouton dans le layout du fragment
        Button goToAnnonceButton = view.findViewById(R.id.goToAnnonceButton);

        // Définissez un écouteur pour le bouton "Aller à AnnonceActivity"
        goToAnnonceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancez AnnonceActivity lorsque le bouton est cliqué
                Intent intent = new Intent(getActivity(), AnnonceActivity.class);
                startActivity(intent);
            }
        });

        // Définissez un écouteur pour la barre de recherche
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Appelé lorsque l'utilisateur soumet la recherche
                // Implémentez ici le traitement de la soumission de la recherche
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Appelé chaque fois que le texte de la recherche change
                // Vous pouvez mettre à jour votre liste de résultats ici
                return true;
            }
        });

        return view;
    }
}

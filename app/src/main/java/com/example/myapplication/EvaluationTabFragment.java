package com.example.myapplication;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EvaluationTabFragment extends Fragment {

    private RatingBar ratingBarGlobal;
    private RatingBar ratingBarQualiteProduit;
    private RatingBar ratingBarServiceClient;
    private RatingBar ratingBarLivraison;
    private TextView txtCommentaire;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluation, container, false);

        // Initialiser les vues
        ratingBarGlobal = view.findViewById(R.id.ratingBarGlobal);
        ratingBarQualiteProduit = view.findViewById(R.id.ratingBarQualiteProduit);
        ratingBarServiceClient = view.findViewById(R.id.ratingBarServiceClient);
        ratingBarLivraison = view.findViewById(R.id.ratingBarLivraison);
        txtCommentaire = view.findViewById(R.id.txtCommentaire);

        // Remplir les notes d'évaluation (c'est un exemple, vous devez récupérer ces données depuis votre modèle ou votre source de données)
        ratingBarGlobal.setRating(4.5f);
        ratingBarQualiteProduit.setRating(4.0f);
        ratingBarServiceClient.setRating(3.4f);
        ratingBarLivraison.setRating(4.1f);
        txtCommentaire.setText("Dernier commentaire : Excellent service!");

        return view;
    }
}


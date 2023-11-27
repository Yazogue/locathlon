package com.example.myapplication;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileDetailFragment extends Fragment {

    private TextView txtNom;
    private TextView txtPrenom;
    private TextView txtNumeroTelephone;
    private TextView txtAdresseMail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        // Initialiser les vues
        txtNom = view.findViewById(R.id.txtNom);
        txtPrenom = view.findViewById(R.id.txtPrenom);
        txtNumeroTelephone = view.findViewById(R.id.txtNumeroTelephone);
        txtAdresseMail = view.findViewById(R.id.txtAdresseMail);

        // Remplir les informations (c'est un exemple, vous devez récupérer ces données depuis votre modèle ou votre source de données)
        txtNom.setText(Html.fromHtml("<b>Nom: </b>Dupont"));
        txtPrenom.setText(Html.fromHtml("<b>Prénom : </b>Julien"));
        txtNumeroTelephone.setText(Html.fromHtml("<b>Numéro de téléphone : </b>0731567197"));
        txtAdresseMail.setText(Html.fromHtml("<b>Adresse e-mail : </b>juliendupont@orange.fr"));

        return view;
    }
}



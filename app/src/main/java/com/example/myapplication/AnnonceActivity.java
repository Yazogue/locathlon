package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class AnnonceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce);

        // Exemple de données pour l'annonce
        String nomArticle = "Vélo de montagne";
        String descriptionArticle = "Vélo de montagne en excellent état.";
        float evaluationArticle = 4.5f;
        double prixArticle = 20.0;
        MesLocasTabFragment mesLocasTabFragment = new MesLocasTabFragment();


        // Liaison des vues avec le code
        ImageView imageArticle = findViewById(R.id.imageArticle);
        TextView nomTextView = findViewById(R.id.nomArticle);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView prixTextView = findViewById(R.id.prixArticle);
        TextView descriptionTextView = findViewById(R.id.descriptionArticle);
        Button louerButton = findViewById(R.id.louerButton);
        Button contacterVendeurButton = findViewById(R.id.contacterVendeurButton);

        // Définition des données de l'annonce
        imageArticle.setImageResource(R.drawable.ic_launcher_background); // Remplacez R.drawable.velo_image par l'ID de votre image
        nomTextView.setText(nomArticle);
        ratingBar.setRating(evaluationArticle);
        prixTextView.setText(String.valueOf(prixArticle));
        descriptionTextView.setText(descriptionArticle);

        // Ajouter un bouton de retour personnalisé
        Button retourButton = findViewById(R.id.retourButton);
        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Gestion des clics sur les boutons
        louerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logique de location
                Toast.makeText(AnnonceActivity.this, "Article loué !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AnnonceActivity.this, RecapActivity.class);
                startActivity(intent);
            }
        });

        contacterVendeurButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logique pour contacter le vendeur
                Toast.makeText(AnnonceActivity.this, "Contacter le vendeur", Toast.LENGTH_SHORT).show();
            }
        });
    }
}




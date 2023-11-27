// AddNewFragment.java
package com.example.myapplication;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class AddNewFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imagePreview;
    private EditText titleEditText;
    private EditText descriptionEditText;
    private Spinner etatSpinner;
    private EditText priceEditText;
    private Button addButton;

    // Déclarez imageUri en tant que membre de classe
    private Uri imageUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_new, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialisation des vues
        imagePreview = view.findViewById(R.id.imagePreview);
        titleEditText = view.findViewById(R.id.titleEditText);
        descriptionEditText = view.findViewById(R.id.descriptionEditText);
        etatSpinner = view.findViewById(R.id.etatSpinner);
        priceEditText = view.findViewById(R.id.priceEditText);
        addButton = view.findViewById(R.id.addButton);

        // Remplacez "your_image_resource" par l'ID de votre image par défaut dans le dossier res/drawable
        imagePreview.setImageResource(R.drawable.ic_launcher_background);

        // Ajoutez un OnClickListener pour l'ImageView
        imagePreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        
        // Spinner pour l'état de l'article (neuf, acceptable, usé)
        String[] etatOptions = {"Neuf", "Acceptable", "Usé"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, etatOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etatSpinner.setAdapter(adapter);

        // Exemple de prix
        priceEditText.setText("25");

        // Bouton d'ajout
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ajoutez la logique pour enregistrer les données dans votre base de données ou effectuer d'autres actions.
                // Assurez-vous de valider les données avant de les enregistrer.
                // Par exemple, assurez-vous que le prix est un nombre valide.
            }
        });
    }
    private void openGallery() {
        // Lancer une intention pour sélectionner une image depuis la galerie
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Récupérer l'URI de l'image sélectionnée depuis la galerie
            imageUri = data.getData();

            // Afficher l'image dans l'ImageView
            imagePreview.setImageURI(imageUri);

            // Vous pouvez stocker l'URI de l'image ou effectuer d'autres opérations avec l'image ici.
        }
    }
}




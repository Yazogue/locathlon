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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddNewFragment extends Fragment {

    FirebaseFirestore db;
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

        db = FirebaseFirestore.getInstance();

        // Remplacez "your_image_resource" par l'ID de votre image par défaut dans le dossier res/drawable
        imagePreview.setImageResource(R.drawable.emptyimage);

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
        priceEditText.setText(" ");

        // Bouton d'ajout
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String TitleEditText = titleEditText.getText().toString();
            String Description = descriptionEditText.getText().toString();
            String Price = priceEditText.getText().toString();
            String ImageUri = imageUri.getPath().toString();

                Map<String,Object> data=new HashMap<>();
                data.put("title",TitleEditText);
                data.put("description",Description);
                data.put("price",Price);
                data.put("imageUri",ImageUri);

                db.collection("articles")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Map<String, Object> timestampData = new HashMap<>();
                                timestampData.put("timestamp", com.google.firebase.firestore.FieldValue.serverTimestamp());
                                // Update the document with the timestamp
                                documentReference.update(timestampData)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(requireContext(), "Ajout réussi!", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(requireContext(), "Échec de la mise à jour du timestamp. Erreur : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(requireContext(), "Échec de l'ajout. Erreur : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

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




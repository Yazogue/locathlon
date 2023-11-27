package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RecapActivity extends AppCompatActivity {

    private EditText pricePerDayEditText;
    private EditText numberOfDaysEditText;
    private EditText cardNumberEditText;
    private EditText expirationDateEditText;
    private EditText securityCodeEditText;
    private Button paymentButton;
    private ArticlesDomain object;
    private TextView totalAmountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityrecap);
        //object = new ArticlesDomain();

        pricePerDayEditText = findViewById(R.id.editTextPricePerDay);
        numberOfDaysEditText = findViewById(R.id.editTextNumberOfDays);
        cardNumberEditText = findViewById(R.id.editTextCardNumber);
        expirationDateEditText = findViewById(R.id.editTextExpirationDate);
        securityCodeEditText = findViewById(R.id.editTextSecurityCode);
        paymentButton = findViewById(R.id.buttonPayment);
        double articlePrice = object.getPrice();
        pricePerDayEditText.setText(String.valueOf(articlePrice) + "€");

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();
            }
        });
    }

    private void processPayment() {
        // Récupérer les informations saisies
        String pricePerDayStr = pricePerDayEditText.getText().toString();
        String numberOfDaysStr = numberOfDaysEditText.getText().toString();
        String cardNumber = cardNumberEditText.getText().toString();
        String expirationDate = expirationDateEditText.getText().toString();
        String securityCode = securityCodeEditText.getText().toString();

        // Vérifier si les champs de la carte sont remplis
        if (!cardNumber.isEmpty() && !expirationDate.isEmpty() && !securityCode.isEmpty()) {
            // Convertir les prix et jours en nombres
            double pricePerDay = Double.parseDouble(pricePerDayStr);
            int numberOfDays = Integer.parseInt(numberOfDaysStr);

            // Calculer le montant total
            double totalPrice = pricePerDay * numberOfDays;



            // Vous pouvez utiliser les informations de la carte ici pour le paiement sécurisé

            pricePerDayEditText.setText(object.getPrice() + "€");
            // Exemple : Afficher le montant total
            Toast.makeText(this, "Montant total: " + totalPrice, Toast.LENGTH_SHORT).show();
            totalAmountTextView.setText("Montant total: " + totalPrice);

            // Modifier l'apparence du bouton
            paymentButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
            paymentButton.setText("Validé");
        } else {
            Toast.makeText(this, "Veuillez remplir tous les champs de la carte", Toast.LENGTH_SHORT).show();
        }
    }
}



package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LoginActivity", "Clic sur le bouton de connexion");

                // Ajoutez ici la logique de vérification des identifiants et mots de passe
                if (validateCredentials(editTextUsername.getText().toString(), editTextPassword.getText().toString())) {
                    Log.d("LoginActivity", "Identifiants valides");
                    // Lancez l'activité principale avec le fragment Home
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Fermez l'activité de login
                } else {
                    Log.d("LoginActivity", "Identifiants invalides");
                    // Affichez un message d'erreur si les identifiants sont incorrects
                    // Vous pouvez utiliser Toast.makeText() ou un autre mécanisme
                }
            }
        });

    }

    // Méthode pour valider les identifiants
    private boolean validateCredentials(String username, String password) {
        boolean isValid = username.equals("user") && password.equals("password");
        Log.d("LoginActivity", "Validation des identifiants : " + isValid);
        return isValid;
    }

}

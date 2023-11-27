package com.example.myapplication;

import static android.webkit.URLUtil.isValidUrl;
import static java.util.ResourceBundle.getBundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
private Button rentBtn;
private TextView titleTxt, feeTxt, descriptionTxt, reviewTxt, scoreTxt;

private ImageView picItem;
private ArticlesDomain object;

private int numberOrder = 1;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    initView();
    getBundle();
}

    private void getBundle() {
    object = (ArticlesDomain) getIntent().getSerializableExtra("object");

        // Use Glide to load the image or set a default image if the URL is invalid
        String imageUrl = object.getPicUrl();
        if (imageUrl != null && isValidUrl(imageUrl)) {
            int drawableResourceId = this.getResources().getIdentifier(imageUrl, "drawable", this.getPackageName());
            Glide.with(this)
                    .load(imageUrl)
                    .into(picItem);
        } else {
            // Use a default image from your drawable resources
            picItem.setImageResource(R.drawable.emptyimage);
        }
        titleTxt.setText(object.getTitle());
        feeTxt.setText(object.getPrice() + "€");
        descriptionTxt.setText(object.getDescription());
        reviewTxt.setText(object.getReview()+"");
        scoreTxt.setText(object.getScore()+"");

        // Ajouter un bouton de retour personnalisé
        ImageView retourButton = findViewById(R.id.imageView7);
        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        rentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberinCart(numberOrder);
                Toast.makeText(DetailActivity.this, "Article loué !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailActivity.this, RecapActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
    rentBtn = findViewById(R.id.rentBtn);
    feeTxt = findViewById(R.id.priceTxt);
    titleTxt = findViewById(R.id.titleTxt);
    descriptionTxt = findViewById(R.id.descriptionTxt);
    picItem = findViewById(R.id.itemPic);
    reviewTxt = findViewById(R.id.reviewTxt);
    scoreTxt = findViewById(R.id.scoreTxt);

    }
}
// HomeFragment.java
package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView.Adapter adapterArticles;
    private RecyclerView recyclerViewArticles;
    private ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);
        initRecyclerView(rootView);
        scrollView = rootView.findViewById(R.id.articlesView);

        // Set up touch listener on ScrollView
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // Allow ScrollView to intercept touch events.
                view.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });

        fetchArticlesFromFirestore(); // Call the method to fetch data from Firestore
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchArticlesFromFirestore(); // Fetch data every time the fragment becomes visible or resumes
    }

    public void initRecyclerView(View rootView) {
        ArrayList<ArticlesDomain> items=new ArrayList<>();
        items.add(new ArticlesDomain("VTT BTWIN 6-9ans","Bonjour, je loue ce VTT Racing Boy de BTWIN, parfait pour les enfants de 6 à 9 ans. Robuste, avec un design inspiré des vélos de course, il offre une expérience de conduite dynamique. Facile à manœuvrer et à entretenir, il est prêt à accompagner votre petit aventurier sur tous les terrains. En excellent état, prêt à rouler. Ne manquez pas cette opportunité !","btwinmoyen", 15, 20, 69));
        items.add(new ArticlesDomain("Skis de Haute Performance"," Vous rêvez de dévaler les pentes enneigées avec des skis performants ? Ne cherchez pas plus loin ! Je propose la location de mes skis de haute qualité pour vous offrir une expérience de glisse exceptionnelle lors de votre prochaine escapade en montagne.","ski1", 12, 4, 130));
        items.add(new ArticlesDomain(" Location de Paddle !","Embarquez pour une aventure aquatique avec la location de mon paddle de haute qualité ! Que vous soyez novice ou passionné de paddle, ces planches vous offriront une expérience de glisse exceptionnelle sur l'eau.","paddle2", 37, 4, 20));
        items.add(new ArticlesDomain("Raquettes de Tennis de Qualité","Préparez-vous à dominer le court de tennis avec la location de mes raquettes de tennis haut de gamme ! Que vous soyez un joueur occasionnel ou passionné, ces raquettes vous offriront la puissance et le contrôle nécessaires pour des performances exceptionnelles sur le court.","tennis1", 15, 2, 15));
        items.add(new ArticlesDomain("Location de Clubs de Golf","Préparez-vous à améliorer votre swing avec la location de mes clubs de golf haut de gamme ! Que vous soyez un golfeur débutant ou expérimenté, ces clubs vous offriront la performance et la précision nécessaires pour des parties mémorables sur le green.","golf1", 17, 3, 200));

        recyclerViewArticles=rootView.findViewById(R.id.view1);

        if (recyclerViewArticles != null) {
            recyclerViewArticles.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            adapterArticles = new ArticlesListAdapter(items);
            recyclerViewArticles.setAdapter(adapterArticles);
            // Ajoutez un écouteur tactile à la RecyclerView
            recyclerViewArticles.setOnTouchListener((v, event) -> {
                // Demande au parent de ne pas intercepter l'événement tactile horizontal
                v.getParent().requestDisallowInterceptTouchEvent(true);

                // Appel à performClick pour traiter le clic
                v.performClick();

                return false;
            });

            // Ajoutez un écouteur de clic
            recyclerViewArticles.setOnClickListener(v -> {
                // Traitement du clic ici
            });
        }
    }

    private void fetchArticlesFromFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("articles")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<ArticlesDomain> items = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Create an ArticlesDomain object from the document data
                            ArticlesDomain article = new ArticlesDomain(
                                    document.getString("title"),
                                    document.getString("description"),
                                    document.getString("imageUri"),
                                    10, // Convert Long to int
                                    5.0,
                                    document.getString("price") != null ? Double.parseDouble(document.getString("price").trim()) : 0.0
                            );
                            items.add(article);
                        }

                        // Update the RecyclerView adapter with the fetched items
                        updateRecyclerView(items);
                    } else {
                        // Handle errors
                    }
                });
    }

    private void updateRecyclerView(ArrayList<ArticlesDomain> items) {
        if (recyclerViewArticles != null && adapterArticles != null) {
            // Update the RecyclerView adapter with the new items
            adapterArticles = new ArticlesListAdapter(items);
            recyclerViewArticles.setAdapter(adapterArticles);
        }
    }
}

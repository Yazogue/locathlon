package com.example.myapplication;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MesLocasTabFragment extends Fragment {

    private RecyclerView recyclerView;
    private MesLocasAdapter locasAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mes_locas_tab, container, false);

        // Initialiser la liste de locations (c'est un exemple, vous devrez remplacer cela par vos données réelles)
        List<LocationModel> mesLocasList = getMesLocasList();

        // Initialiser la RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewMesLocas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Adapter pour la RecyclerView
        locasAdapter = new MesLocasAdapter(mesLocasList);
        recyclerView.setAdapter(locasAdapter);

        return view;
    }


    public class LocationModel {

        private String name;
        private String date;
        private String duration;

        public LocationModel(String name, String date, String duration) {
            this.name = name;
            this.date = date;
            this.duration = duration;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public String getDuration() {
            return duration;
        }
    }


    // Méthode de test pour générer des données fictives
    private List<LocationModel> getMesLocasList() {
        List<LocationModel> locasList = new ArrayList<>();

        locasList.add(new LocationModel("Location velo", "1 janvier 2023", "5 jours"));
        locasList.add(new LocationModel("Location Paddle", "10 février 2023", "3 jours"));
        // Ajoutez d'autres locations selon vos besoins

        return locasList;
    }

    // Adaptateur pour la RecyclerView
    private class MesLocasAdapter extends RecyclerView.Adapter<MesLocasAdapter.ViewHolder> {

        private List<LocationModel> locasList;

        public MesLocasAdapter(List<LocationModel> locasList) {
            this.locasList = locasList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            LocationModel location = locasList.get(position);
            holder.txtLocationName.setText(location.getName());
            holder.txtDate.setText(location.getDate());
            holder.txtDuration.setText(location.getDuration());
        }

        @Override
        public int getItemCount() {
            return locasList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView txtLocationName;
            public TextView txtDate;
            public TextView txtDuration;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txtLocationName = itemView.findViewById(R.id.txtLocationName);
                txtDate = itemView.findViewById(R.id.txtDate);
                txtDuration = itemView.findViewById(R.id.txtDuration);
            }
        }
    }
}


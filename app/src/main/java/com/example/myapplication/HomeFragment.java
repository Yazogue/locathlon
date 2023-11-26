// HomeFragment.java
package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    // Méthode dans le fragment où vous souhaitez appeler la méthode de MainActivity
    private void appelDeMethodeDansMainActivity() {
        // Obtenir une référence à l'activité parente (MainActivity)
        MainActivity mainActivity = (MainActivity) getActivity();

        // Vérifier si l'activité est non nulle avant d'appeler la méthode
        if (mainActivity != null) {
            // Appeler la méthode de MainActivity
            mainActivity.initRecyclerView();
        }
    }

}

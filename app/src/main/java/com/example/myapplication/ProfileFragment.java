// ProfileFragment.java
package com.example.myapplication;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

public class ProfileFragment extends Fragment {

    private ViewPager viewPager3;
    private TabLayout tabLayout1;
    private ImageView profile_image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        profile_image = view.findViewById(R.id.profile_image);
        // Initialiser les vues
        viewPager3 = view.findViewById(R.id.viewPager3);
        tabLayout1 = view.findViewById(R.id.tabs2);

        // Adapter pour les onglets
        ProfilePagerAdapter pagerAdapter = new ProfilePagerAdapter(getChildFragmentManager());
        viewPager3.setAdapter(pagerAdapter);
        tabLayout1.setupWithViewPager(viewPager3);

        return view;
    }

    private static class ProfilePagerAdapter extends FragmentPagerAdapter {

        private static final int NUM_PAGES = 3;

        public ProfilePagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ProfileDetailFragment();
                case 1:
                    return new EvaluationTabFragment();
                case 2:
                    return new MesLocasTabFragment();
                default:
                    throw new IllegalArgumentException("Invalid position: " + position);
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            // Noms des onglets
            switch (position) {
                case 0:
                    return "Profile";
                case 1:
                    return "Évaluation";
                case 2:
                    return "Mes Locas";
                default:
                    return "";
            }
        }
    }
}

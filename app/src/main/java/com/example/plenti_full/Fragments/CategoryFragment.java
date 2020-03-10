package com.example.plenti_full.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plenti_full.CustomCategoryAdapter;
import com.example.plenti_full.DatabaseHandler;
import com.example.plenti_full.Javabeans.Recipe;
import com.example.plenti_full.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_category, container, false);

        DatabaseHandler db = new DatabaseHandler(getContext());
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(new Recipe("Chicken", "Chicken on Bread!", R.drawable.ic_dashboard_black_24dp));
        recipes.add(new Recipe("Chicken", "Chicken on Bread!", R.drawable.ic_dashboard_black_24dp));
        recipes.add(new Recipe("Chicken", "Chicken on Bread!", R.drawable.ic_dashboard_black_24dp));
        recipes.add(new Recipe("Chicken", "Chicken on Bread!", R.drawable.ic_dashboard_black_24dp));
        recipes.add(new Recipe("Chicken", "Chicken on Bread!", R.drawable.ic_dashboard_black_24dp));
        recipes.add(new Recipe("Chicken", "Chicken on Bread!", R.drawable.ic_dashboard_black_24dp));
        recipes.add(new Recipe("Chicken", "Chicken on Bread!", R.drawable.ic_dashboard_black_24dp));
        recipes.add(new Recipe("Chicken", "Chicken on Bread!", R.drawable.ic_dashboard_black_24dp));
        recipes.add(new Recipe("Chicken", "Chicken on Bread!", R.drawable.ic_dashboard_black_24dp));
        recipes.add(new Recipe("Chicken", "Chicken on Bread!", R.drawable.ic_dashboard_black_24dp));
        recipes.add(new Recipe("Chicken", "Chicken on Bread!", R.drawable.ic_dashboard_black_24dp));
        recipes.add(new Recipe("Chicken", "Chicken on Bread!", R.drawable.ic_dashboard_black_24dp));
        recipes.add(new Recipe("Chicken", "Chicken on Bread!", R.drawable.ic_dashboard_black_24dp));
        recipes.add(new Recipe("Chicken", "Chicken on Bread!", R.drawable.ic_dashboard_black_24dp));
        recipes.add(new Recipe("Chicken", "Chicken on Bread!", R.drawable.ic_dashboard_black_24dp));
        recipes.add(new Recipe("Chicken", "Chicken on Bread!", R.drawable.ic_dashboard_black_24dp));



        CustomCategoryAdapter adapter = new CustomCategoryAdapter(recipes, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.categoryRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return view;
    }

}

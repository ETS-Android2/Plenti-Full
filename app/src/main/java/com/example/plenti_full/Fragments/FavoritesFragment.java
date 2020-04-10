package com.example.plenti_full.Fragments;


import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.plenti_full.Adapters.CustomFavouritesAdapter;
import com.example.plenti_full.Adapters.CustomInstructionAdapter;
import com.example.plenti_full.DatabaseHandler;
import com.example.plenti_full.Javabeans.Favorite;
import com.example.plenti_full.API.RecipeSingleton;
import com.example.plenti_full.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {
    private ArrayList<Favorite> favoritesList = new ArrayList<>();

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        final DatabaseHandler db = new DatabaseHandler(getContext());

        final TextView recipeName = view.findViewById(R.id.favoriteRecipeName);



        favoritesList = db.getAllFavorites();
        if(favoritesList.isEmpty()) {
            TextView message = view.findViewById(R.id.messageTextView);
            message.setVisibility(View.VISIBLE);
        }



        CustomFavouritesAdapter adapter = new CustomFavouritesAdapter(favoritesList, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.favoritesRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

}

package com.example.plenti_full.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.plenti_full.API.RecipeSingleton;
import com.example.plenti_full.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    public static String url;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView recipeName = view.findViewById(R.id.recipeItemName);
        TextView recipeImage = view.findViewById(R.id.recipeItemImage);



        ImageView breakfastButton = view.findViewById(R.id.breakfastButton);

        breakfastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Breakfast";
                Navigation.findNavController(view).navigate(R.id.categoryFragment);
                Log.d("TEST", url + "<---- URL!");
            }
        });

        ImageView dessertButton = view.findViewById(R.id.dessertButton);

        dessertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.categoryFragment);
                url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Dessert";
                Log.d("TEST", url + "<---- URL!");
            }
        });

        ImageView appetizerButton = view.findViewById(R.id.appetizerButton);

        appetizerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.categoryFragment);
                url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Starter";
                Log.d("TEST", url + "<---- URL!");
            }
        });





        return view;
    }



}

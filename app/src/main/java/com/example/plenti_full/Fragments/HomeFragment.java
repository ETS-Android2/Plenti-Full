package com.example.plenti_full.Fragments;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.plenti_full.CustomCategoryAdapter;
import com.example.plenti_full.DatabaseHandler;
import com.example.plenti_full.Javabeans.Recipe;
import com.example.plenti_full.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    public static String url;
    String name;
    String url2;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView recipeName = view.findViewById(R.id.recipeItemName);
        TextView recipeImage = view.findViewById(R.id.recipeItemImage);



        ImageView breakfastButton = view.findViewById(R.id.breakfastButton);

        breakfastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Breakfast";
                Log.d("TEST", url + "<---- URL!");

                TestMethod(url, v);
            }
        });

        ImageView dessertButton = view.findViewById(R.id.dessertButton);

        dessertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Dessert";
                Log.d("TEST", url + "<---- URL!");

                TestMethod(url, v);
            }
        });

        ImageView appetizerButton = view.findViewById(R.id.appetizerButton);

        appetizerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Starter";
                Log.d("TEST", url + "<---- URL!");

                TestMethod(url, v);
            }
        });

        ImageView chickenButton = view.findViewById(R.id.chickenButton);

        chickenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Chicken";
                Log.d("TEST", url + "<---- URL!");

                TestMethod(url, v);
            }
        });

        ImageView beefButton = view.findViewById(R.id.beefButton);

        beefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Beef";
                Log.d("TEST", url + "<---- URL!");

                TestMethod(url, v);
            }
        });

        ImageView porkButton = view.findViewById(R.id.porkButton);

        porkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Pork";
                Log.d("TEST", url + "<---- URL!");

                TestMethod(url, v);
            }
        });

        ImageView seafoodButton = view.findViewById(R.id.seafoodButton);

        seafoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood";
                Log.d("TEST", url + "<---- URL!");

                TestMethod(url, v);
            }
        });





        return view;
    }


    public void TestMethod(String url, View view) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("meals");
                            DatabaseHandler db = new DatabaseHandler(getContext());

                            db.deleteAllRecipes();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject category = jsonArray.getJSONObject(i);


                                name = category.getString("strMeal");
                                url2 = category.getString("strMealThumb");
                                db.addRecipe(new Recipe(name, url2));
                                Log.d("TEST",  category.getString("strMeal") + "\n" + category.getString("strMealThumb") + "<<- INFO");


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getLocalizedMessage());
            }
        });

        RecipeSingleton.getInstance(getContext()).getRequestQueue().add(request);
        Navigation.findNavController(view).navigate(R.id.categoryFragment);
    }



}

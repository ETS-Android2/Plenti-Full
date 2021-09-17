package com.example.plenti_full.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.plenti_full.API.RecipeSingleton;
import com.example.plenti_full.Adapters.CustomCategoryAdapter;
import com.example.plenti_full.DatabaseHandler;
import com.example.plenti_full.Javabeans.Recipe;
import com.example.plenti_full.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.plenti_full.Fragments.HomeFragment.url;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    public static int spanCount = 2;
    ArrayList<Recipe> recipes = new ArrayList<>();
    CustomCategoryAdapter adapter;
    RecyclerView recyclerView;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        final DatabaseHandler db = new DatabaseHandler(getContext());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("meals");

                            db.deleteAllRecipes();
                            recipes.clear();
                            ArrayList<Recipe> newRecipes = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject category = jsonArray.getJSONObject(i);


                                Recipe recipe = new Recipe(category.getString("idMeal"),
                                                           category.getString("strMeal"),
                                                           category.getString("strMealThumb"));

                                newRecipes.add(recipe);
                                db.addRecipe(recipe);
                                Log.d("Recipe Info",  "ID: " + category.getString("idMeal") + ", " + "NAME: " + category.getString("strMeal") + ", " + "IMAGE: " + category.getString("strMealThumb"));



                            }
                            recipes.addAll(newRecipes);

                            adapter.notifyDataSetChanged();

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

        recyclerView = view.findViewById(R.id.categoryRecyclerView);

        adapter = new CustomCategoryAdapter(recipes, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.categoryRecyclerView);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        return view;
    }


}

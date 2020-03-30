package com.example.plenti_full.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.plenti_full.API.RecipeSingleton;
import com.example.plenti_full.CustomCategoryAdapter;
import com.example.plenti_full.DatabaseHandler;
import com.example.plenti_full.Javabeans.Recipe;
import com.example.plenti_full.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.plenti_full.Fragments.HomeFragment.url;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    String name;
    String url2;
    int spanCount = 2;
    ArrayList<Recipe> recipes;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_category, container, false);
        DatabaseHandler db = new DatabaseHandler(getContext());
        recipes = db.getAllRecipes();
        final ImageView imageView = view.findViewById(R.id.recipeItemImage);



        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("meals");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject category = jsonArray.getJSONObject(i);


                                name = category.getString("strMeal");
                                url2 = category.getString("strMealThumb");
                                //Picasso.get().load(url2).into(imageView);
                                Recipe recipe = new Recipe(name, url2);
                                DatabaseHandler db = new DatabaseHandler(getContext());
                                if(db.getAllRecipes().size() > jsonArray.length() - 1) {


                                } else {

                                    db.addRecipe(recipe);
                                }

                                Log.d("TEST",  category.getString("strMeal") + "\n" + category.getString("strMeal") + "<<- INFO");

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
        CustomCategoryAdapter adapter = new CustomCategoryAdapter(recipes, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.categoryRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        return view;
    }

}

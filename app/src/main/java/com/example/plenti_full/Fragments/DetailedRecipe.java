package com.example.plenti_full.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.plenti_full.CustomInstructionAdapter;
import com.example.plenti_full.DatabaseHandler;

import com.example.plenti_full.Javabeans.Instruction;
import com.example.plenti_full.Javabeans.Recipe;
import com.example.plenti_full.R;
import com.example.plenti_full.API.RecipeSingleton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.plenti_full.CustomCategoryAdapter.mealName;
import static java.security.AccessController.getContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailedRecipe extends Fragment {
    private String url;
    private String ingredients;
    private String measurements;
    private String image;
    private String title;
    private String categoryString;
    private String areaString;
    private String tagsString;
    private String instructionsString;
    private LinearLayout ingredientsLayout;
    private LinearLayout measurementsLayout;
    private ImageView recipeImage;
    private TextView recipeTitle;
    private TextView categoryLabel;
    private TextView areaLabel;
    private TextView tagsLabel;

    public DetailedRecipe() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_detailed_recipe, container, false);

        ingredientsLayout = view.findViewById(R.id.ingredientsList);
        measurementsLayout = view.findViewById(R.id.measurementsList);

        final DatabaseHandler db = new DatabaseHandler(getContext());
        final ArrayList<Recipe> recipes = db.getAllRecipes();
        final ArrayList<Instruction> instructionsList = new ArrayList<>();





        url = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + mealName;

        Log.d("TEST", url);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("meals");
                            int stepper = 1;


                            JSONObject category = jsonArray.getJSONObject(0);
                            image = category.getString("strMealThumb");
                            recipeImage = view.findViewById(R.id.recipeImage);
                            Picasso.get().load(image)
                                    .resize(280, 280).centerCrop().into(recipeImage);

                            title = category.getString("strMeal");
                            recipeTitle = view.findViewById(R.id.recipeTitle);
                            recipeTitle.setText(title);

                            categoryString = category.getString("strCategory");
                            categoryLabel = view.findViewById(R.id.recipeCategory);
                            categoryLabel.setText(categoryString);

                            areaString = category.getString("strArea");
                            areaLabel = view.findViewById(R.id.recipeArea);
                            areaLabel.setText(areaString);

                            tagsString = category.getString("strArea");
                            tagsLabel = view.findViewById(R.id.recipeTags);
                            tagsLabel.setText(tagsString);

                            instructionsString = category.getString("strInstructions");
                            String[] instructionArray = instructionsString.split("\n");
                            for(int i = 0; i < instructionArray.length; i ++) {

                                instructionsList.add(new Instruction(i, instructionArray[i]));
                            }




                            for (int i = 0; i < stepper; i++) {

                                ingredients = category.getString("strIngredient" + stepper);
                                measurements = category.getString("strMeasure" + stepper);
                                if(ingredients.isEmpty()) {
                                    Log.d("TEST", "Empty string!");

                                } else {
                                    TextView ingredientItem = new TextView(getContext());
                                    ingredientItem.setText(ingredients);
                                    TextView measurementItem = new TextView(getContext());
                                    measurementItem.setText(measurements);

                                    ingredientsLayout.addView(ingredientItem);
                                    measurementsLayout.addView(measurementItem);
                                    stepper++;
                                    Log.d("TEST", "not empty String - " + ingredients + "\n" + measurements);
                                }



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




        CustomInstructionAdapter adapter = new CustomInstructionAdapter(instructionsList, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.instuctionsRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }




}

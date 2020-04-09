package com.example.plenti_full.Fragments;


import android.content.Intent;
import android.net.Uri;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.plenti_full.API.RecipeSingleton;
import com.example.plenti_full.Adapters.CustomInstructionAdapter;
import com.example.plenti_full.DatabaseHandler;
import com.example.plenti_full.Javabeans.Favorite;
import com.example.plenti_full.Javabeans.Instruction;
import com.example.plenti_full.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RandomRecipe extends Fragment {
    private int counter = 0;
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
    private TextView instructions;
    private String youtubeUrl;
    private ImageView youtubeButton;
    private ImageView favoriteButton;

    public RandomRecipe() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_random_recipe, container, false);

        final ArrayList<Instruction> instructionsList = new ArrayList<>();
        favoriteButton = view.findViewById(R.id.randomFavouriteRecipe);
        favoriteButton.setEnabled(true);
        ingredientsLayout = view.findViewById(R.id.randomIngredientsList);
        measurementsLayout = view.findViewById(R.id.randomMeasurementsList);
        url = "https://www.themealdb.com/api/json/v1/1/random.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("meals");
                            int stepper = 1;


                            JSONObject recipe = jsonArray.getJSONObject(0);
                            image = recipe.getString("strMealThumb");
                            recipeImage = view.findViewById(R.id.randomRecipeImage);
                            Picasso.get().load(image)
                                    .resize(280, 280).centerCrop().into(recipeImage);

                            title = recipe.getString("strMeal");
                            recipeTitle = view.findViewById(R.id.randomRecipeTitle);
                            recipeTitle.setText(title);

                            categoryString = recipe.getString("strCategory");
                            categoryLabel = view.findViewById(R.id.randomRecipeCategory);
                            categoryLabel.setText(categoryString);

                            areaString = recipe.getString("strArea");
                            areaLabel = view.findViewById(R.id.randomRecipeArea);
                            areaLabel.setText(areaString);


                            instructionsString = recipe.getString("strInstructions");
                            instructionsString = instructionsString.replaceAll("(\\r)", "");
                            String[] instructionArray = instructionsString.split("\n");
                            for(int i = 0; i < instructionArray.length; i++) {
                                if(instructionArray[i].length() > 5) {
                                    counter++;
                                    instructionsList.add(new Instruction(counter, instructionArray[i]));
                                    Log.d("TEST", instructionArray[i]);
                                } else {

                                }

                            }

                            tagsString = recipe.getString("strTags");
                            tagsLabel = view.findViewById(R.id.randomRecipeTags);
                            ImageView tagsImage = view.findViewById(R.id.randomTagImage);
                            String[] tags = tagsString.split(",");
                            if(tags[0] == "null") {
                                tagsLabel.setText("");

                                tagsImage.setVisibility(View.INVISIBLE);
                            }else {
                                tagsLabel.setText(tags[0]);
                            }


                            youtubeButton = view.findViewById(R.id.videoButton);
                            youtubeUrl = recipe.getString("strYoutube");
                            youtubeButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
                                    startActivity(intent);
                                }
                            });

                            favoriteButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    DatabaseHandler db = new DatabaseHandler(getContext());
                                    favoriteButton.setEnabled(false);
                                    Favorite favorite = new Favorite(title, image);
                                    ArrayList<Favorite> favorites = db.getAllFavorites();
                                    boolean foundMatch = false;
                                    for(int i = 0; i < favorites.size(); i++) {
                                        if(title.equals(favorites.get(i).getName())) {
                                            foundMatch = true;
                                        }

                                    }
                                    if(foundMatch) {
                                        Toast toast = Toast.makeText(getContext(), "Recipe Already Favorited!", Toast.LENGTH_SHORT);
                                        toast.show();
                                    } else {
                                        db.addFavorite(favorite);
                                        Toast toast = Toast.makeText(getContext(), "Favorite added!", Toast.LENGTH_SHORT);
                                        toast.show();
                                        Navigation.findNavController(v).navigate(R.id.favoritesFragment);
                                    }
                                }
                            });


                            for (int i = 0; i < stepper; i++) {

                                ingredients = recipe.getString("strIngredient" + stepper);
                                measurements = recipe.getString("strMeasure" + stepper);
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
        RecyclerView recyclerView = view.findViewById(R.id.randomInstuctionsRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

}

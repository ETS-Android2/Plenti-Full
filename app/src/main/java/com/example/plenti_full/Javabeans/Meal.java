package com.example.plenti_full.Javabeans;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;

import androidx.navigation.Navigation;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.plenti_full.DatabaseHandler;
import com.example.plenti_full.API.RecipeSingleton;
import com.example.plenti_full.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class Meal {
    private String mealId;
    private String mealName;
    private String mealCategory;
    private String mealArea;
    private String mealInstructions;
    private String mealThumbnail;
    private String mealTags;
    private String mealVideoURL;


    private ArrayList<String> ingredients;
    private ArrayList<String> measurements;

    public Meal(String mealId, String mealName, String mealCategory,
                String mealArea, String mealInstructions, String mealThumbnail,
                String mealTags, String mealVideoURL, ArrayList<String> ingredients, ArrayList<String> measurements) {

        this.mealId = mealId;
        this.mealName = mealName;
        this.mealCategory = mealCategory;
        this.mealArea = mealArea;
        this.mealInstructions = mealInstructions;
        this.mealThumbnail = mealThumbnail;
        this.mealTags = mealTags;
        this.mealVideoURL = mealVideoURL;
        this.ingredients = ingredients;
        this.measurements = measurements;

    }




}

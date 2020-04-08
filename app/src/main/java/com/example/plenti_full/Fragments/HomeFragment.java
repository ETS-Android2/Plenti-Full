package com.example.plenti_full.Fragments;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plenti_full.DatabaseHandler;
import com.example.plenti_full.Javabeans.Recipe;
import com.example.plenti_full.R;

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

        final TextView recipeName = view.findViewById(R.id.instructionStep);
        TextView recipeImage = view.findViewById(R.id.recipeItemImage);



        ImageView breakfastButton = view.findViewById(R.id.breakfastButton);

        breakfastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Breakfast";
                Log.d("TEST", url + "<---- URL!");

                navigate(v);
            }
        });

        ImageView dessertButton = view.findViewById(R.id.dessertButton);

        dessertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Dessert";
                Log.d("TEST", url + "<---- URL!");

                navigate(v);
            }
        });

        ImageView appetizerButton = view.findViewById(R.id.appetizerButton);

        appetizerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Starter";
                Log.d("TEST", url + "<---- URL!");

                navigate(v);
            }
        });

        ImageView chickenButton = view.findViewById(R.id.chickenButton);

        chickenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Chicken";
                Log.d("TEST", url + "<---- URL!");

                navigate(v);
            }
        });

        ImageView beefButton = view.findViewById(R.id.beefButton);

        beefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Beef";
                Log.d("TEST", url + "<---- URL!");

                navigate(v);
            }
        });

        ImageView porkButton = view.findViewById(R.id.porkButton);

        porkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Pork";
                Log.d("TEST", url + "<---- URL!");

                navigate(v);
            }
        });

        ImageView seafoodButton = view.findViewById(R.id.seafoodButton);

        seafoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood";
                Log.d("TEST", url );

                navigate(v);
                //TestMethod(url, v);
            }
        });





        return view;
    }
    public void navigate(View view) {
        Navigation.findNavController(view).navigate(R.id.categoryFragment);
    }

//    public void TestMethod(String url, View view) {
//
//        Navigation.findNavController(view).navigate(R.id.categoryFragment);
//    }



}

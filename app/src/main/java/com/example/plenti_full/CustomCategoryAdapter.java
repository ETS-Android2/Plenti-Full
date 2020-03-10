package com.example.plenti_full;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plenti_full.Javabeans.Recipe;

import java.util.ArrayList;


/**
 * @author Owen Bick

 */
public class CustomCategoryAdapter extends RecyclerView.Adapter<CustomCategoryAdapter.CustomViewHolder> {
    private ArrayList<Recipe> recipes;
    private Context context;


    public CustomCategoryAdapter(ArrayList<Recipe> recipes, Context context){
            this.recipes = recipes;
            this.context = context;
    }

    @NonNull
    @Override
    /**
     * Create view and return CustomViewHolder
     */
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new CustomViewHolder(view);
    }

    /**
     * Bind values to the POJO getters
     */
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

            Recipe recipeItem = recipes.get(position);
            holder.name.setText(recipeItem.getName());
            holder.description.setText(recipeItem.getDescription());

            }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected TextView name;
        protected TextView description;
        protected ImageView image;


        /**
         * Set values to textViews and imageView
         */
        public CustomViewHolder(View view){
            super(view);
            this.name = view.findViewById(R.id.recipeItemName);
            this.description = view.findViewById(R.id.recipeItemDescription);
            this.image = view.findViewById(R.id.recipeItemImage);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            description.setOnClickListener(this);
            name.setOnClickListener(this);
        }

        //When item is clicked
        public void onClick(View v) {

        }
    }
}

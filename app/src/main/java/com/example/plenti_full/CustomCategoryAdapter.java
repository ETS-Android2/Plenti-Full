package com.example.plenti_full;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plenti_full.Javabeans.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * @author Owen Bick

 */
public class CustomCategoryAdapter extends RecyclerView.Adapter<CustomCategoryAdapter.CustomViewHolder> {
    public static String mealName;
    private ArrayList<Recipe> recipes;
    private Context context;
    ImageView imageView;


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
        imageView = view.findViewById(R.id.recipeItemImage);

        return new CustomViewHolder(view);
    }

    /**
     * Bind values to the POJO getters
     */
    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {

        final Recipe recipeItem = recipes.get(position);
        Picasso.get().load(recipeItem.getImage())
                .resize(280, 280).centerCrop().into(imageView);

        holder.name.setText(recipeItem.getName());

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected TextView name;
        protected ImageView image;


        /**
         * Set values to textViews and imageView
         */
        public CustomViewHolder(View view){
            super(view);
            this.name = view.findViewById(R.id.recipeItemName);
            this.image = view.findViewById(R.id.recipeItemImage);
            image.setOnClickListener(this);
            name.setOnClickListener(this);
        }

        //When item is clicked
        public void onClick(View v) {
            Navigation.findNavController(v).navigate(R.id.detailedRecipe);
            DatabaseHandler db = new DatabaseHandler(context);
            mealName = name.getText().toString();
            Log.d("TEST", "Recipe Clicked!");
        }
    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    public int getItemViewType(int position) {
        return position;
    }
}

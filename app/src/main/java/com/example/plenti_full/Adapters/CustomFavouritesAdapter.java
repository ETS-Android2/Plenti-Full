package com.example.plenti_full.Adapters;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plenti_full.DatabaseHandler;
import com.example.plenti_full.Javabeans.Favorite;
import com.example.plenti_full.Javabeans.Recipe;
import static com.example.plenti_full.Adapters.CustomCategoryAdapter.mealName;
import com.example.plenti_full.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomFavouritesAdapter extends RecyclerView.Adapter<CustomFavouritesAdapter.CustomViewHolder> {

    private ArrayList<Favorite> favorites;
    private Context context;
    ImageView recipeImage;




    public CustomFavouritesAdapter(ArrayList<Favorite> favorites, Context context){
        this.favorites = favorites;
        this.context = context;
    }

    @NonNull
    @Override
    /**
     * Create view and return CustomViewHolder
     */
    public CustomFavouritesAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_item, parent, false);

        final DatabaseHandler db = new DatabaseHandler(context);

        recipeImage = view.findViewById(R.id.favoriteRecipeImage);



        return new CustomFavouritesAdapter.CustomViewHolder(view);
    }

    /**
     * Bind values to the POJO getters
     */
    @Override
    public void onBindViewHolder(@NonNull final CustomFavouritesAdapter.CustomViewHolder holder, int position) {

        final Favorite favoriteItem = favorites.get(position);
        Picasso.get().load(favoriteItem.getImage())
                .resize(280, 280).centerCrop().into(recipeImage);

        holder.name.setText(favoriteItem.getName());


    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected TextView name;
        protected ImageView image;
        protected ImageView unfavoriteImage;


        /**
         * Set values to textViews and imageView
         */
        public CustomViewHolder(View view){
            super(view);
            this.name = view.findViewById(R.id.favoriteRecipeName);
            this.image = view.findViewById(R.id.favoriteRecipeImage);
            this.unfavoriteImage = view.findViewById(R.id.unfavouriteButton);
            image.setOnClickListener(this);
            name.setOnClickListener(this);
            unfavoriteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHandler db = new DatabaseHandler(context);
                    ArrayList<Favorite> favoritesList = db.getAllFavorites();
                    boolean foundMatch;
                    for(int i = 0; i < favoritesList.size(); i++) {
                        if(favoritesList.get(i).getName().equals(name.getText().toString())) {
                            Log.d("TEST", "Recipe Removed! - " + favoritesList.get(i).getName());
                            db.deleteFavorite(favoritesList.get(i).getName());
                            Toast toast = Toast.makeText(context, "Recipe Removed!", Toast.LENGTH_SHORT);
                            toast.show();
                            Navigation.findNavController(v).navigate(R.id.favoritesFragment);
                        }

                    }
                }
            });
        }

        //When item is clicked
        public void onClick(View v) {
            mealName = name.getText().toString();
            Navigation.findNavController(v).navigate(R.id.detailedRecipe);
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

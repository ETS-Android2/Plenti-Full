package com.example.plenti_full;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.plenti_full.Javabeans.Favorite;
import com.example.plenti_full.Javabeans.Recipe;

import java.util.ArrayList;

/**
 @author Ocbick
  *
  * CRUD OPERATIONS
  * */
public class DatabaseHandler extends SQLiteOpenHelper {


    /*
     Database Version
     */
    public static final int DATABASE_VERSION = 1;

    /*
    Database Name
     */
    public static final String DATABASE_NAME = "recipedb";

    /*
    Table Names
     */
    public static final String TABLE_RECIPES = "recipes";
    public static final String TABLE_FAVORITES = "favorites";



    /*
    Column Names
     */
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";


    public static final String COLUMN_IMAGE = "image";




    /*
    CREATE TABLE STATEMENT
     */


    public static final String CREATE_RECIPE_TABLE = "CREATE TABLE " +
            TABLE_RECIPES + " (" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME + " TEXT," + COLUMN_IMAGE + " TEXT)";

    public static final String CREATE_FAVORITES_TABLE = "CREATE TABLE " +
            TABLE_FAVORITES + " (" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME + " TEXT," + COLUMN_IMAGE + " TEXT)";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RECIPE_TABLE);
        db.execSQL(CREATE_FAVORITES_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*
    INSERT RECORDS
     */
    public void addRecipe(Recipe recipe){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, recipe.getName());
        values.put(COLUMN_IMAGE, recipe.getImage());
        db.insert(TABLE_RECIPES, null, values);
        db.close();
    }

    public void addFavorite(Favorite favorite){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, favorite.getName());
        values.put(COLUMN_IMAGE, favorite.getImage());
        db.insert(TABLE_FAVORITES, null, values);
        db.close();
    }




    /*
    RETRIEVE RECORDS
     */


    public ArrayList<Recipe> getAllRecipes(){
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RECIPES ,
                null);
        ArrayList<Recipe> recipes = new ArrayList<>();
        while(cursor.moveToNext()) {
            recipes.add(new Recipe(
                    cursor.getString(1),
                    cursor.getString(2)));
        }
        db.close();
        return recipes;
    }

    public ArrayList<Favorite> getAllFavorites(){
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FAVORITES ,
                null);
        ArrayList<Favorite> favorites = new ArrayList<>();
        while(cursor.moveToNext()) {
            favorites.add(new Favorite(
                    cursor.getString(1),
                    cursor.getString(2)));
        }
        db.close();
        return favorites;
    }






    /*
        UPDATE RECORDS
    */



    /*
        DELETE RECORDS
     */
    public void deleteFavorite(String favorite){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVORITES, COLUMN_NAME + " = ?",
                new String[]{favorite});
        db.close();
    }

    public void deleteAllRecipes() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_RECIPES);
    }

    public void deleteAllFavorites() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_FAVORITES);
    }



}


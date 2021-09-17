package com.example.plenti_full.API;


import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RecipeSingleton {
    private static RecipeSingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    private RecipeSingleton(Context context) {
        this.context = context;
    }

    public static RecipeSingleton getInstance(Context context) {
        if(instance == null){
            instance = new RecipeSingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
}

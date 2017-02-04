package com.example.vibhor.swwaadelajawwab;

import android.app.Application;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by vibhor on 2/4/2017.
 */
public class Swwaadelajawwab extends Application {

    RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this);
    }
}

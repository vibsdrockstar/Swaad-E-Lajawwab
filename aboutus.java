package com.example.vibhor.swwaadelajawwab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
    }

    public void openSpinnerActivity(View view){
        startActivity(new Intent(this, SpinnerActivity.class));
    }
}

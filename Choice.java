package com.example.vibhor.swwaadelajawwab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class SpinnerActivity extends AppCompatActivity {

    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        spinner = (Spinner) findViewById(R.id.dishes);

    }

    public void getRecepie(View view){
        Intent intent = new Intent(this, Dish.class);
        intent.putExtra("recepie", spinner.getSelectedItem().toString());
        startActivity(intent);
    }
}

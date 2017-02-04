package com.example.vibhor.swwaadelajawwab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Dish extends AppCompatActivity {

    String recepie,dish_id=null,url,code,image_url=null;
    String key = "WW9D0XwfATmsh7V3AdWTFfMqo24op18aoUujsnQSTAMXk4rjXT";
    final HashMap<String, String> headers = new HashMap<>();
    TextView title,recipie_text;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);
        title = (TextView)findViewById(R.id.dish_title);
        recipie_text = (TextView)findViewById(R.id.recipie_text);
        imageView = (ImageView)findViewById(R.id.dish_image);
        recepie = getIntent().getStringExtra("recepie");
        headers.put("X-Mashape-Key", key);
        headers.put("Accept", "application/json");
        Log.d("here", recepie);
        String dish_name = null;
        try {
            dish_name = URLEncoder.encode(recepie, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        url = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/search?query=" + dish_name;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //Toast.makeText(Dish.this,"Response is: "+ response, Toast.LENGTH_LONG).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            JSONObject object = jsonArray.getJSONObject(0);
                            dish_id = Integer.toString(object.getInt("id"));
                            image_url ="https://spoonacular.com/recipeImages/"+ object.getString("image").toString();
                            Picasso.with(Dish.this).load(image_url).into(imageView);

                            getRecipie();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
                Toast.makeText(Dish.this, "Some error occured : " + error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            public Map<String, String> getHeaders() {
                return headers;
            }
        };
// Add the request to the RequestQueue.
        ((Swwaadelajawwab) getApplicationContext()).requestQueue.add(stringRequest);

    }
    public void getRecipie(){
        try {
            code = URLEncoder.encode(dish_id, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        url = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/"+code+"/summary";


        StringRequest stringRequest_recipie = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //Toast.makeText(Dish.this,"Response is: "+ response, Toast.LENGTH_LONG).show();
                        Log.d("here",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //JSONArray jsonArray = jsonObject.getJSONArray("results");
                            //JSONObject object = jsonArray.getJSONObject(0);

                            title.setText(jsonObject.getString("title").toString());
                            recipie_text.setText(Html.fromHtml(jsonObject.getString("summary").toString()));
                            Log.d("imurl",image_url);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
                Toast.makeText(Dish.this, "Some error occured : " + error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            public Map<String, String> getHeaders() {
                return headers;
            }
        };
// Add the request to the RequestQueue.
        ((Swwaadelajawwab) getApplicationContext()).requestQueue.add(stringRequest_recipie);
    }
}




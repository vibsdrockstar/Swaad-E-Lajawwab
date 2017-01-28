import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getResponse(View view){
        RequestQueue queue = Volley.newRequestQueue(this);
        String apiKey = "1866da4d8b030532a1a6e1418453008d";
        String url ="https://developers.zomato.com/api/v2.1/search?q=pizza";

        final HashMap<String, String> headers = new HashMap<>();
        headers.put("user-key",apiKey);
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                       Toast.makeText(MainActivity.this,"Response is: "+ response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
                Toast.makeText(MainActivity.this,"Some error occured : " + error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            public Map<String, String> getHeaders(){
                return headers;
            }
        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}

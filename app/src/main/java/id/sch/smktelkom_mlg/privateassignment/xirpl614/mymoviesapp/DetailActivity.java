package id.sch.smktelkom_mlg.privateassignment.xirpl614.mymoviesapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {
    private static final String URL_DATA = "https://api.nytimes.com/svc/movies/v2/reviews/search.json?api-key=35ffe1ef119e4fcd9655c229d74e6327";
    public TextView textViewHeaddt;
    public TextView textViewDescdt;
    public ImageView imageViewdt;
    public String url;
    public String urlGambar;
    boolean ipress;
    private Integer postkey = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        postkey = getIntent().getExtras().getInt("Kolv");
        loadReyclerViewData();

        textViewHeaddt = (TextView) findViewById(R.id.textViewHeaddt);
        textViewDescdt = (TextView) findViewById(R.id.textViewDescdt);
        imageViewdt = (ImageView) findViewById(R.id.imageViewdt);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void loadReyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("results");
                            JSONObject o = array.getJSONObject(postkey);

                            setTitle(o.getString("display_title"));
                            textViewHeaddt.setText(o.getString("display_title"));
                            textViewDescdt.setText(o.getString("summary_short"));
                            url = o.getJSONObject("link").getString("url");
                            urlGambar = o.getJSONObject("multimedia").getString("src");

                            Glide
                                    .with(DetailActivity.this)
                                    .load(o.getJSONObject("multimedia").getString("src"))
                                    .into(imageViewdt);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}

package id.web.christiawan.seastaff;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import id.web.christiawan.seastaff.adapter.MainAdapter;
import id.web.christiawan.seastaff.config.ConfigUmum;
import id.web.christiawan.seastaff.holder.ItemObject;

public class ListTugas extends AppCompatActivity {
    private RecyclerView rv_item;
    private LinearLayoutManager layoutManager;
    private ProgressDialog progressDialog;
    private ItemObject.ObjectBelajar objectBelajar;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tugas);

        rv_item = (RecyclerView) findViewById(R.id.rv_item);
//        tanggal = (TextView) findViewById(R.id.txtTanggal);

        layoutManager = new LinearLayoutManager(getApplication());
        rv_item.setHasFixedSize(true);
        rv_item.setLayoutManager(layoutManager);
        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Silahkan Tunggu...");


        GetData(ConfigUmum.URL_GET_LIST_TUGAS);
    }

    public void GetData(String URL) {

        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            ;

            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                objectBelajar = mGson.fromJson(response, ItemObject.ObjectBelajar.class);
                adapter = new MainAdapter(getApplication(), objectBelajar.result);
                rv_item.setAdapter(adapter);

                progressDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke server, periksa jaringan anda :(", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        });
        int socketTimeout = 5000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        queue.add(stringRequest);
    }
}

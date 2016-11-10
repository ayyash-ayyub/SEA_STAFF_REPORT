package id.web.christiawan.seastaff;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import id.web.christiawan.seastaff.config.ConfigUmum;

/**
 * Created by FactorChrist on 09/11/2016.
 */

    public class UpdatePassword extends Activity  {


    public static final String KEY_EMAIL= "aa";
    public static final String KEY_passwordbaru1 = "bb";
    String email;

    Button simpan;
    private EditText txtpass1;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubahpassword);

        findViewsById();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Silahkan Tunggu...");


        SharedPreferences sharedPreferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(ConfigUmum.NIS_SHARED_PREF, "tidak tersedia");

        simpan.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  simpan();
              }
          }
        );
    }

    private void findViewsById() {
        simpan = (Button) findViewById(R.id.buttonUpdate);
        txtpass1 = (EditText) findViewById(R.id.editText3);


    }

    private void  simpan() {
        progressDialog.show();

        final String aa = email.trim();

        final String bb = txtpass1.getText().toString().trim();



        StringRequest sR = new StringRequest( Request.Method.POST, ConfigUmum.UPDATE_PASSWORD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                     //   System.out.println("ast"+response);

                        if(response.equals("Sukses")){
                            Toast.makeText(getApplicationContext(), "Password changed", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(UpdatePassword.this, Login.class);
                            startActivity(i);
                            finish();
                        }

                       // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
//
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_EMAIL, aa);
                params.put(KEY_passwordbaru1, bb);
                return params;
            }
        };
        int socketTimeout = 10000;//10 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        sR.setRetryPolicy(policy);
        requestQueue.add(sR);
    }
}

package id.web.christiawan.seastaff;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import id.web.christiawan.seastaff.config.ConfigUmum;


/**
 * Created by chris on 30/10/2016.
 */

public class InsertTugas extends AppCompatActivity  {

    public static final String KEY_EMAIL= "aa";
    public static final String KEY_TUGAS = "a";
    public static final String KEY_KETERANGAN = "b";
    public static final String KEY_TGL_AWAL= "c";
    public static final String KEY_JAM_AWAL= "cc";

    public static final String KEY_TGL_AKHIR= "d";
    public static final String KEY_JAM_AKHIR= "dd";


    Button btnDatePicker, btnTimePicker, btnDatePickerB, btnTimePickerB;
    EditText txtDate, txtTime, txtDateB, txtTimeB;
    private int mYear, mMonth, mDay, mHour, mMinute;


    Button simpan;
    String email;





    private EditText txttugas,txtdeskripsi;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas_insert);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Silahkan Tunggu...");


        simpan = (Button) findViewById(R.id.btnUbahTampan);
        txttugas = (EditText) findViewById(R.id.tugas);
        txtdeskripsi = (EditText) findViewById(R.id.deskripsi);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        // kembaran
        btnDatePickerB=(Button)findViewById(R.id.btn_dateDua);
        btnTimePickerB=(Button)findViewById(R.id.btn_timeDua);
        txtDateB=(EditText)findViewById(R.id.in_dateDua);
        txtTimeB=(EditText)findViewById(R.id.in_timeDua);



        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date


                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(InsertTugas.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();




            }
        });

        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(InsertTugas.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });


        // kembarannya

        btnDatePickerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date


                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(InsertTugas.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDateB.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();




            }
        });

        btnTimePickerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(InsertTugas.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtTimeB.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        getActionBar().hide();
        toolbar.setNavigationIcon(R.drawable.logo_atas);
        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);


        SharedPreferences sharedPreferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(ConfigUmum.NIS_SHARED_PREF, "tidak tersedia");

        Toast.makeText(InsertTugas.this, "Email : "+email, Toast.LENGTH_SHORT).show();


        simpan.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                 if(txttugas.getText().toString().isEmpty() ||
                         txtdeskripsi.getText().toString().isEmpty() ||
                         txtDate.getText().toString().isEmpty() ||
                         txtTime.getText().toString().isEmpty() ||
                         txtDateB.getText().toString().isEmpty() ||
                         txtTimeB.getText().toString().isEmpty()){
                     Toast.makeText(InsertTugas.this, "please fulfill the form", Toast.LENGTH_SHORT).show();

                 }else{
                     simpan();
                 }
              }
          }
        );
    }







    private void logout() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are You Sure?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {


                        SharedPreferences preferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();

                        SharedPreferences sp = getSharedPreferences("myloginapp", MODE_WORLD_READABLE);
                        SharedPreferences.Editor edd = sp.edit();

                        edd.clear();
                        edd.commit();


                        editor.putBoolean(ConfigUmum.LOGGEDIN_SHARED_PREF, false);
                        editor.putString(ConfigUmum.NIS_SHARED_PREF, "");

                        editor.commit();
                        //clear sp IP


                        Intent intent = new Intent(InsertTugas.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void resetField(){

        txttugas.setText(null);
        txtdeskripsi.setText(null);
        txtDate.setText(null);
        txtTime.setText(null);
        txtDateB.setText(null);
        txtTimeB.setText(null);
    }

    private void  simpan(){
        progressDialog.show();
        final String aa = email.trim();
        final String a = txttugas.getText().toString().trim();
        final String b = txtdeskripsi.getText().toString().trim();

        final String c = txtDate.getText().toString().trim();
        final String cc = txtTime.getText().toString().trim();


        final String d = txtDateB.getText().toString().trim();
        final String dd = txtTimeB.getText().toString().trim();








        StringRequest sR = new StringRequest(Request.Method.POST, ConfigUmum.INSERT_TUGAS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("ast"+response);

                        if(response.equals("Sukses")){
                            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                            resetField();
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
                        resetField();
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_EMAIL, aa);
                params.put(KEY_TUGAS, a);
                params.put(KEY_KETERANGAN, b);

                params.put(KEY_TGL_AWAL, c);
                params.put(KEY_JAM_AWAL, cc);

                params.put(KEY_TGL_AKHIR, d);
                params.put(KEY_JAM_AKHIR, dd);



                return params;
        }
        };
        int socketTimeout = 3000;//10 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        sR.setRetryPolicy(policy);
        requestQueue.add(sR);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {

           logout();
           // Toast.makeText(InsertTugas.this, "Logout", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.menuHelp) {
            Intent i = new Intent(InsertTugas.this, UpdatePassword.class);
            startActivity(i);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }






}
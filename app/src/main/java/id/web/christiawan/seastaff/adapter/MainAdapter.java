package id.web.christiawan.seastaff.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import id.web.christiawan.seastaff.holder.ItemObject;
import id.web.christiawan.seastaff.R;
import id.web.christiawan.seastaff.holder.MainHolder;

/**
 * Created by Isfahani on 30-Jul-16.
 */
public class MainAdapter extends RecyclerView.Adapter<MainHolder> {

    ProgressDialog progressDialog;



    public List<ItemObject.ObjectBelajar.Results> resultsList;
    public Context context;

    public MainAdapter(Context context, List<ItemObject.ObjectBelajar.Results> resultsList) {
        this.context = context;
        this.resultsList = resultsList;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, null);
        MainHolder mainHolder = new MainHolder(view);
        return mainHolder;
    }




    public void DeleteData(String Url) {

        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url,
                new Response.Listener<String>() {;
                    @Override
                    public void onResponse(String response) {
                        Log.d("uye", response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("uye", error.toString());

            }
        });
        queue.add(stringRequest);
    }


    @Override
    public void onBindViewHolder(MainHolder holder, final int position) {
        holder.txtTugas.setText(resultsList.get(position).nama_tugas);
        holder.txtMulai.setText("Tgl Mulai : "+resultsList.get(position).mulai);
        holder.txtSelesai.setText("Tgl Selesai : "+resultsList.get(position).selesai);
        holder.txtDeskripsi.setText(resultsList.get(position).deskripsi);

        holder.cardview_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
//                progressDialog = new ProgressDialog(context);
//                progressDialog.setCancelable(false);
//                progressDialog.setMessage("Silahkan Tunggu...");


              //  DeleteData(ConfigUmum.URL_DELETE_PAGI+idd);
                // Intent i = new Intent(context, Pengalih.class);
                //  view.getContext().startActivity(i);


//                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                builder.setTitle("Konfirmasi");
//                builder.setMessage("Apakah anda yakin ingin menghapus\n" +
//                        nama_makanan + " ?");
//                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        DeleteData(ConfigUmum.URL_DELETE_PAGI+idd);
//                        dialog.dismiss();

                        //   Intent i = new Intent(context, SarapanActivity.class);

                        //
                        //  i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                        //view.getContext().startActivity(i);


//                        Activity activity = (Activity)view.getContext();
//                        activity.finish();
//                        view.getContext().startActivity(activity.getIntent());
                      //  Toast.makeText(context, "Temuan si okta",Toast.LENGTH_SHORT).show();

//                    }
//                });
//                builder.setNegativeButton("Batalkan", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
////                        dialog.dismiss();
//                    }
//                });
//                AlertDialog alert = builder.create();
//                alert.show();


                //   Toast.makeText(context,"ID nya: "+resultsList.get(position).nama_makanan, Toast.LENGTH_LONG).show();


            }
        });
    }



    @Override
    public int getItemCount() {
        return this.resultsList.size();
    }
}

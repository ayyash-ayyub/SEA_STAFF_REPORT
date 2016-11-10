package id.web.christiawan.seastaff.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import id.web.christiawan.seastaff.R;

/**
 * Created by Ayyash on 30-Jul-16.
 */

public class MainHolder extends RecyclerView.ViewHolder {

    public ImageView img_avatar;
    public TextView txtTugas, txtMulai, txtSelesai,txtDeskripsi;
    public CardView cardview_item;

    public MainHolder(View itemView) {
        super(itemView);
        txtTugas = (TextView) itemView.findViewById(R.id.txtTugas);
        txtMulai = (TextView) itemView.findViewById(R.id.txtMulai);
        txtSelesai = (TextView)itemView.findViewById(R.id.txtSelesai);
        txtDeskripsi = (TextView)itemView.findViewById(R.id.txtDeskripsi);
        cardview_item = (CardView) itemView.findViewById(R.id.cardview_item);
    }
}

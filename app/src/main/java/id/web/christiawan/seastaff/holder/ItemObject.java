package id.web.christiawan.seastaff.holder;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ItemObject {
    public class ObjectBelajar {
        @SerializedName("result")
        public List<Results> result;

        public class Results {
            @SerializedName("id")
            public String id;

            @SerializedName("nama_tugas")
            public String nama_tugas;

            @SerializedName("mulai")
            public String mulai;

            @SerializedName("selesai")
            public String selesai;

            @SerializedName("deskripsi")
            public String deskripsi;
        }
    }
}

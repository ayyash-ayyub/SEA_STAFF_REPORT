package id.web.christiawan.seastaff.config;

/**
 * Created by Abdul Rizal Adompo on 9/18/2016.
 */
public class ConfigUmum {

    public static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";

    public static final String IP="eblu.projectaplikasi.web.id";

    public static final String LOGIN_URL = "http://"+IP+"/Api/login.php";

    public static String URL_GET_LIST_TUGAS = "http://"+IP+"/Api/get_data_tugas.php";
    public static String UPDATE_PASSWORD = "http://"+IP+"/Api/ubah_password.php";
    public static String INSERT_TUGAS = "http://"+IP+"/Api/insert_data_tugas.php";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String LOGIN_SUCCESS = "success";
    public static final String SHARED_PREF_NAME = "myloginapp";
    public static final String AMBIL_NAMA = "nama";
    public static final String NIS_SHARED_PREF = "email";
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    public static final String ID_KELAS = "id_kelas";
}

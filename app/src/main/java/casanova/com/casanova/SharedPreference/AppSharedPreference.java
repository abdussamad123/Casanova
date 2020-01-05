package casanova.com.casanova.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Nitesh on 30/5/17.
 */
public class AppSharedPreference {
    private static AppSharedPreference appSharedPrefrence;
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;
    private Context context;
    private static final String IS_LOGIN = "";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
  //  public static final String KEY_AGENCY = "agencuName";


    public AppSharedPreference(Context context) {
        this.appSharedPrefs = context.getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    public static AppSharedPreference getsharedprefInstance(Context con) {
        if (appSharedPrefrence == null)
            appSharedPrefrence = new AppSharedPreference(con);
        return appSharedPrefrence;
    }

    public SharedPreferences getAppSharedPrefs() {
        return appSharedPrefs;
    }

    public void setAppSharedPrefs(SharedPreferences appSharedPrefs) {
        this.appSharedPrefs = appSharedPrefs;
    }


    public SharedPreferences.Editor getPrefsEditor() {

        return prefsEditor;
    }

    public void Commit() {
        prefsEditor.commit();
    }

    public void clearallSharedPrefernce() {
        prefsEditor.clear();
        prefsEditor.commit();
    }

    public String getDate() {
        return appSharedPrefs.getString("Date", "");
    }

    public void setDate(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("Date", path);
        prefsEditor.commit();
    }

    public String getAll() {
        return appSharedPrefs.getString("All", "");
    }

    public void setAll(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("All", path);
        prefsEditor.commit();
    }
/*
    public Boolean getLanguage() {
        return appSharedPrefs.getBoolean("language", false);
    }
*/

/*
    public void setLanguag(Boolean flag) {
        prefsEditor.putBoolean("language", flag).commit();
    }
*/




    public String getEmailId() {
        return appSharedPrefs.getString("EmailId", "");
    }

    public void setEmailId(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("EmailId", path);
        prefsEditor.commit();
    }

    public String getGallery() {
        return appSharedPrefs.getString("Gallery", "");
    }

    public void setGallery(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("Gallery", path);
        prefsEditor.commit();
    }

    public String getParentid() {
        return appSharedPrefs.getString("Parentid", "");
    }

    public void setParentid(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("Parentid", path);
        prefsEditor.commit();
    }

    public String getmobile_no() {
        return appSharedPrefs.getString("mobile_no", "");
    }

    public void setmobile_no(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("mobile_no", path);
        prefsEditor.commit();
    }

    public String getuser_id() {
        return appSharedPrefs.getString("user_id", "");
    }

    public void setuser_id(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("user_id", path);
        prefsEditor.commit();
    }

    public String getuser_name() {
        return appSharedPrefs.getString("getuser_name", "");
    }

    public void setuser_name(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("getuser_name", path);
        prefsEditor.commit();
    }

    public String getsection_id() {
        return appSharedPrefs.getString("section_id", "");
    }



    public void setsection_id(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("section_id", path);
        prefsEditor.commit();
    }

    public String getclass_id() {
        return appSharedPrefs.getString("class_id", "");
    }



    public void setclass_id(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("class_id", path);
        prefsEditor.commit();
    }

    public String getemp_id() {
        return appSharedPrefs.getString("emp_id", "");
    }



    public void setemp_id(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("emp_id", path);
        prefsEditor.commit();
    }

    public String getfirebase_id() {
        return appSharedPrefs.getString("firebase_id", "");
    }

    public void setfirebase_id(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("firebase_id", path);
        prefsEditor.commit();
    }

    public String getAcedminc_year() {
        return appSharedPrefs.getString("Acedminc_year", "");
    }



    public void setAcedminc_year(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("Acedminc_year", path);
        prefsEditor.commit();
    }

    public String getcustomer_id() {
        return appSharedPrefs.getString("customer_id", "");
    }



    public void setcustomer_id(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("customer_id", path);
        prefsEditor.commit();
    }

    public String getlang() {
        return appSharedPrefs.getString("lang", "");
    }



    public void setlang(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("lang", path);
        prefsEditor.commit();
    }




    public Boolean getlanguage() {
        return appSharedPrefs.getBoolean("language", false);
    }



    public void setlanguage(Boolean flag) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putBoolean("language", flag);
        prefsEditor.commit();
    }

    public String getImage() {
        return appSharedPrefs.getString("language", "");
    }



    public void setImage(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("image", path);
        prefsEditor.commit();
    }

    public String getposition0() {
        return appSharedPrefs.getString("position0", "");
    }



    public void setposition0(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("position0", path);
        prefsEditor.commit();
    }

    public String getposition1() {
        return appSharedPrefs.getString("position1", "");
    }

    public void setposition1(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("position1", path);
        prefsEditor.commit();
    }

    public String getname() {
        return appSharedPrefs.getString("name", "");
    }

    public void setname(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("name", path);
        prefsEditor.commit();
    }
    public String getuser_type() {
        return appSharedPrefs.getString("user_type", "");
    }

    public void setuser_type(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("user_type", path);
        prefsEditor.commit();
    }

    public String getStatus() {
        return appSharedPrefs.getString("Status", "");
    }

    public void setStatus(String path) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("Status", path);
        prefsEditor.commit();
    }





    public void createLoginSession(String email, String password){
        prefsEditor.putBoolean(IS_LOGIN, true);
        prefsEditor.putString(KEY_EMAIL, email);
        prefsEditor.putString(KEY_PASSWORD, password);
      //  prefsEditor.putString(KEY_AGENCY, agencuName);
        prefsEditor.commit();
    }


}

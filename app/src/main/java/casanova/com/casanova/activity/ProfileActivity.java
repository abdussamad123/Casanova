package casanova.com.casanova.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import casanova.com.casanova.Font;
import casanova.com.casanova.R;
import casanova.com.casanova.SharedPreference.AppSharedPreference;

public class ProfileActivity extends AppCompatActivity {

    AppSharedPreference appSharedPreference;
    EditText name,surname,address,pincode,town,country,password,confirm_password,email;
    LinearLayout date_linear;
    Integer mYear,mMonth,mDay;
    TextView dates,months,years;
    Button save,logout;
    String nam,surna,add,pin,tow,cont,pass,conf_pass,dob,eml;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        appSharedPreference = AppSharedPreference.getsharedprefInstance(getApplicationContext());
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        surname=findViewById(R.id.surname);
        name=findViewById(R.id.name);
        date_linear=findViewById(R.id.date_linear);

        years=findViewById(R.id.year);
        months=findViewById(R.id.month);
        dates=findViewById(R.id.date);

        password=findViewById(R.id.password);
        country=findViewById(R.id.country);
        town=findViewById(R.id.town);
        pincode=findViewById(R.id.pincode);
        address=findViewById(R.id.address);
        image=findViewById(R.id.image);
        email=findViewById(R.id.email);
        confirm_password=findViewById(R.id.confirm_password);

        logout=findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ProfileActivity.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                        .setMessage("Are you sure you want to Logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences settings = ProfileActivity.this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                settings.edit().clear().commit();
                                Intent intent=new Intent(ProfileActivity.this,LoginActivity.class);
                                intent.putExtra("logout","logout");
                                startActivity(intent);
                            }
                        }).setNegativeButton("No", null).show();
            }
        });

        save=findViewById(R.id.save);
        date_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date c1 = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c1);

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ProfileActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
                                SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MMM-yyyy");
                                Date date;
                                try {
                                    date = originalFormat.parse(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                    dates.setText(String.valueOf(dayOfMonth));
                                    months.setText(String.valueOf(monthOfYear + 1));
                                    years.setText(String.valueOf(year));
                                    //dob=date.toString();
                                    // endDateEditText.setText(targetFormat.format(date));
                                    //endDate = date.getTime();
                                    //endDateEditText.setTextColor(Color.parseColor("#212121"));
                                } catch (ParseException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.show();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nam=name.getText().toString();
                surna=surname.getText().toString();
                add=address.getText().toString();
                pin=pincode.getText().toString();
                tow=town.getText().toString();
                cont=country.getText().toString();
                pass=password.getText().toString();
                conf_pass=confirm_password.getText().toString();
                dob=dates.getText().toString()+"-"+months.getText().toString()+"-"+years.getText().toString();
                eml=email.getText().toString();

                if (pass.equals(conf_pass)){
                    Update();
                }else {
                    Toast.makeText(ProfileActivity.this,"Password not match",Toast.LENGTH_SHORT).show();
                }
               // Update();
            }
        });

        Profile();
    }

    public void Update() {

        ProgressDialog pDialog = new ProgressDialog(ProfileActivity.this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        String url = URL_SUPPORT.Baseurl+"update";

        final JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.accumulate("user_id",appSharedPreference.getcustomer_id());

            jsonObject.accumulate("firstname",nam);
            jsonObject.accumulate("lastname",surna);
            jsonObject.accumulate("email",eml);
            jsonObject.accumulate("dob",dob);
            //jsonObject.accumulate("phonenumber","");
            jsonObject.accumulate("address",add);
            jsonObject.accumulate("country",cont);
            jsonObject.accumulate("town",tow);
            jsonObject.accumulate("postal_code",pin);
            //jsonObject.accumulate("privacy_policies","");
            //jsonObject.accumulate("term_conditions","");
            jsonObject.accumulate("password",pass);

        } catch (JSONException e) {
            e.printStackTrace();
        }



        Log.e("jsonpostdata",""+jsonObject);
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, response -> {
/*
            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString("name", "demo");
            editor.commit();*/
            try {
                if (response.getString("success").equals("true")){

                   // JSONObject contact = response.getJSONObject("data");
                    //String userid=contact.getString("userid");
                    Toast.makeText(getApplicationContext(),
                            "Update Successfully", Toast.LENGTH_LONG).show();
                    Profile();
                    pDialog.dismiss();
                }else {
                    Toast.makeText(getApplicationContext(),
                            "Not Updated", Toast.LENGTH_LONG).show();
                    pDialog.dismiss();
                }
            } catch (JSONException e) {
                pDialog.dismiss();
                e.printStackTrace();
            }

            // response

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Profile();
               /* Toast.makeText(getApplicationContext(),
                        "Invalid !", Toast.LENGTH_LONG).show();*/
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-type", "application/json");
                return headers;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                1000*5,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsonObjectRequest);


    }


    public void Profile() {

        ProgressDialog pDialog = new ProgressDialog(ProfileActivity.this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();


        String url = URL_SUPPORT.Baseurl+"profile";


        final JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.accumulate("user_id",appSharedPreference.getcustomer_id());

        } catch (JSONException e) {
            e.printStackTrace();
        }



        Log.e("jsonpostdata",""+jsonObject);
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, response -> {
/*
            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString("name", "demo");
            editor.commit();*/
            try {
                if (response.getString("success").equals("true")){

                    JSONObject contact = response.getJSONObject("data");
                    //String userid=contact.getString("userid");
                    name.setText(contact.getString("firstname").substring(0,1).toUpperCase() + contact.getString("firstname").substring(1));
                    name.setTypeface(Font.getSansHeading(ProfileActivity.this));
                    surname.setText(contact.getString("lastname").substring(0,1).toUpperCase() + contact.getString("lastname").substring(1));
                    surname.setTypeface(Font.getSansHeading(ProfileActivity.this));
                    String test = contact.getString("dateofbirth");


                    if (test.equals("")){

                    }else {
                        try {
                            String [] dateParts = test.split("-");
                            String year = dateParts[0];
                            String month = dateParts[1];
                            String day = dateParts[2];

                            dates.setText(day);
                            months.setText(month);
                            years.setText(year);

                            dates.setTypeface(Font.getSansHeading(ProfileActivity.this));
                            months.setTypeface(Font.getSansHeading(ProfileActivity.this));
                            years.setTypeface(Font.getSansHeading(ProfileActivity.this));

                        }catch (Exception e){

                        }

                    }

                    if (contact.getString("address").equals("null")){
                        address.setText("");
                    }else {
                        address.setText(contact.getString("address"));
                        address.setTypeface(Font.getSansHeading(ProfileActivity.this));
                    }
                    if (contact.getString("town").equals("null")){
                        town.setText("");
                    }else {
                        town.setText(contact.getString("town"));
                        town.setTypeface(Font.getSansHeading(ProfileActivity.this));
                    }
                     if (contact.getString("postal_code").equals("null")){
                         pincode.setText("");
                    }else {
                         pincode.setText(contact.getString("postal_code"));
                         pincode.setTypeface(Font.getSansHeading(ProfileActivity.this));
                     }
                    if (contact.getString("country").equals("null")){
                        country.setText("");
                    }else {
                        country.setText(contact.getString("country"));
                        country.setTypeface(Font.getSansHeading(ProfileActivity.this));
                    }
                    if (contact.getString("email").equals("null")){
                        email.setText("");
                    }else {

                        email.setText(contact.getString("email"));
                        email.setTypeface(Font.getSansHeading(ProfileActivity.this));



                    }


                    Picasso.with(ProfileActivity.this).load(contact.getString("image")).memoryPolicy(MemoryPolicy.NO_CACHE).into(image);
                    pDialog.dismiss();
                }else {
                    Toast.makeText(getApplicationContext(),
                            "Wrong username and password", Toast.LENGTH_LONG).show();
                    pDialog.dismiss();
                }
            } catch (JSONException e) {
                pDialog.dismiss();
                e.printStackTrace();
            }

            // response

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Toast.makeText(getApplicationContext(),
                        "Invalid !", Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-type", "application/json");
                return headers;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                1000*5,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsonObjectRequest);


    }

}

package casanova.com.casanova.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import casanova.com.casanova.R;
import casanova.com.casanova.SharedPreference.AppSharedPreference;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    Button login;
    JSONObject responseobj;
    EditText password,username;
    SharedPreferences sharedpreferences;
    private ProgressDialog mProgressDialog;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 007;
    ImageView google_sign;
    String firstWords ;
    String lastWord ;
    String personPhotoUrl ;
    String email ;
    AppSharedPreference appSharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        appSharedPreference = AppSharedPreference.getsharedprefInstance(getApplicationContext());
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        login=findViewById(R.id.login);
        google_sign=findViewById(R.id.google_sign);
        password=findViewById(R.id.password);
        username=findViewById(R.id.username);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();

               // new Listyourproperty().execute( user,pass);
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                //SelectScrtion(user,pass);

            }
        });

/*
        try {

            Bundle bundle = getIntent().getExtras();
            String logout = bundle.getString("logout");
            if (logout.equals("logout")){
                signOut();
            }else {

                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();

                mGoogleApiClient = new GoogleApiClient.Builder(this)
                        .enableAutoManage(this, this)
                        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                        .build();

                username.setText("");
                password.setText("");

            }

        }catch (Exception e){
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();

            username.setText("");
            password.setText("");
        }
*/



/*
        google_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
*/
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();


            String test = acct.getDisplayName();
            firstWords = test.substring(0, test.lastIndexOf(" "));
            lastWord = test.substring(test.lastIndexOf(" ") + 1);
            personPhotoUrl = acct.getPhotoUrl().toString();
            email = acct.getEmail();



            //txtName.setText(personName);
            //txtEmail.setText(email);
            /*Glide.with(getApplicationContext()).load(personPhotoUrl)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgProfilePic);*/


            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onStart() {
        super.onStart();



       /* OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }*/
    }


    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
           // mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            try {
                mProgressDialog.dismiss();
                Signup(firstWords,lastWord,email);
            }catch (Exception e){

            }

            /*btnSignIn.setVisibility(View.GONE);
            btnSignOut.setVisibility(View.VISIBLE);
            btnRevokeAccess.setVisibility(View.VISIBLE);
            llProfileLayout.setVisibility(View.VISIBLE);*/
        } else {
            signOut();

           /* btnSignIn.setVisibility(View.VISIBLE);
            btnSignOut.setVisibility(View.GONE);
            btnRevokeAccess.setVisibility(View.GONE);
            llProfileLayout.setVisibility(View.GONE);*/
        }
    }

    public void Signup(String first_name,String last_name,String email) {

        ProgressDialog pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();


        String url = URL_SUPPORT.Baseurl+"signup";


        final JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.accumulate("firstname",first_name);
            jsonObject.accumulate("lastname",last_name);
            jsonObject.accumulate("email",email);
            jsonObject.accumulate("dob","");
            jsonObject.accumulate("phonenumber","");
            jsonObject.accumulate("g_id","");
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
                    String userid=contact.getString("userid");
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("name", "demo");
                    editor.commit();
                    appSharedPreference.setcustomer_id(userid);
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
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


    public void SelectScrtion(String username,String password) {

        ProgressDialog pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();


        String url = URL_SUPPORT.Baseurl+"login";


        final JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.accumulate("email",username);
            jsonObject.accumulate("password",password);
            jsonObject.accumulate("login_type","1");
            jsonObject.accumulate("g_id","");
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
                    String userid=contact.getString("userid");
                    appSharedPreference.setcustomer_id(userid);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("name", "demo");
                    editor.commit();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
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


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

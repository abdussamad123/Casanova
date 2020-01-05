package casanova.com.casanova.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import casanova.com.casanova.R;
import casanova.com.casanova.activity.URL_SUPPORT;


public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    RecyclerView mRecyclerView1,mRecyclerView2;
    RecyclerView.Adapter  mAdapter1;
    ArrayList<String> alName1;
    ImageView image1,image2;
    //ArrayList<LocationBean> arrayList=new ArrayList<>();
    Spinner select_location;
    LinearLayout spinner,linear_layout;
    RecyclerView.LayoutManager mLayoutManager, mLayoutManager1, mLayoutManager2;

    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);


        image1=view.findViewById(R.id.image1);
        image2=view.findViewById(R.id.image2);
        select_location=view.findViewById(R.id.select_location);
        spinner=view.findViewById(R.id.spinner);
        linear_layout=view.findViewById(R.id.linear_layout);

        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(getActivity(), PickupLocationDetailsActivity.class);
                //intent.putExtra("location","location_spinner");
                //startActivity(intent);
            }
        });
        Headers();


        return view;
    }

/*
    public void Location() {


        ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        String url = URL_SUPPORT.Baseurl+"offices";


        final JSONObject jsonObject = new JSONObject();




        Log.e("jsonpostdata",""+jsonObject);
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, response -> {


            // response
            try {
                JSONArray jsonArray = response.getJSONArray("data") ;

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    LocationBean locationBean = new LocationBean();
                    if (i==0){
                        locationBean.setOffice_name("Choose Pickup Location");
                        arrayList.add(locationBean);
                    }else {

                        locationBean.setOffice_name(jsonObject1.getString("office_name"));

                        arrayList.add(locationBean);
                    }


                }
                select_location.setAdapter(new LocationAdapter(getActivity(), arrayList));
                Headers();
                pDialog.dismiss();

            } catch (JSONException e) {
                pDialog.dismiss();
                e.printStackTrace();
            }

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Toast.makeText(getActivity(),
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
*/



    public void Headers() {


        ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        String url = URL_SUPPORT.Baseurl+"sliders";


        final JSONObject jsonObject = new JSONObject();




        Log.e("jsonpostdata",""+jsonObject);
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, response -> {


            // response
            try {
                JSONArray jsonArray = response.getJSONArray("data") ;

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    if (i==0){
                        Picasso.with(getContext()).load(jsonObject1.getString("img_url")).memoryPolicy(MemoryPolicy.NO_CACHE).into(image1);

                    }else if (i==1){
                        Picasso.with(getContext()).load(jsonObject1.getString("img_url")).memoryPolicy(MemoryPolicy.NO_CACHE).into(image2);
                    }


                }
                pDialog.dismiss();
                linear_layout.setVisibility(View.VISIBLE);

            } catch (JSONException e) {
                pDialog.dismiss();
                e.printStackTrace();
            }

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Toast.makeText(getActivity(),
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

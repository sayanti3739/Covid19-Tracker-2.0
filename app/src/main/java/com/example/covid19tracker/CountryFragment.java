package com.example.covid19tracker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CountryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ListView lvtext;
    private Adapter aa = null;
    String url = "https://corona.lmao.ninja/countries";
    //String url = "https://corona.lmao.ninja/all";
    RequestQueue queue = null;
    ArrayList<ListItem> list = new ArrayList<>();


    public CountryFragment() {
        // Required empty public constructor
    }

    class MyResponseHandler implements Response.Listener<JSONArray>{

        @Override
        public void onResponse(JSONArray response) {
            Log.i("FUCK2", "Atleast 2 here");
            try{
                //Log.i("FUCK2", "response : "+response);
                for(int i = 0; i<response.length(); i++)
                {
                    Log.i("FUCK2", "i : "+i);
                    JSONObject jsonObject = response.getJSONObject(i);
                    String cname = jsonObject.getString("country");
                    String total = jsonObject.getString("cases");
                    String discharged = jsonObject.getString("recovered");
                    String deaths = jsonObject.getString("deaths");

                    Log.i("FUCK2", "Test : "+jsonObject.getString("active"));

                    ListItem lo = new ListItem();

                    lo.setSname(cname);
                    lo.setCnf(total);
                    lo.setDis(discharged);
                    lo.setDeat(deaths);

                    list.add(lo);
                }
            }
            catch (Exception e){
                Log.i("TRUTH", "Error : "+e.getMessage());
            }
            aa.notifyDataSetChanged();
        }
    }

    class MyErrorHandler implements Response.ErrorListener{

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.i("FACT", "onErrorResponse : "+error.getMessage());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        lvtext = getView().findViewById(R.id.listview);

        aa = new Adapter(getContext(), list);
        //Log.i("ERR", "onErrorResponse : "+aa);
        lvtext.setAdapter(aa);

        queue = Volley.newRequestQueue(getContext());

        Log.i("FUCK2", "Atleast here");

        MyResponseHandler response = new MyResponseHandler();
        MyErrorHandler error = new MyErrorHandler();

        Log.i("FUCK2", "Atleast here2 : "+response);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, response, error);

        //JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response, error);

        Log.i("FUCK2", "Atleast here3");

        queue.add(request);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false);
    }
}

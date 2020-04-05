package com.example.covid19tracker;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class StateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ListView lvtext;
    private Adapter aa = null;
    String url = "https://api.rootnet.in/covid19-in/stats/latest";
    //String url = "https://free.currconv.com/api/v7/currencies?apiKey=c2715b3881e484081a68";
    RequestQueue queue = null;
    ArrayList<ListItem> list = new ArrayList<>();

    public StateFragment() {
        // Required empty public constructor
    }

    class MyResponseHandler implements Response.Listener<JSONObject>{
        @Override
        public void onResponse(JSONObject response) {
            try{
                if(response.getString("success").equals("true")){
                    JSONObject obj = new JSONObject(response.getString("data"));
                    JSONArray jarr = obj.getJSONArray("regional");

                    for(int i = 0; i<jarr.length(); i++){
                        Log.i("TRUTH", "i : "+i);

                        JSONObject jobj = jarr.getJSONObject(i);
                        String sname = jobj.getString("loc");
                        String total = String.valueOf(Integer.parseInt(jobj.getString("confirmedCasesIndian"))+Integer.parseInt(jobj.getString("confirmedCasesForeign")));
                        String discharge = jobj.getString("discharged");
                        String death = jobj.getString("deaths");

                        //Log.i("TRUTH", "States : "+sname);

                        ListItem lo = new ListItem();
                        lo.setSname(sname);
                        lo.setCnf(total);
                        lo.setDis(discharge);
                        lo.setDeat(death);

                        list.add(lo);
                    }
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
        super.onViewCreated(view, savedInstanceState);

        lvtext = getView().findViewById(R.id.listview);

        aa = new Adapter(getContext(), list);
        Log.i("ERR", "onErrorResponse : "+aa);
        lvtext.setAdapter(aa);

        queue = Volley.newRequestQueue(getContext());

        MyResponseHandler response = new MyResponseHandler();
        MyErrorHandler error = new MyErrorHandler();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response, error);

        queue.add(request);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_state, container, false);
    }
}

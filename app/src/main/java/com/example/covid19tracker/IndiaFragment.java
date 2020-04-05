package com.example.covid19tracker;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

public class IndiaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    OkHttpClient client = new OkHttpClient();

    TextView confirmed, discharged, deaths;

    TextView cong, disg, deatg;

    public IndiaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        confirmed = getView().findViewById(R.id.confirmed);
        discharged = getView().findViewById(R.id.discharged);
        deaths = getView().findViewById(R.id.deaths);

        cong = getView().findViewById(R.id.totalg);
        disg = getView().findViewById(R.id.dischargedg);
        deatg = getView().findViewById(R.id.deathsg);

        OkHttpHandler okHttpHandler= new OkHttpHandler();
        okHttpHandler.execute();

        okhttphandlerg okHttpHandlerj = new okhttphandlerg();
        okHttpHandlerj.execute();

    }

    public class OkHttpHandler extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            Log.i("TRUTH", "Response : ");

            Request request = new Request.Builder()
                    .url("https://api.rootnet.in/covid19-in/stats/latest")
                    .method("GET", null)
                    .build();


            try{
                Response response = client.newCall(request).execute();
                try{
                    JSONObject Jobject = new JSONObject(response.body().string());
                    //
                    if(Jobject.getString("success").equals("true")) {
                        //Log.i("TRUTH", "Jobject : "+Jobject.getString("data"));
                        JSONObject summ1 = new JSONObject(Jobject.getString("data"));
                        JSONObject summ = new JSONObject(summ1.getString("summary"));
                        return summ;

                    }

                }
                catch (Exception e){
                    Log.i("TRUTH", "Error2 : "+e.getMessage());
                }
            }
            catch (IOException e){
                Log.i("TRUTH", "Error : "+e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            //Log.i("TRUTH", "Object : "+o.toString());
            try {
                JSONObject summ = new JSONObject(o.toString());
                /*tot = ();
                con = ;
                dis = ;
                deat = ;*/
                Log.i("TRUTH", "TEST : " + summ.getString("confirmedButLocationUnidentified"));
                confirmed.setText(summ.getString("total"));
                discharged.setText(summ.getString("discharged"));
                deaths.setText(summ.getString("deaths"));
            }
            catch (Exception e){
                Log.i("TRUTH", "Error3 : "+e.getMessage());
            }
        }
    }

    public class okhttphandlerg extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            Log.i("TRUTH", "Response : ");

            Request request = new Request.Builder()
                    .url("https://corona.lmao.ninja/all")
                    .method("GET", null)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                try {
                    JSONObject Jobject = new JSONObject(response.body().string());
                    return Jobject;
                } catch (Exception e) {
                    Log.i("TRUTH", "Error2 : " + e.getMessage());
                }
            }
            catch (Exception e){
                Log.i("TRUTH", "Error2 : " + e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            try{
                JSONObject jobj = new JSONObject(o.toString());
                Log.i("TRUTH", "TEST : " + jobj.getString("active"));
                cong.setText(jobj.getString("cases"));
                disg.setText(jobj.getString("recovered"));
                deatg.setText(jobj.getString("deaths"));
            }
            catch (Exception e){
                Log.i("TRUTH", "Error2 : " + e.getMessage());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_india, container, false);
    }
}

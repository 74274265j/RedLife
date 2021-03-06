package com.newlogin.mainpage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.newlogin.Config;
import com.newlogin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NeedBlood extends Activity {
    private SimpleAdapter adpt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.needblood);

        adpt  = new SimpleAdapter(new ArrayList<Contact>(), this);
        ListView lView = (ListView) findViewById(R.id.listview);

        lView.setAdapter(adpt);

        // Exec async load task
        (new AsyncListViewLoader()).execute(Config.HISTORY_URL);
    }


    private class AsyncListViewLoader extends AsyncTask<String, Void, List<Contact>> {
        private final ProgressDialog dialog = new ProgressDialog(NeedBlood.this);

        @Override
        protected void onPostExecute(List<Contact> result) {
            super.onPostExecute(result);
            dialog.dismiss();
            adpt.setItemList(result);
            adpt.notifyDataSetChanged();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Downloading contacts...");
            dialog.show();
        }

        @Override
        protected List<Contact> doInBackground(String... params) {
            List<Contact> result = new ArrayList<Contact>();

            try {
                URL u = new URL(params[0]);

                HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                conn.setRequestMethod("GET");

                conn.connect();
                InputStream is = conn.getInputStream();

                // Read the stream
                byte[] b = new byte[1024];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                while ( is.read(b) != -1)
                    baos.write(b);

                String JSONResp = new String(baos.toByteArray());

                JSONArray arr = new JSONArray(JSONResp);
                for (int i=0; i < arr.length(); i++) {
                    result.add(convertContact(arr.getJSONObject(i)));
                }

                return result;
            }
            catch(Throwable t) {
                t.printStackTrace();
            }
            return null;
        }

        private Contact convertContact(JSONObject obj) throws JSONException {
            String name = obj.getString("name");
            String bloodgrp = obj.getString("bloodgrp");
            String address = obj.getString("address");
            String contactno = obj.getString("contactno");

            return new Contact(name, bloodgrp, address, contactno);
        }

    }



}
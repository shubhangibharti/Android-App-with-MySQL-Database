package com.shubhangi.orders;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;

public class MainActivity extends Activity {
    EditText OrderID, RestrauntID, UserID, Address, OrderDetails, BillingAmount;
    String oid, rid, uid, add, od, ba;
    private JSONObject json;

    private int success = 0;
    private String path = "http://yourdomain/order.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OrderID = (EditText) findViewById(R.id.etOrderID);
        RestrauntID = (EditText) findViewById(R.id.etRestaurantID);
        UserID = (EditText) findViewById(R.id.etUserID);
        Address = (EditText) findViewById(R.id.etAddress);
        OrderDetails = (EditText) findViewById(R.id.etDetails);
        BillingAmount = (EditText) findViewById(R.id.etAmount);

        Button go = (Button) findViewById(R.id.bGo);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oid = OrderID.getText().toString();
                rid = RestrauntID.getText().toString();
                uid = UserID.getText().toString();
                add = Address.getText().toString();
                od = OrderDetails.getText().toString();
                ba = BillingAmount.getText().toString();

                new PostDataTOServer().execute();
            }
        });
    }

    private class PostDataTOServer extends AsyncTask<Void, Void, Void> {

        String response = "";
        //Create hashmap Object to send parameters to web service
        HashMap<String, String> postDataParams;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {

                String link = path;
                String data = URLEncoder.encode("orderid", "UTF-8") + "=" +
                        URLEncoder.encode(oid, "UTF-8");
                data += "&" + URLEncoder.encode("restid", "UTF-8") + "=" +
                        URLEncoder.encode(rid, "UTF-8");
                data += "&" + URLEncoder.encode("uid", "UTF-8") + "=" +
                        URLEncoder.encode(uid, "UTF-8");
                data += "&" + URLEncoder.encode("add", "UTF-8") + "=" +
                        URLEncoder.encode(add, "UTF-8");
                data += "&" + URLEncoder.encode("orderdetail", "UTF-8") + "=" +
                        URLEncoder.encode(od, "UTF-8");
                data += "&" + URLEncoder.encode("amount", "UTF-8") + "=" +
                        URLEncoder.encode(ba, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                return null;
            } catch (Exception e) {
                String s = new String("Exception: " + e.getMessage());
                System.out.print(s);
                return null;

            }
        }





        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

//            if (success == 1) {
//                Toast.makeText(getApplicationContext(), "Ordered successfully..!", Toast.LENGTH_LONG).show();
//            }
        }

    }
}

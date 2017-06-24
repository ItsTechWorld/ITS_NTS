package hecosoft.com.its_nts;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class NewSalesOrder extends NavigationDrawer {
    private static final String TAG_CName = "server_response";

    private static final String TAG_NAME = "name";



    // contacts JSONArray
    JSONArray contacts = null;

    // Hashmap for ListView
    //ArrayList<HashMap<String, String>> contactList;
ArrayList<String> contactList;

    AutoCompleteTextView name;
    EditText id1;
    Spinner ship,saletype,saleperson,invoice;
    String[] country = {"Pakistan", "India", "Uk", "China"};
    ArrayAdapter<String> spinner;
    ArrayAdapter<String> countryListAdapter;
    ArrayAdapter<String> auto;
    Button net;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_new_sales_order, null, false);
        drawerLayout.addView(contentView, 0);
        //contactList = new ArrayList<HashMap<String, String>>();
        contactList=new ArrayList<>();
        spinner=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,country);
        txt.setVisibility(View.INVISIBLE);
        img.setVisibility(View.INVISIBLE);
        name=(AutoCompleteTextView)findViewById(R.id.nameautocmplete);
        id1=(EditText)findViewById(R.id.showid);
        id1.setText("");
        name.setText("");

        ship=(Spinner)findViewById(R.id.spinnershipto);
        ship=(Spinner)findViewById(R.id.spinnersale);
        ship=(Spinner)findViewById(R.id.spinersaletext);
        ship=(Spinner)findViewById(R.id.spinnerinvoicetext);
        //Intent i=getIntent();
        //Bundle b=i.getExtras();
        //String n=b.getString("uname");


get1 obj=new get1();
        obj.execute();
        countryListAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,contactList);
        name.setAdapter(countryListAdapter);
        ship.setAdapter(spinner);
        name.setThreshold(1);
        name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // String s = (String) parent.getItemAtPosition(position);
               // id1.setText(s);
            }
        });

    }
    private class get1 extends AsyncTask<Void, Void, Void> {
        StringBuilder sb = new StringBuilder();
        String url = "http://192.168.1.199/nts/get.php";



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {


                URL u = new URL(url);
                HttpURLConnection con = (HttpURLConnection) u.openConnection();
                con.setDoOutput(true);
                con.setRequestMethod("GET");
                InputStream is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                String n = sb.toString();
                Log.d("Array",n);
                if (n != null) {


                    JSONObject jsonObj = new JSONObject(n);

                    // Getting JSON Array node
                    contacts = jsonObj.getJSONArray(TAG_CName);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);


                        String name = c.getString(TAG_NAME);

                        /*HashMap<String, String> contact = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        contact.put(TAG_CUSTOMERID, id);
                        contact.put(TAG_CUSTOMERCODEACTIVE, custmer);
                        contact.put(TAG_NAME, name);
                        contact.put(TAG_ENTRYFIRSTNAME, fname);
                        contact.put(TAG_ADDRESS, address);

                        contact.put(TAG_CITY, city);
                        contact.put(TAG_PHONE, phone);

                        // adding contact to contact list
                        contactList.add(contact);
*/
                        ArrayList<String> contact=new ArrayList<>();
                        contact.add( name);
                        contactList.add(contact.toString());
                    }

                } else {
                    Log.e("Connection Handler", "Couldn't get any data from the url");
                }

            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            Log.d("hello",contactList.toString());





            return null;
        }

        @Override
        protected void onPostExecute(Void s) {




        }
    }


}

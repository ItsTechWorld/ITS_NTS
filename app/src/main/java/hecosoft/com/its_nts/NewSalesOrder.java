package hecosoft.com.its_nts;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    EditText id1,orderdate,reqdate;
    Spinner ship,saletype,saleperson,invoice;
    String[] country = {"Pakistan", "India", "Uk", "China"};
    String[] sales={"Cash Sales","Credit Sales"};
    String[] invoicedata={"Sale on Credit","30 days","net cash 10 days","Net Cash"};
    String[] salespersondata={"Adnan Perwaiz","Hafiz Mubeen","Mian Sardar","Mohammad Rafi","Yousaf Qureshi"};
    ArrayAdapter<String> salesadapter,salespersonadapter,invoiceadapter;
    Calendar datetime=Calendar.getInstance();
    SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
    ArrayAdapter<String> spinner;
    ArrayAdapter<String> countryListAdapter;
    ArrayAdapter<String> auto;
    Button net;
    int i=0;
    ImageButton btn,req;
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
        salesadapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,sales);
        salespersonadapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,salespersondata);
        invoiceadapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,invoicedata);
        txt.setVisibility(View.INVISIBLE);
        img.setVisibility(View.INVISIBLE);
        name=(AutoCompleteTextView)findViewById(R.id.nameautocmplete);
        id1=(EditText)findViewById(R.id.showid);
        id1.setText("");
        name.setText("");

        ship=(Spinner)findViewById(R.id.spinnershipto);
        saletype=(Spinner)findViewById(R.id.spinnersale);
        saleperson=(Spinner)findViewById(R.id.spinersaletext);
        invoice=(Spinner)findViewById(R.id.spinnerinvoicetext);
        //Intent i=getIntent();
        //Bundle b=i.getExtras();
        //String n=b.getString("uname");
        orderdate=(EditText)findViewById(R.id.orderdate);
        reqdate=(EditText)findViewById(R.id.reqiredate);
        updatelabel();
        updatelabel2();

get1 obj=new get1();
        obj.execute();
        countryListAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,contactList);
        name.setAdapter(countryListAdapter);
        ship.setAdapter(spinner);
        saletype.setAdapter(salesadapter);
        saleperson.setAdapter(salespersonadapter);
        invoice.setAdapter(invoiceadapter);
        name.setThreshold(1);
        btn=(ImageButton)findViewById(R.id.showtime);
        req=(ImageButton)findViewById(R.id.showreqtime);
        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedate();
                i = 1;
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedate();
            }
        });
        name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition(position);
                id1.setText(s);
            }
        });

    }
    private void updatelabel()
    {
        orderdate.setText(dateFormat.format(datetime.getTime()));
    }
    private void updatelabel2()
    {
        reqdate.setText(dateFormat.format(datetime.getTime()));
    }
    private void updatedate()
    {
        new DatePickerDialog(this,d,datetime.get(Calendar.YEAR),datetime.get(Calendar.MONTH),datetime.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            datetime.set(Calendar.YEAR, year);
            datetime.set(Calendar.MONTH,monthOfYear);
            datetime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            if(i==1)
            {
                updatelabel2();
                i=0;
            }
            else {
                updatelabel();
            }
        }
    };
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

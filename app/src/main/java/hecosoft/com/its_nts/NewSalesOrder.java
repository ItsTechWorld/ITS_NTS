package hecosoft.com.its_nts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewSalesOrder extends AppCompatActivity {
AutoCompleteTextView name;
    EditText id1;
    Spinner ship;
    String[] country = {"Pakistan", "India", "Uk", "China"};
    ArrayAdapter<String> countryListAdapter;
    ArrayAdapter<String> auto;
    Button net;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sales_order);
        name=(AutoCompleteTextView)findViewById(R.id.nameautocmplete);
        id1=(EditText)findViewById(R.id.showid);
        net=(Button)findViewById(R.id.nextbtn);
        ship=(Spinner)findViewById(R.id.spinnershipto);
        Intent i=getIntent();
        Bundle b=i.getExtras();
        String n=b.getString("uname");
        countryListAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, country);
        auto=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,country);
        name.setAdapter(auto);
        ship.setAdapter(countryListAdapter);
        name.setThreshold(1);
        name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition(position);
                id1.setText(s);
            }
        });
        net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(NewSalesOrder.this,SalesOrder2.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_salesorder,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.fareed)
        {
            Intent i=new Intent(getApplicationContext(),EmpSchedule.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}

package hecosoft.com.its_nts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sales_order);
    name=(AutoCompleteTextView)findViewById(R.id.nameautocmplete);
        id1=(EditText)findViewById(R.id.showid);
        ship=(Spinner)findViewById(R.id.spinnershipto);
        countryListAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, country);
auto=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,country);
        name.setAdapter(auto);
        ship.setAdapter(countryListAdapter);
    name.setThreshold(2);
        name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s=(String)parent.getItemAtPosition(position);
                id1.setText(s);
            }
        });
    }
}

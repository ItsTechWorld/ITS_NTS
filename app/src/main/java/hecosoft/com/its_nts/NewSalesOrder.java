package hecosoft.com.its_nts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

public class NewSalesOrder extends AppCompatActivity {
AutoCompleteTextView name;
    EditText id;
    Spinner ship;
    String[] country = {"Pakistan", "India", "Uk", "China"};
    ArrayAdapter<String> countryListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sales_order);
    name=(AutoCompleteTextView)findViewById(R.id.nameautocmplete);
        id=(EditText)findViewById(R.id.showid);
        ship=(Spinner)findViewById(R.id.spinnershipto);
        countryListAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, country);

        ship.setAdapter(countryListAdapter);
    }
}

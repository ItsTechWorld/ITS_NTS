package hecosoft.com.its_nts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EmpSchedule extends AppCompatActivity {
    String[] country = {"Pakistan", "India", "Uk", "China"};
    ArrayAdapter<String> countryListAdapter;
    Spinner showmnth,showyear;
    AutoCompleteTextView editname;
    EditText editid;
    Button btn;
    ArrayAdapter<String>  autoemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_schedule);
        editname=(AutoCompleteTextView)findViewById(R.id.employeename);
        editid  =(EditText)findViewById(R.id.empid);
        btn=(Button)findViewById(R.id.next);
        showmnth=(Spinner)findViewById(R.id.spinershowmonths);
        showyear=(Spinner)findViewById(R.id.spinershowyears);
        countryListAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, country);
        autoemp= new ArrayAdapter<String>( this,android.R.layout.simple_list_item_1,country);
        editname.setAdapter(autoemp);
        editname.setThreshold(1);
        showmnth.setAdapter(countryListAdapter);
        showyear.setAdapter(countryListAdapter);
        editname.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
                editid.setText(s);
            }
        });


    }
}

package hecosoft.com.its_nts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EmpSchedule extends AppCompatActivity {
    String[] country = {"Pakistan", "India", "Uk", "China"};
    ArrayAdapter<String> countryListAdapter;
    Spinner showmnth,showyear;
    EditText editname,editid;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_schedule);
        editname=(EditText)findViewById(R.id.employeename);
        editid  =(EditText)findViewById(R.id.empid);
        btn=(Button)findViewById(R.id.next);
        showmnth=(Spinner)findViewById(R.id.spinershowmonths);
        showyear=(Spinner)findViewById(R.id.spinershowyears);
        countryListAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, country);
        showmnth.setAdapter(countryListAdapter);
        showyear.setAdapter(countryListAdapter);
    }
}

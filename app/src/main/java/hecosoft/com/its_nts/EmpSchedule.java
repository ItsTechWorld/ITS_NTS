package hecosoft.com.its_nts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EmpSchedule extends NavigationDrawer {
    String[] month = {"jan", "Feb", "Mar", "Apr","May", "Jun", "July", "Aug","Sep", "Oct", "Nov", "December"};
    String[] year = {"2011", "2012", "2013", "2014","2015", "2016", "2017"};
    String[] empname = {"Ali", "Asif", "Abubakar", "Faisal","Akbar", "Lateef", "Noman", "Sarmad","Bilal", "Zain", "Ilyaas", "Waleed"};
    ArrayAdapter<String> monthauto;
    ArrayAdapter<String> yearauto;
    Spinner showmnth,showyear;
    AutoCompleteTextView editname;
    EditText editid;
    Button btn;
    ArrayAdapter<String>  autoemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_emp_schedule, null, false);
        drawerLayout.addView(contentView, 0);

        txt.setVisibility(View.INVISIBLE);
        img.setVisibility(View.INVISIBLE);

        editname=(AutoCompleteTextView)findViewById(R.id.employeename);
        editid  =(EditText)findViewById(R.id.empid);
        btn=(Button)findViewById(R.id.next);
        showmnth=(Spinner)findViewById(R.id.spinershowmonths);
        showyear=(Spinner)findViewById(R.id.spinershowyears);
        monthauto = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, month);
        yearauto = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, year);
        autoemp= new ArrayAdapter<String>( this,android.R.layout.simple_list_item_1,empname);
        editname.setAdapter(autoemp);
        editname.setThreshold(1);
        showmnth.setAdapter(monthauto);
        showyear.setAdapter(yearauto);
        editname.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
                editid.setText(s);
            }
        });


    }
}

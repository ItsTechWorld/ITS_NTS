package hecosoft.com.its_nts;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class SalesOrder2 extends AppCompatActivity {

    String[] sales={"Cash Sales","Credit Sales"};
    ArrayAdapter<String> salesadapter;
    Spinner salestypes;
    Calendar datetime=Calendar.getInstance();
    ImageButton btn;
    TextView text;
    EditText orderdate;
    DateFormat dateFormat=DateFormat.getDateInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order2);
    text=(TextView)findViewById(R.id.header2txt);
        salestypes=(Spinner)findViewById(R.id.spinersalestype);
        salesadapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sales);
        salestypes.setAdapter(salesadapter);
        orderdate=(EditText)findViewById(R.id.orderdate);
        btn=(ImageButton)findViewById(R.id.showtime);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedate();
            }
        });

    }
    private void updatelabel()
    {
orderdate.setText(dateFormat.format(datetime.getTime()));
    }
    private void updatedate()
    {
        new DatePickerDialog(this,d,datetime.get(Calendar.YEAR),datetime.get(Calendar.MONTH),datetime.get(Calendar.DAY_OF_MONTH)).show();
    }
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
datetime.set(Calendar.YEAR,year);
            datetime.set(Calendar.MONTH,monthOfYear);
            datetime.set(Calendar.DAY_OF_MONTH,dayOfMonth);
updatelabel();

        }
    };
}

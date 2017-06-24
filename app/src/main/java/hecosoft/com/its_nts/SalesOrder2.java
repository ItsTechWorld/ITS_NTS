package hecosoft.com.its_nts;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SalesOrder2 extends NavigationDrawer {

    String[] sales={"Cash Sales","Credit Sales"};
    ArrayAdapter<String> salesadapter,salespersonadapter,invoiceadapter;
    Spinner salestypes,salesperson,invoice;
    Calendar datetime=Calendar.getInstance();
    ImageButton btn,req;
    TextView text;
    EditText orderdate,reqdate;
    Button save;
int i=0;

    SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_sales_order2, null, false);
        drawerLayout.addView(contentView, 0);

        txt.setVisibility(View.INVISIBLE);
        img.setVisibility(View.INVISIBLE);
save=(Button)findViewById(R.id.btnsave);

        salestypes=(Spinner)findViewById(R.id.spinersalestype);
        salesperson=(Spinner)findViewById(R.id.spinersalesperson);
        invoice=(Spinner)findViewById(R.id.spinerinvoice);
        salesadapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sales);
        salespersonadapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sales);
        invoiceadapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sales);
        salestypes.setAdapter(salesadapter);
        salesperson.setAdapter(salespersonadapter);
        invoice.setAdapter(invoiceadapter);
        orderdate=(EditText)findViewById(R.id.orderdate);
        reqdate=(EditText)findViewById(R.id.reqiredate);
        updatelabel();
        updatelabel2();
        btn=(ImageButton)findViewById(R.id.showtime);
        req=(ImageButton)findViewById(R.id.showreqtime);
        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedate();
                i=1;
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Ready to Send",Toast.LENGTH_LONG).show();
            }
        });
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
}

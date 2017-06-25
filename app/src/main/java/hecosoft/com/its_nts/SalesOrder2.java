package hecosoft.com.its_nts;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SalesOrder2 extends NavigationDrawer {

    ListView mylist;
    Button save;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      LayoutInflater inflater = (LayoutInflater) this.getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);

      //inflate your activity layout here!
      View contentView = inflater.inflate(R.layout.activity_sales_order2, null, false);
      drawerLayout.addView(contentView, 0);

      txt.setVisibility(View.INVISIBLE);
      img.setVisibility(View.INVISIBLE);
      save = (Button) findViewById(R.id.btnsave);

  }

}


package hecosoft.com.its_nts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
Button btn1,btn2;
    EditText uname,upass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button)findViewById(R.id.login);
        btn2=(Button)findViewById(R.id.register);
uname=(EditText)findViewById(R.id.uname);
        upass=(EditText)findViewById(R.id.upass);
       btn1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(uname.getText().toString().equals("arsi")&&upass.getText().toString().equals("arsi"))
               {
                   Intent i=new Intent(getApplicationContext(),NewSalesOrder.class);
                   i.putExtra("uname",uname.getText().toString());
                   startActivity(i);
               }
           }
       });
    }


}

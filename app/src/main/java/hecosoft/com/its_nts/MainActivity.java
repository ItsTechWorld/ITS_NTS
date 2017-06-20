package hecosoft.com.its_nts;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
Button btn1,btn2;
    EditText uname,upass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button)findViewById(R.id.login);

uname=(EditText)findViewById(R.id.uname);
        upass=(EditText)findViewById(R.id.upass);
       btn1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               signin obj=new signin();
               String n=uname.getText().toString();
               String p=upass.getText().toString();
               obj.execute("login",n,p);
           }
       });
    }

    private class signin extends AsyncTask<String, Void, String>
    {
        StringBuilder sb=new StringBuilder();
        String url="http://192.168.1.6/nts/login.php";
        String name;
        String password;
        String message;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            message=params[0];
            if(message.equals("login")) {
                try {

                    name = params[1];
                    password = params[2];
                    URL u = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) u.openConnection();
                    con.setDoOutput(true);

                    con.setRequestMethod("POST");

                    OutputStream os = con.getOutputStream();
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                            URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                    bw.write(data);
                    bw.flush();
                    bw.close();
                    InputStream is = con.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    String m=sb.toString();

                    return sb.toString();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            if(s.equals("Login Successful"))
            {

                Intent i=new Intent(getApplicationContext(),NavigationDrawer.class);

                startActivity(i);
                finish();
            }
            else
            {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        }
    }

}

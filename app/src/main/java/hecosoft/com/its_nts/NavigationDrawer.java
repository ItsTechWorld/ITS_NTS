package hecosoft.com.its_nts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NavigationDrawer extends AppCompatActivity {
protected DrawerLayout drawerLayout;
    protected FrameLayout frameLayout;
protected ImageView img;
    protected TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

//        setSupportActionBar(toolbar);
        frameLayout = (FrameLayout)findViewById(R.id.content_frame);

        final ActionBar actionBar = getSupportActionBar();
txt=(TextView)findViewById(R.id.txtnav);
img=(ImageView)findViewById(R.id.imgnav);

        if (actionBar != null) {

            actionBar.setHomeAsUpIndicator(R.drawable.mainhaeader1);

            actionBar.setDisplayHomeAsUpEnabled(true);

        }
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);



        NavigationView view = (NavigationView) findViewById(R.id.navigation_view);

        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override public boolean onNavigationItemSelected(MenuItem menuItem) {


if(menuItem.getItemId()==R.id.employee_menu)
{


    Intent i=new Intent(NavigationDrawer.this,EmpSchedule.class);
    startActivity(i);
}
                else if(menuItem.getItemId()==R.id.sales_menu)
{

    Intent i=new Intent(NavigationDrawer.this,NewSalesOrder.class);
    startActivity(i);

}
else if(menuItem.getItemId()==R.id.logout)
{
    Intent i=new Intent(NavigationDrawer.this,MainActivity.class);
    startActivity(i);
    finish();
}
else if(menuItem.getItemId()==R.id.home)
{

    Intent i=new Intent(NavigationDrawer.this,NavigationDrawer.class);
    startActivity(i);
    finish();
}

                else
{
    Toast.makeText(getApplicationContext(), menuItem.getTitle() + " pressed", Toast.LENGTH_SHORT).show();
}
                menuItem.setChecked(true);

                drawerLayout.closeDrawers();

                return true;

            }

        });

    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                drawerLayout.openDrawer(GravityCompat.START);

                return true;

        }



        return super.onOptionsItemSelected(item);

    }

}

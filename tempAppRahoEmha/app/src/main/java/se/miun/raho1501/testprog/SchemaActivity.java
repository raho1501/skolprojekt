package se.miun.raho1501.testprog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

import static android.R.attr.data;

/**
 * Created by Rasmus on 2017-09-25.
 */


public class SchemaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        {
    private ListView list;
    protected ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schema);

/*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_schema);
        navigationView.setNavigationItemSelectedListener(this);
        */

        final TabHost host = (TabHost)findViewById(R.id.schema_host);
        host.setup();

        TabHost.TabSpec tab1 = host.newTabSpec("Tab 1");
        TabHost.TabSpec tab2 = host.newTabSpec("Tab 2");
        TabHost.TabSpec tab3 = host.newTabSpec("Tab 3");
        TabHost.TabSpec tab4 = host.newTabSpec("Tab 4");
        TabHost.TabSpec tab5 = host.newTabSpec("Tab 5");

<<<<<<< HEAD

        list = (ListView) findViewById(R.id.tab1listview);
        adapter = new ArrayAdapter<String>(SchemaActivity.this,R.layout.schema_view,
                                             R.id.tab1listview, arrayList);
=======
>>>>>>> 6244587bd9a7e8ffe710e98135fe58206d008d71
        //Tab 1
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("Mån");
        host.addTab(tab1);

        //Tab 2
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("Tis");
        host.addTab(tab2);

        //Tab 3
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("Ons");
        host.addTab(tab3);

        //Tab 4
        tab4.setContent(R.id.tab4);
        tab4.setIndicator("Tors");
        host.addTab(tab4);

        //Tab 5
        tab5.setContent(R.id.tab5);
        tab5.setIndicator("Fre");
        host.addTab(tab5);

<<<<<<< HEAD
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent in = new Intent(SchemaActivity.this,BokaLedighet.class);

                startActivityForResult(in,1);

            }
        });




=======
>>>>>>> 6244587bd9a7e8ffe710e98135fe58206d008d71
    }
            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.main, menu);
                return true;

            }
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                return super.onOptionsItemSelected(item);
            }

            @SuppressWarnings("StatementWithEmptyBody")
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_gallery) {
                    // Handle the camera action
                    //setContentView(R.layout.schema_view);
                    onBackPressed();
                } else if (id == R.id.nav_boka_ledighet) {
                    Intent i = new Intent(SchemaActivity.this, BokaLedighet.class);
                    startActivity(i);

                } /*else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }

    @Override
public void onActivityResult(int requestCode, int resultCode, Intent data)
{
    this.invalidateOptionsMenu();
    if (resultCode == Activity.RESULT_OK)
    {
        if (data != null) {
            String re = data.getStringExtra("result");
            System.out.println(re);

        }
    }
}




    @Override
    public void onBackPressed()
    {
        finish();
    }
}

package se.miun.raho1501.testprog;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by Rasmus on 2017-09-25.
 */

public class SchemaActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schema_view);
        TabHost host = (TabHost)findViewById(R.id.schema_host);
        host.setup();

        TabHost.TabSpec tab1 = host.newTabSpec("Tab 1");
        TabHost.TabSpec tab2 = host.newTabSpec("Tab 2");
        TabHost.TabSpec tab3 = host.newTabSpec("Tab 3");
        TabHost.TabSpec tab4 = host.newTabSpec("Tab 4");
        TabHost.TabSpec tab5 = host.newTabSpec("Tab 5");

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

    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}

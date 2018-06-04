package ba.fit.app.hci_odbrana;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;


public class NavigationDrawerActivity extends AppCompatActivity
{


    private DrawerLayout drawer_Layout;
    private ListView left_drawer;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence drawerNaslov;
    private CharSequence activityNaslov;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Fabric.with(this, new Crashlytics());
        init();


    }

    private void init() {
        activityNaslov = drawerNaslov = getTitle();
        drawer_Layout = (DrawerLayout) findViewById(R.id.drawer_Layout);
        left_drawer = (ListView) findViewById(R.id.left_drawer);

        drawer_Layout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        left_drawer.setAdapter(new ArrayAdapter<>(this, R.layout.stavka_drawer_list, getNaslovi()));
        left_drawer.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                selectItem(position);
            }
        });

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawer_Layout,         /* DrawerLayout object */
                R.string.drawer_otvoreno,  /* "open drawer" description for accessibility */
                R.string.drawer_zatvoreno  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(activityNaslov);
                // invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(drawerNaslov);
                //    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawer_Layout.setDrawerListener(mDrawerToggle);
    }

    protected String[] getNaslovi()
    {
        return new String[]{
                "Dodavanje odjeljenja",
                "Pregled odjeljenja",
                "I td",
                "Logout"};
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }


    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;

        if (position == 0)
            fragment = new Paket1Fragment();

        if (position == 1)
            fragment = new Paket3Fragment();

        if (position == 2)
            fragment = new Paket1Fragment();

        if (position == 3)
        {
            //Sesija.setLogiraniKorisnik(null);
            startActivity(new Intent(this, KontejnerActivity.class));
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.kontejnerID, fragment).commit();

        // update selected item and title, then close the drawer
        left_drawer.setItemChecked(position, true);
        setTitle(getNaslovi()[position]);
        drawer_Layout.closeDrawer(left_drawer);
    }



    @Override
    public void setTitle(CharSequence title) {
        activityNaslov = title;
        getSupportActionBar().setTitle(activityNaslov);
    }
}


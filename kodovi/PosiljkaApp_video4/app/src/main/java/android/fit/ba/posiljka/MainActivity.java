package android.fit.ba.posiljka;

import android.content.res.Configuration;
import android.fit.ba.posiljka.fragments.PosiljkaListFragment;
import android.fit.ba.posiljka.helper.MyFragmentUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Locale locale = new Locale("de");
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        setContentView(R.layout.activity_main);

        MyFragmentUtils.openAsReplace(this, R.id.mjestoFragment, PosiljkaListFragment.newInstance());

    }


}

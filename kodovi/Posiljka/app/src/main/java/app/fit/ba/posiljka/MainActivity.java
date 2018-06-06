package app.fit.ba.posiljka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.fit.ba.posiljka.fragments.PosiljkaListFragment;
import app.fit.ba.posiljka.helper.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Util.otvoriFragmentKaoReplace(this, R.id.fragmentPlace, PosiljkaListFragment.newInstance());
    }
}

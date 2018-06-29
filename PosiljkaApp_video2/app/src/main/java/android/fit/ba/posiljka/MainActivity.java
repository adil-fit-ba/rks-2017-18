package android.fit.ba.posiljka;

import android.fit.ba.posiljka.fragments.PosiljkaListFragment;
import android.fit.ba.posiljka.helper.MyFragmentUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyFragmentUtils.openAsReplace(this, R.id.mjestoFragment, PosiljkaListFragment.newInstance());

    }


}

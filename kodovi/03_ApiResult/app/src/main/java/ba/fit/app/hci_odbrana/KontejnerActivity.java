package ba.fit.app.hci_odbrana;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;

import ba.fit.app.hci_odbrana.helper.Util;

public class KontejnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontejner);

       Button b = (Button) findViewById(R.id.buttonID);
        b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        ugradi_fragment();
    }
});

    }

    private void ugradi_fragment() {

        Fragment fragment = new Paket1Fragment();

        Util.otvoriFragmentKaoReplace(this, R.id.kontejnerID, fragment);
       // Util.otvoriFragmentKaoDijalog(this, fragment);
    }
}

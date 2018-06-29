package android.fit.ba.posiljka;

import android.content.Intent;
import android.fit.ba.posiljka.data.KorisnikPregledVM;
import android.fit.ba.posiljka.helper.MySession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        KorisnikPregledVM.Row x = MySession.getKorisnik();

        if (x==null)
            startActivity(new Intent(this, LoginActivity.class));
        else
            startActivity(new Intent(this, GlavniActivity.class));
    }
}

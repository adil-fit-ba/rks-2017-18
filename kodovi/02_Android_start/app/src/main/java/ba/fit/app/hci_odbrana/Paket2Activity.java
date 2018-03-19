package ba.fit.app.hci_odbrana;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ba.fit.app.hci_odbrana.podaci.KorisnikVM;
import ba.fit.app.hci_odbrana.podaci.PosiljkaVM;
import ba.fit.app.hci_odbrana.podaci.Storage;

public class Paket2Activity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket2);


        Button zavrsiButton = (Button) findViewById(R.id.zavrsiButton);
        zavrsiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnZavrsi_click();
            }


        });



    }

    private void do_btnZavrsi_click() {
        String primaoc = Paket1Activity.primaoc;

        KorisnikVM primaocKorisnik = Storage.getKorisnikByName(primaoc);
        if (primaocKorisnik == null)
            primaocKorisnik = new KorisnikVM(primaoc, "nn");

        PosiljkaVM model = new PosiljkaVM();
        model.primaoc = primaocKorisnik;
        model.posljiaoc = primaocKorisnik;

        Storage.getPosiljkeAll().add(model);

    startActivity(new Intent(this, Paket3Activity.class));
    }
}

package ba.fit.app.hci_odbrana;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ba.fit.app.hci_odbrana.helper.api.OdjeljenjeSaveVM;


public class Paket1Activity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket1);

        Button daljeButton = (Button) findViewById(R.id.daljeButton);
        final EditText odjeljenjeNazivText = (EditText) findViewById(R.id.OdjeljenjeNazivText);
        final EditText skolskaGodinaText = (EditText) findViewById(R.id.SkolskaGodinaText);



        Toast.makeText(this, "Kliknuto dugme dalje", Toast.LENGTH_LONG).show();

        daljeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Paket1Activity.this, "Kliknuto dugme dalje", Toast.LENGTH_LONG).show();

                OdjeljenjeSaveVM x = new OdjeljenjeSaveVM();
                x.oznaka = odjeljenjeNazivText.getText().toString();
                x.skolskaGodina = skolskaGodinaText.getText().toString();


                Intent intent = new Intent(Paket1Activity.this, Paket2Activity.class);
                intent.putExtra(Paket2Activity.argName, x);
                startActivity(intent);
            }
        });
    }
}

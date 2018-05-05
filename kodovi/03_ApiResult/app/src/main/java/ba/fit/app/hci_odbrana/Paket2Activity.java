package ba.fit.app.hci_odbrana;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import ba.fit.app.hci_odbrana.helper.ApiTask;
import ba.fit.app.hci_odbrana.helper.api.OdjeljenjeApi;
import ba.fit.app.hci_odbrana.helper.api.OdjeljenjeNastavniciVM;
import ba.fit.app.hci_odbrana.helper.api.OdjeljenjeSaveVM;

public class Paket2Activity extends FragmentActivity {

    public static String argName="nn234yd,..523x!";
    private OdjeljenjeSaveVM saveVM;
    private Spinner spinnerRazrednik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket2);

        saveVM = (OdjeljenjeSaveVM) getIntent().getSerializableExtra(argName);

        spinnerRazrednik = (Spinner) findViewById(R.id.spinnerRazrednik);


        OdjeljenjeApi.GetNastavnici(new ApiTask<OdjeljenjeNastavniciVM>() {
            @Override
            public void run(boolean isCommunicationOrParseError, boolean isException, int exceptionCode, String message,OdjeljenjeNastavniciVM value) {
                if (isException)
                    return;

                ArrayAdapter<String>adapter = new ArrayAdapter<String>(Paket2Activity.this, android.R.layout.simple_spinner_item, value.texts);

                spinnerRazrednik.setAdapter(adapter);
            }
        });


        Button zavrsiButton = (Button) findViewById(R.id.zavrsiButton);
        zavrsiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnZavrsi_click();
            }


        });
    }

    private void do_btnZavrsi_click() {

        OdjeljenjeApi.Save(saveVM, new ApiTask<Void>() {
            @Override
            public void run(boolean isCommunicationOrParseError, boolean isException, int exceptionCode, String message, Void value) {
                if (isException)
                {
                    Toast.makeText(Paket2Activity.this, message, Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(Paket2Activity.this, "Odjeljenje je uspješno dodato", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Paket2Activity.this, Paket3Activity.class));
                }
            }
        });

    }
}

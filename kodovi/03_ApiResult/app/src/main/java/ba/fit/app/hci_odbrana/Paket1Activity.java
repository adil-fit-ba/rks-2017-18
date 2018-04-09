package ba.fit.app.hci_odbrana;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ba.fit.app.hci_odbrana.helper.ApiTask;
import ba.fit.app.hci_odbrana.helper.api.AdminOdjeljenjaApi;
import ba.fit.app.hci_odbrana.helper.api.AdminOdjeljenjaVM;

public class Paket1Activity extends FragmentActivity {

    public static String primaoc = "";
    public static String posiljaoc = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket1);

        Button daljeButton = (Button) findViewById(R.id.daljeButton);
        final EditText posiljaocText = (EditText) findViewById(R.id.posiljaocText);
        final EditText primaocText = (EditText) findViewById(R.id.primaocText);



        Toast.makeText(this, "Kliknuto dugme dalje", Toast.LENGTH_LONG).show();

        daljeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Paket1Activity.this, "Kliknuto dugme dalje", Toast.LENGTH_LONG).show();

                primaoc = primaocText.getText().toString();
                posiljaoc = posiljaocText.getText().toString();


                AdminOdjeljenjaApi.Predmeti(new ApiTask<AdminOdjeljenjaVM>() {
                    @Override
                    public void run(boolean isCommunicationOrParseError, boolean isException, int exceptionCode, String message, AdminOdjeljenjaVM value) {
                        if (isException)
                        {
                            Toast.makeText(Paket1Activity.this, message, Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            int size = value.rows.size();
                            Toast.makeText(Paket1Activity.this, "Broj predmta" + size, Toast.LENGTH_LONG).show();
                        }
                    }
                });

                //startActivity(new Intent(Paket1Activity.this, Paket2Activity.class));
            }
        });
    }
}

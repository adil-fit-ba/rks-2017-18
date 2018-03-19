package ba.fit.app.hci_odbrana;

import android.app.Notification;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

                startActivity(new Intent(Paket1Activity.this, Paket2Activity.class));
            }
        });
    }
}

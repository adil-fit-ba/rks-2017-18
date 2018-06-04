package ba.fit.app.hci_odbrana;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ba.fit.app.hci_odbrana.helper.Util;
import ba.fit.app.hci_odbrana.helper.api.OdjeljenjeSaveVM;


public class Paket1Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_paket, container, false);

        Button daljeButton = (Button)view. findViewById(R.id.daljeButton);
        final EditText odjeljenjeNazivText = (EditText)view. findViewById(R.id.OdjeljenjeNazivText);
        final EditText skolskaGodinaText = (EditText)view. findViewById(R.id.SkolskaGodinaText);




        Toast.makeText(getActivity(), "Kliknuto dugme dalje", Toast.LENGTH_LONG).show();

        daljeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Kliknuto dugme dalje", Toast.LENGTH_LONG).show();

                OdjeljenjeSaveVM x = new OdjeljenjeSaveVM();
                x.oznaka = odjeljenjeNazivText.getText().toString();
                x.skolskaGodina = skolskaGodinaText.getText().toString();


                Paket2Fragment fragment = new Paket2Fragment();
                Bundle args = new Bundle();
                args.putSerializable(Paket2Fragment.argName, x);
                fragment.setArguments(args);
                Util.otvoriFragmentKaoReplace(getActivity(),R.id.kontejnerID, fragment);


            }
        });

        return view;
    }

}

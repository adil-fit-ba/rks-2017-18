package ba.fit.app.hci_odbrana;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import ba.fit.app.hci_odbrana.helper.ApiTask;
import ba.fit.app.hci_odbrana.helper.Util;
import ba.fit.app.hci_odbrana.helper.api.OdjeljenjeApi;
import ba.fit.app.hci_odbrana.helper.api.OdjeljenjeNastavniciVM;
import ba.fit.app.hci_odbrana.helper.api.OdjeljenjeSaveVM;

public class Paket2Fragment extends Fragment {

    public static String argName="nn234yd,..523x!";
    private OdjeljenjeSaveVM saveVM;
    private Spinner spinnerRazrednik;
    private OdjeljenjeNastavniciVM nastavniciVM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_paket2, container, false);

        saveVM = (OdjeljenjeSaveVM)getArguments().getSerializable(argName);

        spinnerRazrednik = (Spinner)view.findViewById(R.id.spinnerRazrednik);

        OdjeljenjeApi.GetNastavnici(new ApiTask<OdjeljenjeNastavniciVM>() {
            @Override
            public void run(boolean isCommunicationOrParseError, boolean isException, int exceptionCode, String message,OdjeljenjeNastavniciVM value) {
                if (isException)
                    return;

                nastavniciVM = value;

                ArrayAdapter<String>adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, value.texts);

                spinnerRazrednik.setAdapter(adapter);
            }
        });

        Button zavrsiButton = (Button) view.findViewById(R.id.zavrsiButton);
        zavrsiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnZavrsi_click();
            }


        });

        return view;
    }


    private void do_btnZavrsi_click()
    {

        saveVM.razrednikID = nastavniciVM.iDs.get(spinnerRazrednik.getSelectedItemPosition());

        OdjeljenjeApi.Save(saveVM, new ApiTask() {
            @Override
            public void run(boolean isCommunicationOrParseError, boolean isException, int exceptionCode, String message, Object value) {
                if (isException)
                {
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Odjeljenje je uspješno dodato", Toast.LENGTH_LONG).show();


                    Util.otvoriFragmentKaoReplace(getActivity(),R.id.kontejnerID,new Paket3Fragment());

                }
            }
        });

    }
}

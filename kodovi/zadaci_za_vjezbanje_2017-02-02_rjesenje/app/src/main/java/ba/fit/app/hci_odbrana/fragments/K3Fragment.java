package ba.fit.app.hci_odbrana.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ba.fit.app.hci_odbrana.R;
import ba.fit.app.hci_odbrana.dialogs.PretragaDialogFragment;
import ba.fit.app.hci_odbrana.helper.MyRunnable;
import ba.fit.app.hci_odbrana.helper.Util;
import ba.fit.app.hci_odbrana.podaci.KorisnikVM;
import ba.fit.app.hci_odbrana.podaci.PosiljkaVM;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link K3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class K3Fragment extends Fragment {


    private static String key_posiljka;

    public K3Fragment() {
        // Required empty public constructor
    }

    public static K3Fragment newInstance(PosiljkaVM posiljkaVM) {
        K3Fragment fragment = new K3Fragment();
        Bundle args = new Bundle();
        args.putSerializable(key_posiljka, posiljkaVM);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            posiljkaVM = (PosiljkaVM) getArguments().getSerializable(key_posiljka);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_k1, container, false);

        Button btnDalje= (Button) view.findViewById(R.id.btnDalje);

        btnDalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnDaljeClick();
            }
        });

        return view;
    }

    private void do_btnDaljeClick() {

        final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setTitle("Pitanje? ");
        adb.setMessage("Da li želite snimiti?\n");
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.setPositiveButton("Da", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Util.otvoriFragmentKaoReplace(getActivity(), R.id.fragmentPlace, K4Fragment.newInstance());

            }

        });

        adb.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // moveTaskToBack(true);
            }
        });
        adb.setCancelable(false);
        adb.show();

     }



    private PosiljkaVM posiljkaVM ;
}

package app.fit.ba.posiljka.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.fit.ba.posiljka.R;
import app.fit.ba.posiljka.helper.Util;
import app.fit.ba.posiljka.podaci.PosiljkaVM;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PosiljkaAdd2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PosiljkaAdd2Fragment extends Fragment {


    private static final String ARG_POSILJKA = "posiljka_key";

    private PosiljkaVM posiljkaVM;


    public PosiljkaAdd2Fragment() {
        // Required empty public constructor
    }

    public static PosiljkaAdd1Fragment newInstance(PosiljkaVM posiljkaVM) {
        PosiljkaAdd1Fragment fragment = new PosiljkaAdd1Fragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_POSILJKA, posiljkaVM);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            posiljkaVM = (PosiljkaVM) getArguments().getSerializable(ARG_POSILJKA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posiljka_add2, container, false);

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
                Util.otvoriFragmentKaoReplace(getActivity(), R.id.fragmentPlace, PosiljkaListFragment.newInstance());

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

}

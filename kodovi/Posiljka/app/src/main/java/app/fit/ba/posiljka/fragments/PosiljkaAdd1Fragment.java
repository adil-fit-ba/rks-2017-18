package app.fit.ba.posiljka.fragments;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import app.fit.ba.posiljka.R;
import app.fit.ba.posiljka.dialogs.PretragaDialogFragment;
import app.fit.ba.posiljka.helper.MyRunnable;
import app.fit.ba.posiljka.helper.Util;
import app.fit.ba.posiljka.podaci.KorisnikVM;
import app.fit.ba.posiljka.podaci.PosiljkaVM;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PosiljkaAdd1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PosiljkaAdd1Fragment extends Fragment {

    private static final String ARG_POSILJKA = "posiljka_key";

    private PosiljkaVM posiljkaVM;
    private EditText txtPosiljaocIme;
    private EditText txtPosiljaocPrezime;
    private EditText txtPosiljaocAdresa;
    private EditText txtPrimaocIme;
    private EditText txtPrimaocPrezime;
    private EditText txtPrimaocAdresa;


    public PosiljkaAdd1Fragment() {
        // Required empty public constructor
    }

    public static PosiljkaAdd1Fragment newInstance(PosiljkaVM posiljkaVM) {
        PosiljkaAdd1Fragment fragment = new PosiljkaAdd1Fragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_POSILJKA, posiljkaVM);
        fragment.setArguments(args);
        return fragment;
    }

    public static PosiljkaAdd1Fragment newInstance() {
        return new PosiljkaAdd1Fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            posiljkaVM = (PosiljkaVM) getArguments().getSerializable(ARG_POSILJKA);
        }

        if (posiljkaVM == null)
            posiljkaVM = new PosiljkaVM();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posiljka_add1, container, false);

        txtPosiljaocIme = view.findViewById(R.id.txtPosiljaocIme);
        txtPosiljaocPrezime = view.findViewById(R.id.txtPosiljaocPrezime);
        txtPosiljaocAdresa = view.findViewById(R.id.txtPosiljaocAdresa);

        txtPrimaocIme = view.findViewById(R.id.txtPrimaocIme);
        txtPrimaocPrezime = view.findViewById(R.id.txtPrimaocPrezime);
        txtPrimaocAdresa = view.findViewById(R.id.txtPrimaocAdresa);


        Button btnPosiljaocPromjeni= view.findViewById(R.id.btnPosiljaocPromjeni);
        Button btnPrimaocPromjeni= view.findViewById(R.id.btnPrimaocPromjeni);
        Button btnDalje= view.findViewById(R.id.btnDalje);

        btnPosiljaocPromjeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnPosiljaocPromjeniClick();
            }
        });

        btnPrimaocPromjeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnPrimaocPromjeniClick();
            }
        });

        btnDalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnDaljeClick();
            }
        });

        return view;
    }

    private void do_btnPosiljaocPromjeniClick() {
        MyRunnable<KorisnikVM> myCallBack = new MyRunnable<KorisnikVM>() {
            @Override
            public void run(KorisnikVM result) {
                posiljkaVM.posljiaoc = result;
                txtPosiljaocIme.setText(result.getIme());
                txtPosiljaocPrezime.setText(result.getPrezime());
                txtPosiljaocAdresa.setText(result.getOpstinaVM().toString());
            }
        };

        Util.otvoriFragmentKaoDijalog(getActivity(), PretragaDialogFragment.newInstance(myCallBack));
    }

    private void do_btnPrimaocPromjeniClick() {
        MyRunnable<KorisnikVM> myCallBack = new MyRunnable<KorisnikVM>() {
            @Override
            public void run(KorisnikVM result) {
                posiljkaVM.posljiaoc = result;
                txtPosiljaocIme.setText(result.getIme());
                txtPosiljaocPrezime.setText(result.getPrezime());
                txtPosiljaocAdresa.setText(result.getOpstinaVM().toString());
            }
        };

        Util.otvoriFragmentKaoDijalog(getActivity(), PretragaDialogFragment.newInstance(myCallBack));
    }


    private void do_btnDaljeClick() {
        Util.otvoriFragmentKaoReplace(getActivity(), R.id.fragmentPlace, PosiljkaAdd2Fragment.newInstance(posiljkaVM));
    }
}

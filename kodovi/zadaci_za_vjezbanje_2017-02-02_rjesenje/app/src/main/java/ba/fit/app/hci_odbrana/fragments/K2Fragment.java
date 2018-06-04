package ba.fit.app.hci_odbrana.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ba.fit.app.hci_odbrana.R;
import ba.fit.app.hci_odbrana.dialogs.PretragaDialogFragment;
import ba.fit.app.hci_odbrana.helper.MyRunnable;
import ba.fit.app.hci_odbrana.helper.Util;
import ba.fit.app.hci_odbrana.podaci.KorisnikVM;
import ba.fit.app.hci_odbrana.podaci.PosiljkaVM;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link K2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class K2Fragment extends Fragment {


    private static String key_posiljka;
    private EditText txtIme;
    private EditText txtPrezime;

    public K2Fragment() {
        // Required empty public constructor
    }

    public static K2Fragment newInstance(PosiljkaVM posiljkaVM) {
        K2Fragment fragment = new K2Fragment();
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

        Button btnPretraga= (Button) view.findViewById(R.id.btnPretraga);
        Button btnDalje= (Button) view.findViewById(R.id.btnDalje);
        btnPretraga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnPretragaClick();
            }
        });
        btnDalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnDaljeClick();
            }
        });
        txtIme = (EditText) view.findViewById(R.id.txtIme);
        txtPrezime = (EditText) view.findViewById(R.id.txtPrezime);

        return view;
    }

    private void do_btnDaljeClick() {

        Util.otvoriFragmentKaoReplace(getActivity(), R.id.fragmentPlace, K3Fragment.newInstance(posiljkaVM));
    }

    private void do_btnPretragaClick() {
        MyRunnable<KorisnikVM> myCallBack = new MyRunnable<KorisnikVM>() {
            @Override
            public void run(KorisnikVM result) {
                posiljkaVM.primaoc = result;
                txtIme.setText(result.getIme());
                txtPrezime.setText(result.getPrezime());
            }
        };


        Util.otvoriFragmentKaoDijalog(getActivity(), PretragaDialogFragment.newInstance(myCallBack));
    }


    private PosiljkaVM posiljkaVM ;
}

package app.fit.ba.posiljka.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import app.fit.ba.posiljka.R;
import app.fit.ba.posiljka.dialogs.PretragaDialogFragment;
import app.fit.ba.posiljka.helper.MyRunnable;
import app.fit.ba.posiljka.helper.MyFragmentUtils;
import app.fit.ba.posiljka.data.KorisnikVM;
import app.fit.ba.posiljka.data.PosiljkaVM;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PosiljkaAdd1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PosiljkaAdd1Fragment extends Fragment {


    private static final String ARG_POSILJKA = "posiljka_key";
    private static final String ARG_TIP = "tip_key";

    private PosiljkaVM posiljkaVM;
    private EditText txtIme;
    private EditText txtAdresa;


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
        if (getArguments() != null)
        {
            posiljkaVM = (PosiljkaVM) getArguments().getSerializable(ARG_POSILJKA);
        }

        if (posiljkaVM == null)
            posiljkaVM = new PosiljkaVM();

        getActivity().setTitle("Primaoc");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posiljka_add1, container, false);

        txtIme = view.findViewById(R.id.txtIme);
        txtAdresa = view.findViewById(R.id.txtAdresa);


        ImageView imgAdd= view.findViewById(R.id.imgAdd);
        Button btnDalje= view.findViewById(R.id.btnDalje);

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnPromjeniClick();
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

    private void do_btnPromjeniClick() {
        MyRunnable<KorisnikVM> myCallBack = new MyRunnable<KorisnikVM>() {
            @Override
            public void run(KorisnikVM result) {
                posiljkaVM.primaoc = result;
                txtIme.setText(result.getIme() + " " + result.getPrezime());
                txtAdresa.setText(result.getOpstinaVM().toString());
            }
        };

        MyFragmentUtils.openAsDialog(getActivity(), PretragaDialogFragment.newInstance(myCallBack));
    }


    private void do_btnDaljeClick() {

        if (posiljkaVM.primaoc == null)
        {
            Snackbar.make(getView(),"Za nastavak morate odabrati primaoca!",  Snackbar.LENGTH_LONG).show();
        }
        else
        {
            MyFragmentUtils.openAsReplace(getActivity(), R.id.fragmentPlace, PosiljkaAdd2Fragment.newInstance(posiljkaVM));
        }
    }
}

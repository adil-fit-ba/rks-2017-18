package app.fit.ba.posiljka.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import app.fit.ba.posiljka.R;
import app.fit.ba.posiljka.helper.MyFragmentUtils;
import app.fit.ba.posiljka.data.PosiljkaVM;
import app.fit.ba.posiljka.data.Storage;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PosiljkaAdd2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PosiljkaAdd2Fragment extends Fragment {


    private static final String ARG_POSILJKA = "posiljka_key";

    private PosiljkaVM posiljkaVM;
    private EditText txtMasa;
    private EditText txtNapomena;
    private EditText txtIznos;
    private Switch switchPlatiPouzecem;


    public PosiljkaAdd2Fragment() {
        // Required empty public constructor
    }

    public static PosiljkaAdd2Fragment newInstance(PosiljkaVM posiljkaVM) {
        PosiljkaAdd2Fragment fragment = new PosiljkaAdd2Fragment();
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

        getActivity().setTitle("Podaci o paketu");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posiljka_add2, container, false);

        txtMasa = view.findViewById(R.id.txtMasa);
        txtNapomena = view.findViewById(R.id.txtNapomena);
        txtIznos = view.findViewById(R.id.txtIznos);
        switchPlatiPouzecem = view.findViewById(R.id.switchPlatiPouzecem);

        Button btnDalje= view.findViewById(R.id.btnDalje);
        btnDalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnDaljeClick();
            }
        });

        return view;
    }

    private void do_btnDaljeClick() {
try {
    this.posiljkaVM.masa = Float.parseFloat(txtMasa.getText().toString());
    this.posiljkaVM.napomena = txtNapomena.getText().toString();
    this.posiljkaVM.iznos = Float.parseFloat(txtIznos.getText().toString());
    this.posiljkaVM.placaPouzecem = switchPlatiPouzecem.isSelected();
    Storage.addPosiljka(this.posiljkaVM);
    MyFragmentUtils.openAsReplace(getActivity(), R.id.fragmentPlace, PosiljkaListFragment.newInstance());
}
catch (Exception e)
{
    Snackbar.make(getView(),"Greška: " + e.getMessage(),  Snackbar.LENGTH_LONG).show();
}
     }

}

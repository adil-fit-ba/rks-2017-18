package android.fit.ba.posiljka.fragments;


import android.app.Fragment;
import android.fit.ba.posiljka.data.PosiljkaAddVM;
import android.fit.ba.posiljka.helper.MyFragmentUtils;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.fit.ba.posiljka.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PosiljkaAdd2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PosiljkaAdd2Fragment extends Fragment {
    public static final String POSILJKA_KEY  = "NekiKey";

    // TODO: Rename and change types of parameters
    private PosiljkaAddVM posiljkaVM;
    private EditText txtMasa;
    private EditText txtNapomena;
    private EditText txtIznos;
    private Switch switchPlatiPouzecem;
    private String tag="PosiljkaAdd2Fragment";


    // TODO: Rename and change types and number of parameters
    public static PosiljkaAdd2Fragment newInstance(PosiljkaAddVM  posiljkaVM) {
        PosiljkaAdd2Fragment fragment = new PosiljkaAdd2Fragment();
        Bundle args = new Bundle();
        args.putSerializable(POSILJKA_KEY, posiljkaVM);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            posiljkaVM = (PosiljkaAddVM) getArguments().getSerializable(POSILJKA_KEY);
        }
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

        Button btnDalje = view.findViewById(R.id.btnDalje);
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

          //  Storage.addPosiljka(posiljkaVM);
            MyFragmentUtils.openAsReplace(getActivity(), R.id.mjestoFragment, PosiljkaListFragment.newInstance());
        }catch (Exception e)
        {
            Toast.makeText(getActivity(), "Greška: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}

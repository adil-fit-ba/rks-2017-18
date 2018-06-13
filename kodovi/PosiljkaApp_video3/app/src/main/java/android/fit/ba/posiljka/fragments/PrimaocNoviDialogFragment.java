package android.fit.ba.posiljka.fragments;


import android.app.DialogFragment;
import android.app.Fragment;
import android.fit.ba.posiljka.R;
import android.fit.ba.posiljka.data.KorisnikVM;
import android.fit.ba.posiljka.data.OpstinaVM;
import android.fit.ba.posiljka.data.Storage;
import android.fit.ba.posiljka.helper.MyRunnable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;




/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrimaocNoviDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrimaocNoviDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private MyRunnable<KorisnikVM> pMyCallBack;
    private Spinner spinnerOpstina;
    private EditText txtPrezime;
    private EditText txtIme;
    private List<OpstinaVM> opstine;


    public PrimaocNoviDialogFragment() {
        // Required empty public constructor
    }


    public static PrimaocNoviDialogFragment newInstance(MyRunnable<KorisnikVM> myCallBack) {
        PrimaocNoviDialogFragment fragment = new PrimaocNoviDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, myCallBack);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pMyCallBack = (MyRunnable<KorisnikVM>) getArguments().getSerializable(ARG_PARAM1);
        }
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MojDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_primaoc_novi, container, false);
        view.findViewById(R.id.button_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });


        txtIme = view.findViewById(R.id.txtIme);
        txtPrezime = view.findViewById(R.id.txtPrezime);
        spinnerOpstina = view.findViewById(R.id.spinnerOpstina);
        view.findViewById(R.id.btnSnimi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnDodaj();
            }
        });
        
        popuniPodatke();

        return view;
    }
    private List<String> getOpstineString()
    {
        List<String> result = new ArrayList<>();

        for (OpstinaVM x : opstine) {
            result.add(x.getNaziv() + " " + x.getDrzava());
        }

        return result;
    }

    private void popuniPodatke() {

        opstine = Storage.getOpstine();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, getOpstineString());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOpstina.setAdapter(dataAdapter);

    }

    private void do_btnDodaj() {
        int position = spinnerOpstina.getSelectedItemPosition();
        OpstinaVM x = opstine.get(position);

        KorisnikVM korisnikVM = new KorisnikVM(txtIme.getText().toString(), txtPrezime.getText().toString(), x);

        Storage.addKorisnik(korisnikVM);

        pMyCallBack.run(korisnikVM);
        getDialog().dismiss();
    }



}

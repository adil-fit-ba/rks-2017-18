package android.fit.ba.posiljka.fragments;


import android.app.DialogFragment;
import android.content.Context;
import android.fit.ba.posiljka.data.KorisnikVM;
import android.fit.ba.posiljka.data.Storage;
import android.fit.ba.posiljka.helper.MyRunnable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.fit.ba.posiljka.R;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PretragaDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PretragaDialogFragment extends DialogFragment {


    public static final String NEKI_KEY = "neki_key";
    private ListView listView;
    private EditText editText;
    private MyRunnable<KorisnikVM> callback;

    // TODO: Rename and change types and number of parameters
    public static PretragaDialogFragment newInstance(MyRunnable myCallback) {
        PretragaDialogFragment fragment = new PretragaDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(NEKI_KEY, myCallback);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            callback = (MyRunnable<KorisnikVM>) getArguments().getSerializable(NEKI_KEY);
        }

        setStyle(STYLE_NORMAL, R.style.MojDialog );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pretraga_dialog, container, false);

        listView = view.findViewById(R.id.listView);
        editText = view.findViewById(R.id.editText);
        Button btnTrazi = view.findViewById(R.id.btnTrazi);
        btnTrazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnTraziClick();
            }
        });
        return view;
    }

    private void do_btnTraziClick() {
        String query = editText.getText().toString();
        popuniPodatke(query);
    }

    private void popuniPodatke(String query) {
        final List<KorisnikVM> podaci = Storage.getKorisniciByIme(query);

        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return podaci.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {

                if(view==null)
                {
                    LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.stavka_primaoci, viewGroup,false);
                }

                TextView txtIme = (TextView) view.findViewById(R.id.txtFirstLine);
                TextView txtAdresa = (TextView) view.findViewById(R.id.txtSecondLine);

                KorisnikVM x = podaci.get(position);


                txtAdresa.setText(x.getOpstinaVM().toString());
                txtIme.setText(x.getIme() + " " + x.getPrezime());


                return view;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KorisnikVM x = podaci.get(position);
                getDialog().dismiss();
                callback.run(x);
            }
        });
    }

}

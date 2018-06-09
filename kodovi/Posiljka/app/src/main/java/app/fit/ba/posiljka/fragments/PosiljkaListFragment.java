package app.fit.ba.posiljka.fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import app.fit.ba.posiljka.R;
import app.fit.ba.posiljka.helper.Util;
import app.fit.ba.posiljka.podaci.PosiljkaVM;
import app.fit.ba.posiljka.podaci.Storage;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PosiljkaListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PosiljkaListFragment extends Fragment {


    private ListView lvPopisPaketa;

    public PosiljkaListFragment() {
        // Required empty public constructor
    }

    public static PosiljkaListFragment newInstance() {
        return new PosiljkaListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posiljka_list, container, false);


        FloatingActionButton fab = view.findViewById(R.id.fab);
        lvPopisPaketa = view.findViewById(R.id.lvPopisPaketa);
        popuniPodatke();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_fabClick();
            }
        });


        return view;
    }

    private void popuniPodatke() {

        final List<PosiljkaVM> posiljke = Storage.getPosiljke();

        lvPopisPaketa.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return posiljke.size();
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
                PosiljkaVM x = posiljke.get(position);

                if(view==null)
                {
                    LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.stavka_posiljke, viewGroup,false);
                }

                TextView txtFirstLine = view.findViewById(R.id.txtFirstLine);
                TextView txtSecondLine = view.findViewById(R.id.txtSecondLine);
                TextView txtThirdLine = view.findViewById(R.id.txtThirdLine);
                TextView txtMeta = view.findViewById(R.id.txtMeta);


                txtFirstLine.setText(x.primaoc.getImePrezime());
                txtSecondLine.setText(x.primaoc.getOpstinaVM().toString());
                txtThirdLine.setText(x.masa + " kg  ");
                txtMeta.setText("broj: " + x.brojPosiljke);

                return view;
            }
        });
    }

    private void do_fabClick() {
        Snackbar.make(getView(), "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        Util.otvoriFragmentKaoReplace(getActivity(), R.id.fragmentPlace, PosiljkaAdd1Fragment.newInstance());
    }

}

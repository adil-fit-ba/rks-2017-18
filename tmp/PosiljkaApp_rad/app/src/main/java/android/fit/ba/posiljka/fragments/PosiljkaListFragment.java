package android.fit.ba.posiljka.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.fit.ba.posiljka.R;
import android.fit.ba.posiljka.helper.MyApiRequest;
import android.fit.ba.posiljka.helper.MyRunnable;
import android.fit.ba.posiljka.viewmodels.MyUrls;
import android.fit.ba.posiljka.viewmodels.PosiljkaPregledVM;
import android.fit.ba.posiljka.helper.MyFragmentUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PosiljkaListFragment extends Fragment {

    private ListView lvPosiljka;
    private FloatingActionButton btnDodaj;
    private BaseAdapter adapter;


    public  static PosiljkaListFragment newInstance()
    {
        return new PosiljkaListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_posiljka_list, container, false);

        btnDodaj = view.findViewById(R.id.fab);
        lvPosiljka = view.findViewById(R.id.lvPosiljka);
        popuniPodatkeTask();

        btnDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnDodajClick();
            }
        });
        
        return view;


    }

    private void do_btnDodajClick() {
        MyFragmentUtils.openAsReplace(getActivity(), R.id.mjestoFragment, PosiljkaAdd1Fragment.newInstance());
    }

    private void popuniPodatkeTask() {

//        new AsyncTask<Void, Void, PosiljkaPregledVM>(){
//            @Override
//            protected PosiljkaPregledVM doInBackground(Void... voids) {
//
//                String strResult = MyUrlConnection.Get("https://7ca34706.eu.ngrok.io/posiljka/getall");
//                PosiljkaPregledVM x = MyGson.build().fromJson(strResult, PosiljkaPregledVM.class);
//                return x;
//            }
//
//            @Override
//            protected void onPostExecute(PosiljkaPregledVM x) {
//                if (x !=null)
//                    popuniPodakte(x);
//            }
//        }.execute();

        MyApiRequest.Get(MyUrls.PosiljkaPregled(), new MyRunnable<PosiljkaPregledVM>() {
            @Override
            public void run(PosiljkaPregledVM x) {
                popuniPodakte(x);
            }
        });
    }

    private void popuniPodakte(final PosiljkaPregledVM podaci) {


        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return podaci.rows.size();
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
            public View getView(int position, View view, ViewGroup parent) {

                if (view == null) {
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.stavka_posiljka, parent, false);
                }
                TextView txtFirstLine = view.findViewById(R.id.txtFirstLine);
                TextView txtSecondLine = view.findViewById(R.id.txtSecondLine);
                TextView txtThirdLine = view.findViewById(R.id.txtThirdLine);
                TextView txtMeta = view.findViewById(R.id.txtMeta);


                PosiljkaPregledVM.Row x = podaci.rows.get(position);

                txtFirstLine.setText(x.primaocImePrezime);
                txtSecondLine.setText(x.primaocAdresa);
                txtThirdLine.setText(x.masa + " kg  ");
                txtMeta.setText("broj: " + x.brojPosiljke);
                return view;
            }
        };
        lvPosiljka.setAdapter(adapter);

        lvPosiljka.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                PosiljkaPregledVM.Row x = podaci.rows.get(position);
                do_listViewLongClick(x);
                return true;
            }
        });
    }

    private void do_listViewLongClick(final PosiljkaPregledVM.Row x) {

        final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setTitle("Pitanje?");
        adb.setMessage("Da li želite obrisati pošiljku br " + x.brojPosiljke + "?");

        adb.setPositiveButton("DA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Storage.removePosiljka(x);
                dialog.dismiss();
                adapter.notifyDataSetChanged();
            }
        });

        adb.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.show();
    }
}

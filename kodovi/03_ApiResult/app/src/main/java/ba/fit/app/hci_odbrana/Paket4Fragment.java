package ba.fit.app.hci_odbrana;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import ba.fit.app.hci_odbrana.helper.ApiTask;
import ba.fit.app.hci_odbrana.helper.api.OdjeljenjeApi;
import ba.fit.app.hci_odbrana.helper.api.OdjeljenjeIndexVM;

/**
 * Created by adil on 3/19/2018.
 */

public class Paket4Fragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_paket4, container, false);

        mRecyclerView = (RecyclerView)view. findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);



        OdjeljenjeApi.Index(new ApiTask<OdjeljenjeIndexVM>() {
            @Override
            public void run(boolean isCommunicationOrParseError, boolean isException, int exceptionCode, String message, OdjeljenjeIndexVM value) {
                if(!isException)
                    popuniListView(value);
            }
        });

        return view;
    }



    private void popuniListView(final OdjeljenjeIndexVM value) {

        // specify an adapter (see also next example)
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // create a new view
                View v =  LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.stavka_odjeljenje, parent, false);


                RecyclerView.ViewHolder vh = new RecyclerView.ViewHolder(v){

                };
                return vh;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                OdjeljenjeIndexVM.Row row = value.rows.get(position);

                // - get element from your dataset at this position
                // - replace the contents of the view with that element

                View view = holder.itemView;

                TextView oznakaOdjeljenja = (TextView) view.findViewById(R.id.oznakaOdjeljenjaTxt);
                oznakaOdjeljenja.setText(row.oznaka);

                TextView razrednik = (TextView) view.findViewById(R.id.razrednikTxt);
                razrednik.setText(row.razrednik);

                TextView razred = (TextView) view.findViewById(R.id.razredTxt);
                razred.setText(String.valueOf(row.razred));

                TextView skolskaGodina = (TextView) view.findViewById(R.id.skolskaGodinaTxt);
                skolskaGodina.setText(row.skolskaGodina);
            }

            @Override
            public int getItemCount() {
                return  value.rows.size();
            }
        });


//        mojListView.setAdapter(new BaseAdapter() {
//            @Override
//            public int getCount() {
//                return value.rows.size();
//            }
//
//            @Override
//            public Object getItem(int i) {
//                return null;
//            }
//
//            @Override
//            public long getItemId(int i) {
//                return 0;
//            }
//
//            @Override
//            public View getView(int i, View view, ViewGroup viewGroup) {
//
//
//                if (view == null) {
//
//                    LayoutInflater inflater = (LayoutInflater) getActivity()
//                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//                    view = (View) inflater.inflate(R.layout.stavka_odjeljenje, null);
//                }
//                OdjeljenjeIndexVM.Row row = value.rows.get(i);
//
//                TextView oznakaOdjeljenja = (TextView) view.findViewById(R.id.oznakaOdjeljenjaTxt);
//                oznakaOdjeljenja.setText(row.oznaka);
//
//                TextView razrednik = (TextView) view.findViewById(R.id.razrednikTxt);
//                razrednik.setText(row.razrednik);
//
//                TextView razred = (TextView) view.findViewById(R.id.razredTxt);
//                razred.setText(String.valueOf(row.razred));
//
//                TextView skolskaGodina = (TextView) view.findViewById(R.id.skolskaGodinaTxt);
//                skolskaGodina.setText(row.skolskaGodina);
//
//                return view;
//            }
//        });
    }
}

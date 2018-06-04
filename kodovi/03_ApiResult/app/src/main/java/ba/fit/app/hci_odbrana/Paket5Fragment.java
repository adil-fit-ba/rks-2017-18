package ba.fit.app.hci_odbrana;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ba.fit.app.hci_odbrana.helper.ApiTask;
import ba.fit.app.hci_odbrana.helper.Config;
import ba.fit.app.hci_odbrana.helper.MyGson;
import ba.fit.app.hci_odbrana.helper.UrlConnectionHelper;
import ba.fit.app.hci_odbrana.helper.api.OdjeljenjeApi;
import ba.fit.app.hci_odbrana.helper.api.OdjeljenjeIndexVM;

import static ba.fit.app.hci_odbrana.helper.StreamTools.readStream;
import static ba.fit.app.hci_odbrana.helper.api.OdjeljenjeApi.CONTROLLER;

/**
 * Created by adil on 3/19/2018.
 */

public class Paket5Fragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_paket4, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);

//        OdjeljenjeApi.Index(new ApiTask<OdjeljenjeIndexVM>() {
//            @Override
//            public void run(boolean isCommunicationOrParseError, boolean isException, int exceptionCode, String message, OdjeljenjeIndexVM value) {
//                if(!isException)
//                    popuniListView(value);
//            }
//        });


        final HttpURLConnection urlConnection = null;
        final String s = Config.baseUrl
                .buildUpon()
                .appendPath(CONTROLLER)
                .appendPath("Index")
                .appendQueryParameter("razred", "")
                .toString();

        new AsyncTask<Void, Void, OdjeljenjeIndexVM>() {
            @Override
            protected OdjeljenjeIndexVM doInBackground(Void... integers) {
                OdjeljenjeIndexVM r= UrlConnectionHelper.Get(s, OdjeljenjeIndexVM.class);
                return r;
            }

            @Override
            protected void onPostExecute(OdjeljenjeIndexVM r) {
                popuniListView(r);
            }
        }.execute();


        return view;
    }



    private void popuniListView(final OdjeljenjeIndexVM value) {
        recyclerView.setAdapter(new  RecyclerView.Adapter() {

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stavka_odjeljenje2, viewGroup, false);
                return new RecyclerView.ViewHolder(v){};
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                OdjeljenjeIndexVM.Row row = value.rows.get(position);

                View convertView = holder.itemView;

                TextView oznakaOdjeljenja = (TextView) convertView.findViewById(R.id.oznakaOdjeljenjaTxt);
                oznakaOdjeljenja.setText(row.oznaka);

                TextView razrednik = (TextView) convertView.findViewById(R.id.razrednikTxt);
                razrednik.setText(row.razrednik);

                TextView razred = (TextView) convertView.findViewById(R.id.razredTxt);
                razred.setText(String.valueOf(row.razred));

                TextView skolskaGodina = (TextView) convertView.findViewById(R.id.skolskaGodinaTxt);
                skolskaGodina.setText(row.skolskaGodina);

            }

            @Override
            public int getItemCount() {
                return value.rows.size();
            }
        });
    }


}

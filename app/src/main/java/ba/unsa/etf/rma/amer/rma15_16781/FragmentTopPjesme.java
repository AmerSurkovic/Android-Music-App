package ba.unsa.etf.rma.amer.rma15_16781;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amer on 4/28/16.
 */
public class FragmentTopPjesme extends Fragment {

    private Muzicar muzicar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.top5_pjesama_fragment, containter, false);

        if(getArguments().containsKey("muzicar")) {
            muzicar = getArguments().getParcelable("muzicar");

            List<String> songs = new ArrayList<>(muzicar.getPjesme());

            ListView lv_songs = (ListView) v.findViewById(R.id.songs);
            lv_songs.setDivider(null);
            lv_songs.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.objekat_pjesma, R.id.imePjesme, songs));

            lv_songs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent ytIntent = new Intent(Intent.ACTION_SEARCH);
                    ytIntent.setPackage("com.google.android.youtube");
                    ytIntent.putExtra("query", parent.getItemAtPosition(position).toString() + " " + muzicar.getIme());
                    ytIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (ytIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(ytIntent);
                    } else {
                        Toast.makeText(getActivity(), "Instalirajte YouTube aplikaciju.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        return v;
    }

}

package ba.unsa.etf.rma.amer.rma15_16781;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

/**
 * Created by amer on 4/28/16.
 */
public class FragmentSlicniMuzicari extends Fragment {

    private Muzicar muzicar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.slicni_muzicari_fragment, containter, false);

        if(getArguments().containsKey("muzicar")) {

            muzicar = getArguments().getParcelable("muzicar");

            List<Muzicar> muzicari = muzicar.getSlicniMuzicari();
            ListView lv = (ListView)v.findViewById(R.id.slicni_muzicari);
            lv.setAdapter(new MyArrayAdapter(getActivity().getApplicationContext(), R.layout.list_item, muzicari));
        }

        return v;
    }

}

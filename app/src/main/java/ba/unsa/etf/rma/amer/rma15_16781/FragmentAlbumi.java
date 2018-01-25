package ba.unsa.etf.rma.amer.rma15_16781;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by amer on 5/27/16.
 */
public class FragmentAlbumi extends Fragment {

    private Muzicar muzicar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState){
        View vi = inflater.inflate(R.layout.top_albumi_fragment, containter, false);

        if(getArguments().containsKey("muzicar")) {

            muzicar = getArguments().getParcelable("muzicar");
            ArrayList<String> albumi = muzicar.getAlbumi();
            final ArrayList<String> albumi_url = muzicar.getAlbumi_url();
            ListView lv = (ListView)vi.findViewById(R.id.topAlbumiLista_fragment);
            lv.setDivider(null);
            ArrayAdapter<String> mojAdapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item_album,R.id.fragmentAlbum_imeAlbuma, albumi);
            lv.setAdapter(mojAdapter);
            lv.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String url = albumi_url.get(position).toString();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
        }

        return vi;
    }
}

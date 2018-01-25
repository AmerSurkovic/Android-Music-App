package ba.unsa.etf.rma.amer.rma15_16781;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by amer on 4/26/16.
 */
public class FragmentDetalji extends Fragment {

    private Muzicar muzicar;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View iv= inflater.inflate(R.layout.detalji_fragment, container, false);

        if(getArguments()!=null && getArguments().containsKey("muzicar")){
            muzicar=getArguments().getParcelable("muzicar");

            final TextView naziv = (TextView) iv.findViewById(R.id.detalji_nazivMuzicara);
            final TextView zanr = (TextView) iv.findViewById(R.id.detalji_zanrMuzicara);
            final TextView webStranica = (TextView) iv.findViewById(R.id.detalji_webMuzicara);

            naziv.setText(muzicar.getIme());
            zanr.setText(muzicar.getZanr());
            String hlink = muzicar.getWebStranica();

            hlink = "<a href='" + hlink + "'>" + hlink + "</a>";
            webStranica.setText(Html.fromHtml(hlink));

            webStranica.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = webStranica.getText().toString();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });

            webStranica.setText(Html.fromHtml(hlink));

            Button podijeliWeb = (Button)iv.findViewById(R.id.detalji_shareButton);
            podijeliWeb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, muzicar.getWebStranica());
                    intent.setType("text/plain");
                    startActivity(intent);
                }
            });

            Bundle args = new Bundle();
            args.putParcelable("muzicar", muzicar);

            final FragmentTopPjesme topPjesmeFragment = new FragmentTopPjesme();
            topPjesmeFragment.setArguments(args);

            final FragmentSlicniMuzicari slicniMuzicariFragment = new FragmentSlicniMuzicari();

            final FragmentAlbumi albumiFragment = new FragmentAlbumi();
            albumiFragment.setArguments(args);

            slicniMuzicariFragment.setArguments(args);

            getChildFragmentManager().beginTransaction()
                    .replace(R.id.slicni_muzicari_fragment, topPjesmeFragment)
                    .commit();

            Button topPjesmeButton = (Button)iv.findViewById(R.id.buttonTOP5);
            topPjesmeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.slicni_muzicari_fragment, topPjesmeFragment)
                            .commit();
                }
            });
            Button slicniMuzicariButton = (Button)iv.findViewById(R.id.buttonSlicniMuzicari);
            slicniMuzicariButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.slicni_muzicari_fragment, slicniMuzicariFragment)
                            .commit();
                }
            });
            Button topAlbumiButton = (Button)iv.findViewById(R.id.buttonAlbumi);
            topAlbumiButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    getChildFragmentManager().beginTransaction().replace(R.id.slicni_muzicari_fragment, albumiFragment).commit();
                }
            });

        }

        return iv;
    }

}

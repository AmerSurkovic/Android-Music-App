package ba.unsa.etf.rma.amer.rma15_16781;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by amer on 3/15/16.
 */
public class MyArrayAdapter extends ArrayAdapter<Muzicar>{
    int resource;

    public MyArrayAdapter(Context context, int _resource, List<Muzicar> items) {
        super(context, _resource, items);
        resource = _resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        RelativeLayout newView;
        if(convertView == null){
            newView = new RelativeLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li;
            li = ((LayoutInflater)getContext().getSystemService(inflater));
            li.inflate(resource,newView,true);
        }
        else{
            newView = (RelativeLayout)convertView;
        }

        Muzicar instancaMuzicara = getItem(position);

        TextView naziv = (TextView) newView.findViewById(R.id.NazivMuzicara);
        naziv.setText(instancaMuzicara.getIme());
        TextView zanr = (TextView) newView.findViewById(R.id.NazivZanra);
        zanr.setText(instancaMuzicara.getZanr());
        ImageView slika = (ImageView) newView.findViewById(R.id.icon1);
        slika.setImageResource(instancaMuzicara.getSlikaID());

        return newView;
    }
}

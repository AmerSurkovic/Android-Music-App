package ba.unsa.etf.rma.amer.rma15_16781;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by amer on 5/27/16.
 */
public class MuzicarCursorAdapter extends ResourceCursorAdapter {

    public MuzicarCursorAdapter(Context context, int layout, Cursor c, int flags) {
        super(context, layout, c, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView ime = (TextView) view.findViewById(R.id.NazivMuzicara);
        ime.setText(cursor.getString(cursor.getColumnIndex(MuzicarDBOpenHelper.MUZICAR_IME)));

        TextView zanr = (TextView) view.findViewById(R.id.NazivZanra);
        zanr.setText(cursor.getString(cursor.getColumnIndex(MuzicarDBOpenHelper.MUZICAR_ZANR)));

        ImageView slika = (ImageView) view.findViewById(R.id.icon1);
        slika.setImageResource(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MuzicarDBOpenHelper.MUZICAR_SLIKA_ID))));
    }
}

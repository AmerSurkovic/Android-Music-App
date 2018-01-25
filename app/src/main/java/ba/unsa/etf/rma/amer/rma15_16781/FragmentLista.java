package ba.unsa.etf.rma.amer.rma15_16781;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amer on 4/26/16.
 */
public class FragmentLista extends Fragment implements MojResultReceiver.Receiver, SearchArtist.OnMuzicarSearchDone {

    private ArrayList<Muzicar> muzicari;
    private OnItemClick oic;
    private EditText tex;
    public MyArrayAdapter aa;
    private ListView listaView;
    private MojResultReceiver mReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Hardcoding muzicare u tabelu
        ContentValues noveVrijednosti = new ContentValues();
        noveVrijednosti.put(MuzicarDBOpenHelper.MUZICAR_IME, "Halid Beslic");
        noveVrijednosti.put(MuzicarDBOpenHelper.MUZICAR_ZANR, "sevdah");
        noveVrijednosti.put(MuzicarDBOpenHelper.MUZICAR_SLIKA_ID, R.drawable.narodna);

        MuzicarDBOpenHelper temp = new MuzicarDBOpenHelper(this.getActivity(), "mojaBaza", null, 1);
        SQLiteDatabase db = temp.getWritableDatabase();
        db.insert(MuzicarDBOpenHelper.DATABASE_TABLE, null, noveVrijednosti);

        String[] koloneRezultat = new String[]{MuzicarDBOpenHelper.MUZICAR_ID, MuzicarDBOpenHelper.MUZICAR_IME, MuzicarDBOpenHelper.MUZICAR_ZANR};

        String where = MuzicarDBOpenHelper.MUZICAR_ZANR + "='sevdah'";

        String whereArgs[] = null;
        String groupBy = null;
        String having = null;
        String order = null;

        MuzicarDBOpenHelper temp2 = new MuzicarDBOpenHelper(this.getActivity(), "mojaBaza", null, 1);
        SQLiteDatabase db2 = temp2.getWritableDatabase();

        Cursor kursor = db2.query(MuzicarDBOpenHelper.DATABASE_TABLE,
                koloneRezultat, null,
                whereArgs, groupBy, having, order);

        MuzicarCursorAdapter adapter = new MuzicarCursorAdapter(this.getActivity(), R.layout.element_liste_2, kursor, 0);

        View rview = inflater.inflate(R.layout.lista_fragment, container, false);

        listaView = (ListView) rview.findViewById(R.id.listView);
        listaView.setAdapter(adapter);

        //   listaView = (ListView) rview.findViewById(R.id.listView);
        final Button pretragaDugme = (Button) rview.findViewById(R.id.button);
        tex = (EditText) rview.findViewById(R.id.editText);


        pretragaDugme.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                muzicari.clear();
                MyArrayAdapter adapter = (MyArrayAdapter) listaView.getAdapter();
                adapter.notifyDataSetChanged();

                final String upit = tex.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SYNC, null, getActivity(), MyIntentService.class);
                mReceiver = new MojResultReceiver(new android.os.Handler());
                mReceiver.setReceiver(FragmentLista.this);
                intent.putExtra("tekstPretraga", tex.getText().toString());
                intent.putExtra("receiver", mReceiver);
                getActivity().startService(intent);
            }
        });

        Intent shareText =getActivity().getIntent();
        if(shareText.getAction() == Intent.ACTION_SEND) {
            String text = shareText.getStringExtra(Intent.EXTRA_TEXT);
            EditText editText = (EditText)rview.findViewById(R.id.editText);
            editText.setText(text);
        }

        if(getArguments().containsKey("Alista"))
        {
            muzicari = getArguments().getParcelableArrayList("Alista");
            aa = new MyArrayAdapter(getActivity(),R.layout.element_liste_2,muzicari);
            listaView.setAdapter(aa);
        }
        try
        {
            oic = (OnItemClick)getActivity();
        }
        catch (ClassCastException e)
        {

            throw new ClassCastException(getActivity().toString() + "Nije implentiran OnItemClick");
        }
        listaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                oic.onItemClicked(position);
            }
        });

        return rview;
    }

    @Override
    public void onDone(Muzicar m) {
        muzicari.add(m);
        MyArrayAdapter adapter = (MyArrayAdapter) listaView.getAdapter();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case MyIntentService.STATUS_RUNNING:
                break;
            case MyIntentService.STATUS_FINISHED:

                ArrayList<Muzicar> m = resultData.getParcelableArrayList("result");
                muzicari.clear();

                for (Muzicar mm : m) {
                    muzicari.add(mm);
                }
                for(int z = 0; z < muzicari.size(); z++)
                    muzicari.get(z).setMuzicari(muzicari);

                Bundle argumenti=new Bundle();
                argumenti.putParcelableArrayList("Alista",muzicari);
                MyArrayAdapter adapter = (MyArrayAdapter) listaView.getAdapter();
                adapter.notifyDataSetChanged();
                break;
            case MyIntentService.STATUS_ERROR:
                String error = resultData.getString(Intent.EXTRA_TEXT);
                Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
                break;
        }
    }

    public interface OnItemClick {
        public void onItemClicked(int pos);
    }

}

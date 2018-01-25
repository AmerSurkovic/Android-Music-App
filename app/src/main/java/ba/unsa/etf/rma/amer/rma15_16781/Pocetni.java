package ba.unsa.etf.rma.amer.rma15_16781;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Pocetni extends AppCompatActivity implements FragmentLista.OnItemClick {
    private boolean siriL = false;
    static ArrayList<Muzicar> unosi = new ArrayList<Muzicar>();

    private IntentFilter filter = new IntentFilter("CONNECTIVITY_CHANGE");
    private MyBroadcastReciever receiver = new MyBroadcastReciever();
    @Override
    public void onResume(){
        super.onResume();
        // Registruj receiver
        registerReceiver(receiver, filter);
    }
    @Override
    public void onPause(){
        // Unregister receiver
        unregisterReceiver(receiver);
        super.onPause();
    }

   // MyArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetni);

   //     Button dugme = (Button)findViewById(R.id.button);
   //     final EditText tekst = (EditText)findViewById(R.id.editText);
   //     ListView lista = (ListView)findViewById(R.id.listView);

   /*
        ArrayList<String> t5p1 = new ArrayList<String>();
        t5p1.add("Hej zoro, ne svani");
        t5p1.add("Miljacka");
        t5p1.add("Kad zaigra srce od meraka");
        t5p1.add("Iznad tesnja");
        t5p1.add("Gitara i casa vina");
        Muzicar test1 = new Muzicar("Halid", "Bešlić", "Narodna", "https://hr.wikipedia.org/wiki/Halid_Be%C5%A1li%C4%87",
                "Halid Bešlić je rođen 20. novembra 1953. godine u zaseoku Vrapci, kod Knežine, općina Sokolac. Amaterski se počeo baviti muzikom, odnosno pjevanjem još kao učenik osnovne škole, kroz razna kulturno-umjetnička društva, a najduže vremena je proveo u KUD \"Zija Dizdarević\". To je potrajalo sve do odsluženja vojne obaveze u bivšoj Jugoslaviji. Nakon povratka iz vojske, svoju estradnu karijeru je aktivirao kroz nastupe u poznatim sarajevskim i drugim restoranima, što je potrajalo nekih 5-6 godina.\n" +
                        "\n" +
                        "Prvu ‘singl ploču’ je snimio 1979. godine od kada počinje njegov uzlazni put uspjeha i popularnosti na bosanskoj, odnosno balkanskoj estradnoj sceni. Rezultat Halidovog rada od 1979. godine do 2003. je 16 snimljenih albuma, kao i dva albuma uživo, koji su prodani u milionskim nakladama raznih nosača zvuka. Održao je na hiljade solističkih koncerata širom svijeta.\n" +
                        "\n" +
                        "Može se naglasiti, da je Halid Bešlić dao ogroman doprinos na humanitarnom planu za vrijeme agresije na Bosnu i Hercegovinu (1992-95). Održao je preko 500 humanitarnih koncerata širom zapadne Evrope.\n" +
                        "\n" +
                        "Dobitnik je brojnih nagrada i priznanja za muzička ostvarenja i dostignuća. Član je Udruženja muzičara BiH.",
                        t5p1);
        unosi.add(test1);

        ArrayList<String> t5p2 = new ArrayList<String>();
        t5p2.add("Livin' la Vida Loca");
        t5p2.add("Maria");
        t5p2.add("The Cup of Life");
        t5p2.add("Nobody Wants To Be Lonely");
        t5p2.add("Private Emotion");
        Muzicar test2 = new Muzicar("Ricky", "Martin", "Pop", "http://www.rickymartinmusic.com/",
                        "Enrique Martín Morales (24. prosinca 1971.), poznatiji po umjetničkom pseudonimu Ricky Martin je Grammyjem i Latinskim Grammyjem nagrađen portorikanski pop pjevač koji je slavu stekao kao pjevač Latino boy banda Menudo, a potom od 1991. godine kao solo-izvođač.\n" +
                        "\n" +
                        "Tijekom karijere koja traje više od tri desetljeća je prodao preko 55 milijuna albuma širom svijeta. Poznat je i kao osnivač besprofitne dobrotvorne organizacije Fundación Ricky Martin.",
                        t5p2);
        unosi.add(test2);

        ArrayList<String> t5p3 = new ArrayList<String>();
        t5p3.add("test1");
        t5p3.add("test2");
        t5p3.add("test3");
        t5p3.add("test4");
        t5p3.add("test5");
        Muzicar test3 = new Muzicar("test", "test", "test", "http://www.example.com", "biografija", t5p3);
        unosi.add(test3);

        ArrayList<String> t5p4 = new ArrayList<String>();
        t5p4.add("Whenever, Wherever");
        t5p4.add("Hips Don't Lie");
        t5p4.add("She wolf");
        t5p4.add("Waka Waka (This Time for Africa");
        t5p4.add("Can't Remember to Forget You");
        Muzicar test4 = new Muzicar("Shakira", "", "Pop", "http://www.shakira.com/",
                "Znana samo kao \"Shakira\" poznata je po svojim pjesmama, a neke od njih su: \"Estoy Aqui\", \"Ciega, Sordomuda\", \"Ojos Asi\", \"Whenever, Wherever\", \"Underneath Your Clothes\", \"La Tortura\", \"Hips Don't Lie\", \"Waka Waka (This Time for Africa)\", \"Loca\"\n"
                + "i po svom provokativnom trbušnom plesu i micanju bokovima. Trbušni ples naučila je od bake Libanonke. Govori portugalski, španjolski, engleski, francuski, talijanski i arapski jezik."
                ,
                t5p4);
        unosi.add(test4);

        unosi.get(0).setMuzicari(unosi);
        unosi.get(1).setMuzicari(unosi);
        unosi.get(2).setMuzicari(unosi);
        unosi.get(3).setMuzicari(unosi);

      /*  adapter = new MyArrayAdapter(this,R.layout.element_liste_2,unosi);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(Pocetni.this, PrikazMuzicara.class);

                myIntent.putExtra("imeAutora", unosi.get(position).getIme());
                myIntent.putExtra("prezimeAutora", unosi.get(position).getPrezime());
                myIntent.putExtra("zanrAutora", unosi.get(position).getZanr());
                myIntent.putExtra("webAutora", unosi.get(position).getWebStranica());
                myIntent.putExtra("biografijaAutora", unosi.get(position).getBiografija());
                myIntent.putExtra("top5pjesama", unosi.get(position).getTop5pjesama());

                Pocetni.this.startActivity(myIntent);
            }

        });*/

        Intent primiText = getIntent();
        if(primiText.getAction() == Intent.ACTION_SEND) {
            String text = primiText.getStringExtra(Intent.EXTRA_TEXT);

            EditText editText = (EditText)findViewById(R.id.editText);
            editText.setText(text);
        }

        siriL=false;
        FragmentManager fm = getFragmentManager();
        FrameLayout ldetalji = (FrameLayout)findViewById(R.id.detalji_muzicar);
        if(ldetalji!=null){
            siriL=true;
            FragmentDetalji fd;
            fd = (FragmentDetalji)fm.findFragmentById(R.id.detalji_muzicar);

            if(fd==null) {
                fd = new FragmentDetalji();
                fm.beginTransaction().replace(R.id.detalji_muzicar, fd).commit();
            }
        }

        FragmentLista fl = (FragmentLista)fm.findFragmentByTag("Lista");

        if(fl==null){
            fl = new FragmentLista();
            Bundle argumenti=new Bundle();
            argumenti.putParcelableArrayList("Alista",unosi);
            fl.setArguments(argumenti);

            fm.beginTransaction().replace(R.id.muzicari_lista, fl,"Lista").commit();
        }
        else{
            fm.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClicked(int pos) {

        Bundle arguments = new Bundle();
        arguments.putParcelable("muzicar", unosi.get(pos));
        FragmentDetalji fd = new FragmentDetalji();
        fd.setArguments(arguments);
        if (siriL) {

            getFragmentManager().beginTransaction().replace(R.id.detalji_muzicar, fd).commit();
        } else {

            getFragmentManager().beginTransaction().replace(R.id.muzicari_lista, fd).addToBackStack(null).commit();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList("muzicari", unosi);
    }
}

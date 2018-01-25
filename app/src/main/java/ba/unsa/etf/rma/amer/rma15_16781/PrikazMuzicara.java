package ba.unsa.etf.rma.amer.rma15_16781;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PrikazMuzicara extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_muzicara);

        final TextView puniNaziv = (TextView) findViewById(R.id.detalji_nazivMuzicara);
        puniNaziv.setText(getIntent().getStringExtra("imeAutora") + " " + getIntent().getStringExtra("prezimeAutora"));

        TextView puniZanr = (TextView) findViewById(R.id.detalji_zanrMuzicara);
        puniZanr.setText(getIntent().getStringExtra("zanrAutora"));

        TextView puniWeb = (TextView) findViewById(R.id.detalji_webMuzicara);
        puniWeb.setText(getIntent().getStringExtra("webAutora"));

        TextView puniBiografiju = (TextView) findViewById(R.id.detalji_biografija);
        puniBiografiju.setMovementMethod(new ScrollingMovementMethod());
        puniBiografiju.setText(getIntent().getStringExtra("biografijaAutora"));

        final ArrayList<String> pjesme = getIntent().getStringArrayListExtra("top5pjesama");

        TextView linkWeba = (TextView) findViewById(R.id.detalji_webMuzicara);
        String text = "<a href='" + getIntent().getStringExtra("webAutora") + "'>" + getIntent().getStringExtra("webAutora") + "</a>";
        linkWeba.setText(Html.fromHtml(text));


        linkWeba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getIntent().getStringExtra("webAutora");
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        Button shareBiografiju = (Button) findViewById(R.id.detalji_shareButton);
        shareBiografiju.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent posaljiText = new Intent(Intent.ACTION_SEND);
                posaljiText.setType("text/plain");
                posaljiText.putExtra(Intent.EXTRA_TEXT, getIntent().getStringExtra("biografijaAutora"));
                startActivity(Intent.createChooser(posaljiText, "Podijeli sa:"));
            }
        });

    }

}

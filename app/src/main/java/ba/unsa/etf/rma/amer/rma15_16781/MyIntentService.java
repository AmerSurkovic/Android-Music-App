package ba.unsa.etf.rma.amer.rma15_16781;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.renderscript.ScriptGroup;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by amer on 5/26/16.
 */
public class MyIntentService extends IntentService {
    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;
    Muzicar instancaMuzicara;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    public MyIntentService() {super(null);}

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        Bundle bundle = new Bundle();
        receiver.send(STATUS_RUNNING, Bundle.EMPTY);
        ArrayList<Muzicar> results = new ArrayList<>();
        String query = intent.getStringExtra("tekstPretraga");
        if(query.contains(" "))
        {
            query = query.replace(" ","+");
        }
        String url1 = "https://api.spotify.com/v1/search?q=" + query + "&type=artist";
        try {

            URL url = new URL(url1);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String rezultat = convertStreamToString(in);
            JSONObject jo = new JSONObject(rezultat);
            JSONObject artists = jo.getJSONObject("artists");
            JSONArray items = artists.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject artist = items.getJSONObject(i);
                String name = artist.getString("name");
                String artist_ID = artist.getString("id");
                JSONArray genres = (JSONArray)artist.get("genres");
                JSONObject temp = artist.getJSONObject("external_urls");
                String webpage = temp.getString("spotify");
                Log.d("NAME:  ", name);
                Log.d("ID:    ", artist_ID);
                Log.d("HTML:  ", webpage);
                String genre = new String();
                for(int j = 0; j < genres.length(); j++)
                {
                    if(j != genres.length() -1)
                        genre += genres.getString(j) + ", ";
                    else
                        genre += genres.getString(j);

                }

                int zanrSlika = R.drawable.p1;
                if(genre.contains("pop"))
                    zanrSlika = R.drawable.pop;
                else if(genre.contains("sevdah"))
                    zanrSlika = R.drawable.narodna;
                else if(genre.contains("rock"))
                    zanrSlika = R.drawable.rock;
                else if(genre.contains("metal"))
                    zanrSlika = R.drawable.metal;
                else if(genre.contains("jazz"))
                    zanrSlika = R.drawable.jazz;

                instancaMuzicara = new Muzicar(name, genre, webpage, zanrSlika);
                ArrayList<String> topPjesme = new ArrayList<>();

                // Top pjesme
                String spotifyID = artist.getString("id");
                String url2 = "https://api.spotify.com/v1/artists/" + spotifyID + "/top-tracks?country=US";
                URL urlTopPjesme = new URL(url2);
                HttpURLConnection urlConnectionTopPjesme = (HttpURLConnection) urlTopPjesme.openConnection();
                InputStream inTopPjesme = new BufferedInputStream(urlConnectionTopPjesme.getInputStream());
                String rezultatTopPjesme = convertStreamToString(inTopPjesme);
                JSONObject joTopPjesme = new JSONObject(rezultatTopPjesme);
                JSONArray tracks = joTopPjesme.getJSONArray("tracks");
                for(int k = 0; k < tracks.length() && k < 5; k++)
                {
                    JSONObject track = tracks.getJSONObject(k);
                    topPjesme.add(track.getString("name").toString());
                }
                instancaMuzicara.setPjesme(topPjesme);

                // Albumi
                String url3 = "https://api.spotify.com/v1/artists/" + spotifyID + "/albums";
                URL urlTopAlbumi = new URL(url3);
                HttpURLConnection urlConnectionTopAlbumi = (HttpURLConnection) urlTopAlbumi.openConnection();
                InputStream inTopAlbumi = new BufferedInputStream(urlConnectionTopAlbumi.getInputStream());
                String rezultatTopAlbumi = convertStreamToString(inTopAlbumi);
                JSONObject joTopAlbumi = new JSONObject(rezultatTopAlbumi);
                JSONArray items1 = joTopAlbumi.getJSONArray("items");
                ArrayList<String> albumi = new ArrayList<>();
                ArrayList<String> albumiURL = new ArrayList<>();
                for(int m = 0; m < items1.length(); m++)
                {
                    JSONObject objektAlbum = items1.getJSONObject(m);
                    String naziv_albuma = objektAlbum.getString("name");
                    albumi.add(naziv_albuma);
                    JSONObject obj = objektAlbum.getJSONObject("external_urls");
                    String webSpotify = obj.getString("spotify");
                    albumiURL.add(webSpotify);
                }

                instancaMuzicara.setAlbumi(albumi);
                instancaMuzicara.setAlbumi_url(albumiURL);
                results.add(instancaMuzicara);

                bundle.putParcelableArrayList("result", results);
                receiver.send(STATUS_FINISHED, bundle);

            }
        } catch (ClassCastException e) {
            bundle.putString(Intent.EXTRA_TEXT, e.toString());
            receiver.send(STATUS_ERROR, bundle);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String convertStreamToString(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
        } finally {
            try {
                in.close();
            } catch (IOException e) {
            }
        }
        return sb.toString();
    }
}

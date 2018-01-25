package ba.unsa.etf.rma.amer.rma15_16781;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by amer on 4/29/16.
 */
public class SearchArtist extends AsyncTask<String, Integer, Void> {

    public interface OnMuzicarSearchDone {
        public void onDone(Muzicar rez);
    }

    Muzicar instancaMuzicara;
    ArrayList<Muzicar> muzicari = new ArrayList<Muzicar>();
    private OnMuzicarSearchDone pozivatelj;
    public SearchArtist(OnMuzicarSearchDone p ){ pozivatelj=p; }

    @Override
    protected Void doInBackground(String... params) {
        String query = null;
        try {
            query = URLEncoder.encode(params[0], "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
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
                String genre = artist.getString("genres");
                String webpage = artist.getString("external_urls");
                Log.d("NAME:  ", name);
                Log.d("ID:    ", artist_ID);
                Log.d("ZANR:  ", genre);
                if(genre.contains("[") == true || genre.contains("]") == true)
                    genre = " ";
                instancaMuzicara = new Muzicar(name, genre, webpage, 1);
                muzicari.add(instancaMuzicara);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);
        for(Muzicar m : muzicari)
            pozivatelj.onDone(m);

    }

    public String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
        return sb.toString();
    }

}

    

package ba.unsa.etf.rma.amer.rma15_16781;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amer on 3/14/16.
 */
public class Muzicar implements Parcelable {
    public String ime, zanr, webStranica;
    public ArrayList<Muzicar> muzicari = new ArrayList<Muzicar>();
    public ArrayList<String> albumi = new ArrayList<String>();
    public ArrayList<String> albumi_url = new ArrayList<String>();
    public ArrayList<String> pjesme = new ArrayList<String>();
    public int slikaID;

    public ArrayList<String> getPjesme() {
        return pjesme;
    }

    public void setPjesme(ArrayList<String> pjesme) {
        this.pjesme = pjesme;
    }

    public int getSlikaID() {
        return slikaID;
    }

    public void setSlikaID(int slikaID) {
        this.slikaID = slikaID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public String getWebStranica() {
        return webStranica;
    }

    public void setWebStranica(String webStranica) {
        this.webStranica = webStranica;
    }

    public void setMuzicari(List<Muzicar> lista){ muzicari = new ArrayList<>(lista); }

    public ArrayList<Muzicar> getMuzicari() {
        return muzicari;
    }

    public void setMuzicari(ArrayList<Muzicar> muzicari) {
        this.muzicari = muzicari;
    }

    public ArrayList<String> getAlbumi() {
        return albumi;
    }

    public void setAlbumi(ArrayList<String> albumi) {
        this.albumi = albumi;
    }

    public ArrayList<String> getAlbumi_url() {
        return albumi_url;
    }

    public void setAlbumi_url(ArrayList<String> albumi_url) {
        this.albumi_url = albumi_url;
    }

    public Muzicar(){ }

    public Muzicar(String ime, String zanr, String webStranica, int slikaID) {
        this.ime = ime;
        this.zanr = zanr;
        this.webStranica = webStranica;
        this.slikaID = slikaID;
        pjesme = null;
        muzicari = null;
        albumi = null;
    }

    protected Muzicar(Parcel in){
        ime = in.readString();
        zanr = in.readString();
        webStranica = in.readString();
        slikaID = in.readInt();
        pjesme = in.createStringArrayList();
        albumi = in.createStringArrayList();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ime);
        dest.writeString(zanr);
        dest.writeString(webStranica);
        dest.writeStringList(pjesme);
        dest.writeStringList(albumi);
    }

    public static final Creator<Muzicar> CREATOR = new Creator<Muzicar>() {
        @Override
        public Muzicar createFromParcel(Parcel in) {
            return new Muzicar(in);
        }

        @Override
        public Muzicar[] newArray(int size) {
            return new Muzicar[size];
        }
    };

    public List<Muzicar> getSlicniMuzicari() {
        List<Muzicar> slicniMuzicari = new ArrayList<Muzicar>();
        for (Muzicar m : muzicari) {
            if(m.getZanr().equals(this.zanr)){
                slicniMuzicari.add(m);
            }
        }
        return slicniMuzicari;
    }
}

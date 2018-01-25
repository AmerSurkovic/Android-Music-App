package ba.unsa.etf.rma.amer.rma15_16781;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by amer on 5/27/16.
 */
public class MuzicarDBOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mojaBaza.db";
    public static final String DATABASE_TABLE = "Muzicari";
    public static final int DATABASE_VERSION = 1;
    public static final String MUZICAR_ID = "_id";
    public static final String MUZICAR_IME = "ime";
    public static final String MUZICAR_ZANR = "zanr";
    public static final String MUZICAR_SLIKA_ID = "slika";

    // Upit kreiranja SQLite baze podataka
    private static final String DATABASE_CREATE = "create table " +
            DATABASE_TABLE + " (" + MUZICAR_ID +
            " integer primary key autoincrement, " +
            MUZICAR_IME + " text not null, " +
            MUZICAR_ZANR +  " text not null, " +
            MUZICAR_SLIKA_ID + " text not null);";

    public MuzicarDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Poziva se kada ne postoji baza
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    // Poziva se kada se ne poklapaju verzije baze na disku i trenutne baze
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Brisanje stare baze
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);

        // Kreiranje nove
        onCreate(db);
    }
}

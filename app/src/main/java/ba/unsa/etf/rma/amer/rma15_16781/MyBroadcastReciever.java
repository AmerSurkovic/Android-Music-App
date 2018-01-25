package ba.unsa.etf.rma.amer.rma15_16781;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by amer on 3/21/16.
 */

public class MyBroadcastReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(isOnline(context)==true){
            Toast.makeText(context, "Aplikacija je konektovana na internet.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Aplikacija nije konektovana na internet.", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}

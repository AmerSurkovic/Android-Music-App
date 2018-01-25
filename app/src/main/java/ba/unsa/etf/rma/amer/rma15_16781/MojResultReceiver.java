package ba.unsa.etf.rma.amer.rma15_16781;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import java.util.ArrayList;

/**
 * Created by amer on 5/26/16.
 */
public class MojResultReceiver extends ResultReceiver {
    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    private Receiver mReceiver;
    public MojResultReceiver(Handler handler) {
        super(handler);
    }
    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }
    public interface Receiver {
        public void onReceiveResult(int resultCode, Bundle resultData);
    }
    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }
}

package ss12.com.lightsout;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;
import android.widget.Toast;

/**
 * Created by Alex on 2/25/2015.
 */
public class VibrateService  extends Service
{
    // Based on location and amount of vibration
    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);
        Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        // pass the number of millseconds fro which you want to vibrate the phone here we
        // have passed 2000 so phone will vibrate for 2 seconds.
        //long pattern[]={0,800,200,1200,300,2000,400,4000};
        v.vibrate(2000);

        // Tests if this method can be called
        Toast.makeText(getApplicationContext(), "Phone is Vibrating", Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

}
package ss12.com.wearablegame;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

public class SensorActivity extends Activity implements SensorEventListener {

    private TextView mTextView;
    private SensorManager sensorManager;
    private Sensor accel;
    private float[] dataArray = new float[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,accel,sensorManager.SENSOR_DELAY_NORMAL);
        setContentView(R.layout.activity_sensor);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            dataArray[0] = event.values[0];
            dataArray[1] = event.values[1];
            dataArray[2] = event.values[2];
        }
        TextView tv = (TextView) this.findViewById(R.id.text);
        tv.setText("x: "+ dataArray[0]+"\ny:"+dataArray[1]+"\nz: "+dataArray[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

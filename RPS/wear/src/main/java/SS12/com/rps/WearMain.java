package ss12.com.rps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WearMain extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.round_activity_wear_main);
        mTextView = (TextView) this.findViewById(R.id.text);
        Intent intent = new Intent(WearMain.this, SensorData.class);
        startActivity(intent);
    }
}
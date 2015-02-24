package ss12.com.lightsout;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MobileMain extends ActionBarActivity implements TextToSpeech.OnInitListener
{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_main);
       // textToSpeechObj();
        textToSpeechLogic.textToSpeechObj(getApplicationContext());
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mobile_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // I am using the activity main, to test if it workings with an event triggering it.
    public void start(View view) {
        Button btnVibrate=(Button)findViewById(R.id.startButton);
        btnVibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVibrate =new Intent(getApplicationContext(),VibrateService.class);
                startService(intentVibrate);
                textToSpeechLogic.sayWords(MobileMain.this, "Start Fight Mother Fucker");
                SoundEng.playSound(getApplicationContext(), "fail");
               // setContentView(R.layout.activity_in_game);
                TextView sayWhat = (TextView) findViewById(R.id.textView1);
                sayWhat.setText("GETTER DONE");
            }
        });
                //  setContentView(R.layout.activity_in_game);

       // soundEng.playSound(getApplicationContext(), "start");
     //   soundEng.playSound(getApplicationContext(), soundEng.getMediaLocation("fail"));
       // SoundEng.playSound(getApplicationContext(), "fail");

    }
    @Override
    public void onInit(int status) {

    }
}


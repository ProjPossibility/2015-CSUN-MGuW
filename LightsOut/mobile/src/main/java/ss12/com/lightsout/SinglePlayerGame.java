package ss12.com.lightsout;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

//this class is the single player game activity and controls all
// single player game logic on the ui thread
public class SinglePlayerGame extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);

    }

    //determine next expected move for the user
    private void setAction(){
        //implement rolling (random?) number for next action
    }

    //determine and set timer length for next action
    private void setTimer(){
        //implement algorithm for timer length, decreasing on successive wins
        //implement timer mechanism for starting (probably pass timer length to service)
    }

    //launch listener to get success or fail of wearable event
    private void launchListenerService(){
        //launch the service for "background" game logic
        //ideally, pass only int for timer length and int for expected action from user
        //ideally, return only success or fail (boolean?)
    }

    //update ui elements
    private void updateUI(){
        //general updating of UI should occur here
        //probably will want to pass in what and how to update
        //OR
        //looping image or gif while waiting for the service to return with success or fail
    }

    //auditory feedback on success or fail
    private void playSound(){
        //general playing of sounds should occur here
        //probably will want to pass in sound name or path
    }

    //haptic feedback on success or fail
    private void vibrate(){
        //general vibration should occur here
        //probably will want to pass in an int describing preset vibration patterns
    }

    //method called when service returns success
    private void onSuccessAction(){
        //add as callback method for listener service?
        //increase score count and enter back into loop for gameplay
    }

    //method called when service returns fail
    private void onFailAction(){
        //add as callback method for listener service?
        //increase strike count and enter back into loop IFF game should not end
        //possibly end game here
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_in_game, menu);
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
}

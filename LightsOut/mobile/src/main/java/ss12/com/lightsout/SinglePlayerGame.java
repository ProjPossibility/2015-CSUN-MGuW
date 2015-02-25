package ss12.com.lightsout;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

//this class is the single player game activity and controls all
// single player game logic on the ui thread
public class SinglePlayerGame extends ActionBarActivity implements MessageApi.MessageListener {
    private GoogleApiClient mGoogleApiClient;
    private final String TAG = "Single Player Game"; //tag for logging
    private String nodeId = ""; //node id to get from thread in retrieveDeviceNode()
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        createGoogleApiClient();
    }

    @Override
    protected void onStart() {

        super.onStart();
        TextView tv = (TextView) findViewById(R.id.text);
        Button button = (Button) findViewById(R.id.button);

        mGoogleApiClient.connect();
        //assigns nodeId
        retrieveDeviceNode();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushToWearable(nodeId,"test");
                Log.d(TAG, "push " + nodeId + "!");
            }
        });
        //pushToWearable("1");
    }

    private void createGoogleApiClient(){
        //Basic Google Api Client build
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle connectionHint) {
                        Log.d(TAG, "onConnected: " + connectionHint);
                        // now we can use the Data Layer API
                    }
                    @Override
                    public void onConnectionSuspended(int cause) {
                        Log.d(TAG, "onConnectionSuspended: " + cause);
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult result) {
                        Log.d(TAG, "onConnectionFailed: " + result);
                    }
                })
                        // adding only the wearable API
                .addApi(Wearable.API)
                .build();

    }

    //determine next expected move for the user
    private String setAction(){
        //implement rolling (random?) number for next action
        return "1";
    }

    //determine and set timer length for next action
    private void setTimer(){
        //implement algorithm for timer length, decreasing on successive wins
        //implement timer mechanism for starting (probably pass timer length to service)
    }

    //launch listener to get success or fail of wearable event
    private void pushToWearable(String nodeId,String action){
        //launch the service for "background" game logic
        //ideally, pass only int for timer length and int for expected action from user
        //ideally, return only success or fail (boolean?)
        Wearable.MessageApi.sendMessage(mGoogleApiClient,nodeId,action,null);
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

    private void retrieveDeviceNode() {
        //we are using a worker thread to get the nodeId since we do not want to
        // clog up the main thread
        Log.d(TAG,"nodes retrieved");

        new Thread(new Runnable() {
            @Override
            public void run() {

                NodeApi.GetConnectedNodesResult nodesResult =
                        Wearable.NodeApi.getConnectedNodes(mGoogleApiClient).await();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView text = (TextView) findViewById(R.id.text);
                        text.setText("hello thread");
                    }
                });
                final List<Node> nodes = nodesResult.getNodes();
                //we are assuming here that there is only one wearable attached to the phone
                //currently Android only supports having one wearable connected at a time
                if (nodes.size() > 0) {
                    nodeId=nodes.get(0).getId();
                    Log.d(TAG,nodeId);

                }

                Log.d(TAG,nodes.size()+"");
            }
        }).start();
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

    //this gets called when the wearable sends the mobile a message
    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

    }
}

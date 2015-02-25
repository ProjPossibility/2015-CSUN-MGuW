package ss12.com.lightsout;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import org.w3c.dom.Text;

import java.util.List;

public class SinglePlayerGame extends Activity {

    private GoogleApiClient mGoogleApiClient;
    private final String TAG = "Single Player Game"; //tag for logging
    private TextView mTextView;
    private String nodeId = "";//node for mobile device
    private MessageApi.MessageListener messageListener;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player_game);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });

createMessageListener();
createGoogleApiClient();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
        tv = (TextView) findViewById(R.id.text);
        //assigns nodeId
        retrieveDeviceNode();
    }

    private void createMessageListener(){
        messageListener = new MessageApi.MessageListener(){
            //this method gets called when the mobile sends the wearable a message
            @Override
            public void onMessageReceived(MessageEvent messageEvent) {
                final String message = messageEvent.getPath();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Wearable", "message received");
                        tv.setText(message);
                    }
                });
            }
        };
    }

    private void createGoogleApiClient(){
        //Basic Google Api Client build
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle connectionHint) {
                        Log.d(TAG, "onConnected: " + connectionHint);
                        Wearable.MessageApi.addListener(mGoogleApiClient,messageListener);
                        // now we can use the Message API
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

    private void retrieveDeviceNode() {
        //we are using a worker thread to get the nodeId since we do not want to
        // clog up the main thread
        Log.d(TAG,"nodes retrieved");

        new Thread(new Runnable() {
            @Override
            public void run() {

                NodeApi.GetConnectedNodesResult nodesResult =
                        Wearable.NodeApi.getConnectedNodes(mGoogleApiClient).await();
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


}

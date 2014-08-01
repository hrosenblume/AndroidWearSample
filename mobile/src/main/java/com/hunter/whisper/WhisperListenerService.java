package com.hunter.whisper;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

public class WhisperListenerService extends WearableListenerService implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
    private static final String TAG = "UPTO-REPORT-MOBILE";
    protected static final String START_ACTIVITY_PATH = "/outbox/whisper";
    private GoogleApiClient mGoogleApiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        String data = new String(messageEvent.getData());
        if (messageEvent.getPath().equals(START_ACTIVITY_PATH)) {
            toastMessage(data);
        }
    }

    public void toastMessage(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Log.e(TAG, "onConnected");
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.e(TAG, "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.e(TAG, "onConnectionFailed");
    }
}

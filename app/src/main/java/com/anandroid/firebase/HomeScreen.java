package com.anandroid.firebase;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

public class HomeScreen extends AppCompatActivity {
    private Button btnAnalytics, btnRealtimeDatabase;
    private String TAG = getClass().getSimpleName();
    private Activity act;
    private final int MININUM_SESSION = 500;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        initView();
        clickListenner();


    }

    private void initView() {
        act = HomeScreen.this;
        // TODO: INITIALISE
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(act);
        btnRealtimeDatabase = (Button) findViewById(R.id.btnRealtimeDatabase);
        btnAnalytics = (Button) findViewById(R.id.btnAnalytics);
        // TODO: SESSION MINIMUM TRACT TIME
        mFirebaseAnalytics.setMinimumSessionDuration(MININUM_SESSION);
    }

    private void clickListenner() {

        btnRealtimeDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle params = new Bundle();
                params.putInt("dataClicked", R.id.btnRealtimeDatabase);
                mFirebaseAnalytics.logEvent("DATABASE", params);

                startActivity(new Intent(act, MainActivity.class));
                //  Toast.makeText(act, "", Toast.LENGTH_SHORT).show();
            }
        });

        btnAnalytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle params = new Bundle();
                params.putInt("analyticsClicked", R.id.btnRealtimeDatabase);
                mFirebaseAnalytics.logEvent("ANALYTICS", params);

             /*   Bundle params = new Bundle();
                params.putString(FirebaseAnalytics.Param.ITEM_ID, "ANALYTICS");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, params);
*/
                startActivity(new Intent(act, Analytics.class));

            }
        });

    }


}

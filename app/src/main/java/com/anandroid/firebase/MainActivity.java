package com.anandroid.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference conditionRef = rootRef.child("condition");
    private TextView txtDisplay;
    private Button btnSunny, btnCold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        clickListenner();


    }

    private void clickListenner() {

        conditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String text = dataSnapshot.getValue(String.class);
                txtDisplay.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnSunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conditionRef.setValue("SUNNY");
            }
        });

        btnCold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conditionRef.setValue("COLD");

            }
        });
    }

    private void initView() {
        txtDisplay = (TextView) findViewById(R.id.txtDisplay);
        btnCold = (Button) findViewById(R.id.btnCold);
        btnSunny = (Button) findViewById(R.id.btnSunny);

    }
}

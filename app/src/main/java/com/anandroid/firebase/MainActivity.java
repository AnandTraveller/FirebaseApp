package com.anandroid.firebase;

import android.content.Intent;
import android.net.Uri;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference conditionRef = rootRef.child("condition");
    private TextView txtDisplay;
    public static final int GALLERY_IN = 12;
    private StorageReference storeRef = FirebaseStorage.getInstance().getReference();
    private Button btnSunny, btnCold, btnPic;

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
        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_IN);

            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            StorageReference pathFile = storeRef.child("Photos").child(selectedImageUri.getLastPathSegment());
            pathFile.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(MainActivity.this, "DONE", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void initView() {
        txtDisplay = (TextView) findViewById(R.id.txtDisplay);
        btnCold = (Button) findViewById(R.id.btnCold);
        btnPic = (Button) findViewById(R.id.btnPic);
        btnSunny = (Button) findViewById(R.id.btnSunny);

    }
}

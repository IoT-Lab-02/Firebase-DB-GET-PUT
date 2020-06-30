// https://console.firebase.google.com/u/0/project/fir-android-db-get-put/database/fir-android-db-get-put/data

package com.example.firebase_android_db_get_put;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance ();
    DatabaseReference myRef = db.getReference ("logDHT");
//    DatabaseReference myRef = db.getReference ();

    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        setTitle ("addChildEvent");

        textView = (TextView) findViewById (R.id.textView);
        editText = (EditText)findViewById(R.id.inputText);
    }

    public void put_btn_Click(View view)
    {
        String inData = editText.getText().toString ();

        // Firebase에 key에 대한 value 저장
        myRef.setValue(inData);
        editText.setText ("");

    }


    public  void get_btn_Click(View view) {
        // *********************** addValueEventListener *********************************
        myRef.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue (String.class);
                textView.setText (value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

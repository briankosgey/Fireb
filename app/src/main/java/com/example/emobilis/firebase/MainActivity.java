package com.example.emobilis.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Ref;

public class MainActivity extends AppCompatActivity {
    EditText mName,mEmail,mAge;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mName=findViewById(R.id.inputName);
        mEmail=findViewById(R.id.inputMail);
        mAge=findViewById(R.id.inputAge);
        dialog =new ProgressDialog(this);

        dialog.setMessage("Saving...");
    }
//saving code here
    public void save(View view) {
        String name = mName.getText().toString();
        String mail = mEmail.getText().toString();
        String age = mAge.getText().toString();
        long time = System.currentTimeMillis();
        String times = String.valueOf(time);

       DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
        .child("Users/" + time);
        User members = new User(name, mail, age,times); ref.setValue(members).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
            public void onComplete(@NonNull Task<Void> Task) {
        if (Task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

    } else {
                    Toast.makeText(MainActivity.this, "Failed",
                            Toast.LENGTH_SHORT).show();
                }


}

      });
   }
//fetch code here
    public void fetch(View view) {
        Intent fetch = new Intent(getApplicationContext(),UsersActivity.class);
        startActivity(fetch);
    }
}

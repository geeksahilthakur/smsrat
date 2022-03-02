package com.iamsahil.smsrat;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOsms
{
    private DatabaseReference databaseReference;
    public DAOsms(){

        FirebaseDatabase db =FirebaseDatabase.getInstance();
        databaseReference = db.getReference(sms.class.getSimpleName());
    }
    public Task<Void> add(sms sms){

      return   databaseReference.push().setValue(sms);
    }
}

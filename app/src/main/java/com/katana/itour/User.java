package com.katana.itour;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by wdavid on 12/13/2016.
 */
@IgnoreExtraProperties

public class User {

    public String username;
    public String email;
    private DatabaseReference mDatabase;



    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void writeNewUser(String userId, String name, String email) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        User user = new User(name, email);

        mDatabase.child("User").child(userId).setValue(user);
    }



}

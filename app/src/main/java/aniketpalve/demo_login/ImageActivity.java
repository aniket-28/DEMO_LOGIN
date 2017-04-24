package aniketpalve.demo_login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

public class ImageActivity extends AppCompatActivity {

    private StorageReference mStorageRef;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Intent service=new Intent(this,MyService.class);
        startService(service);

        mImage = (ImageView)findViewById(R.id.display_image);

        //connecting to the firebase Database
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mDatabaseReference = mFirebaseDatabase.getReference();


        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String post_image = dataSnapshot.child("image").getValue().toString();
                Glide.with(getApplicationContext())
                        .load(post_image)
                        .into(mImage);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}

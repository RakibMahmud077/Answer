package com.example.answer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import static android.os.Build.VERSION_CODES.P;

public class creating_profile extends AppCompatActivity {
    private EditText nameEditText,ageEditText,institutionEditText,hobbyEditText,idEditText;
    private ImageView profile;
    private ProgressBar progressBarInCreatingProfile;
    private Button buttonforSaveDataInCreatingProfile;
    private Uri imageUri;

    UploadTask uploadTask;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();  //kaj ki egular
    DatabaseReference databaseReference;
   FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;

    allmembers user_memebers;
    String currentUserId;

    private static final int choseImage=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_profile);

        user_memebers=new allmembers();

        nameEditText=(EditText)findViewById(R.id.name_editTextId);
        ageEditText=(EditText)findViewById(R.id.age_editTextId);
        institutionEditText=(EditText)findViewById(R.id.institution_editTextId);
        hobbyEditText=(EditText)findViewById(R.id.hobby_editTextId);
        idEditText=(EditText)findViewById(R.id.unique_editTextId);
        profile=(ImageView)findViewById(R.id.profileImageId);
        progressBarInCreatingProfile=(ProgressBar)findViewById(R.id.progressBarIdInCreatingProfile);
        buttonforSaveDataInCreatingProfile=(Button)findViewById(R.id.save_data);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        currentUserId=user.getUid();

        documentReference=db.collection("user").document(currentUserId);
        storageReference= FirebaseStorage.getInstance().getReference("Profile images");
        databaseReference=firebaseDatabase.getReference("All users");


        buttonforSaveDataInCreatingProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent intent=new Intent();
                //intent.setType("image/*");
                //  intent.setAction(Intent.ACTION_GET_CONTENT);
                // startActivityForResult(intent,choseImage);
                Intent openGallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGallery,choseImage);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if(requestCode==choseImage || resultCode==RESULT_OK  || data!=null || data.getData() !=null)
            {
                imageUri=data.getData();
                Picasso.with(creating_profile.this).load(imageUri).into(profile);
            }

        }
        catch (Exception e)
        {
            Toast.makeText(creating_profile.this,"Error"+e,Toast.LENGTH_SHORT).show();
        }


    }
    private String getFileExtension(Uri uri)     //Etar kahini vhalo kore bujte hobe
    {
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadData() {
        String name = nameEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String institution = institutionEditText.getText().toString();
        String id = idEditText.getText().toString();
        String hobby = hobbyEditText.getText().toString();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age) && !TextUtils.isEmpty(institution) && !TextUtils.isEmpty(id) && !TextUtils.isEmpty(hobby)) {
            progressBarInCreatingProfile.setVisibility(View.VISIBLE);
            final StorageReference reference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
            uploadTask = reference.putFile(imageUri);
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return reference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        Map<String, String> profile1 = new HashMap<>();
                        profile1.put("name", name);
                        profile1.put("age", age);
                        profile1.put("institution", institution);
                        profile1.put("id", id);
                        profile1.put("hobby", hobby);
                        profile1.put("uri", downloadUri.toString());
                        profile1.put("privacy", "public");   //This child is for privacy
                        user_memebers.setName(name);
                        user_memebers.setAge(age);
                        user_memebers.setInstitution(institution);
                        user_memebers.setId(id);
                        user_memebers.setHobby(hobby);
                        user_memebers.setUri(downloadUri.toString());


                        databaseReference.child(currentUserId).setValue(user_memebers);
                                documentReference.set(profile1)

                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        progressBarInCreatingProfile.setVisibility(View.INVISIBLE);
                                        Toast.makeText(creating_profile.this, "profile created", Toast.LENGTH_SHORT).show();

                                        Intent intent=new Intent(creating_profile.this,subject_activity.class);
                                        startActivity(intent);
                                    }
                                });
                        //documentReference.set(profile1);
                    }
                }
            });


        }
        else
        {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

}
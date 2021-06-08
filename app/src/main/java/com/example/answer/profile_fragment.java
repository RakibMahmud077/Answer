package com.example.answer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class profile_fragment extends Fragment implements View.OnClickListener{
    ImageView imageView;
    TextView name,age,institution,id,hobby;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.profile_fragment,container,false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView=getActivity().findViewById(R.id.myprofileImageId);
        name=getActivity().findViewById(R.id.myprofileNameId);
        age=getActivity().findViewById(R.id.myprofileAgeId);
        institution=getActivity().findViewById(R.id.myprofileInstitutionId);
        id=getActivity().findViewById(R.id.myprofileId);
        hobby=getActivity().findViewById(R.id.myprofileHobbyId);

        TextView textView=getActivity().findViewById(R.id.profilefragmentId);
        textView.setOnClickListener(this);

    }

   @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.profilefragmentId:
            {
                Intent intent=new Intent(getActivity(),creating_profile.class);
                startActivity(intent);
            }

        }
    }
/*
   @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String currentid=user.getUid();
        DocumentReference reference;
        FirebaseFirestore firestore=FirebaseFirestore.getInstance();
        reference=firestore.collection("user").document(currentid);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult().exists())
                        {
                            String name1=task.getResult().getString("name");
                            String age1=task.getResult().getString("age");
                            String institution1=task.getResult().getString("institution");
                            String id1=task.getResult().getString("id");
                            String  hobby1=task.getResult().getString("hobby");
                            String url1=task.getResult().getString("uri");
                            Picasso.with(imageView.getContext()).load(url1).into(imageView);
                            name.setText(name1);
                            age.setText(age1);
                            institution.setText(institution1);
                            id.setText(id1);
                            hobby.setText(hobby1);

                        }
                        else
                        {
                            Intent intent=new Intent(getActivity(),creating_profile.class);
                            startActivity(intent);
                        }
                    }
                });
    }*/
}

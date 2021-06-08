package com.example.answer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class logout_fragment extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.logout_fragment,container,false);
        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseAuth auth;
        auth=FirebaseAuth.getInstance();
        TextView textView=getActivity().findViewById(R.id.logOutFragmentId);
        textView.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.logOutFragmentId:
            {
                FirebaseAuth.getInstance().signOut();
                //finish();
                Toast.makeText(getActivity(),"You are log out",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }

        }
    }
}

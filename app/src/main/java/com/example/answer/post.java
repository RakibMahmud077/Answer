package com.example.answer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class post extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Bundle bundle=getIntent().getExtras();
        String position=bundle.getString("type");
        Log.d("type",position);
        auth=FirebaseAuth.getInstance();


        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationViewId);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNav);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutId,new profile_fragment()).commit();



        if(position.equals("0"))
        {
            Toast.makeText(post.this,"Bangla is selected ",Toast.LENGTH_SHORT).show();
            loadBangla();
        }
        else if(position.equals("1"))
        {
            Toast.makeText(post.this,"English is selected ",Toast.LENGTH_SHORT).show();
            loadEnglish();
        }
        else if(position.equals("2"))
        {
            Toast.makeText(post.this,"Math is selected ",Toast.LENGTH_SHORT).show();
            loadMath();
        }
        else if(position.equals("3"))
        {
            Toast.makeText(post.this,"Physics is selected ",Toast.LENGTH_SHORT).show();
            loadPhysics();
        }
        else if(position.equals("4"))
        {
            Toast.makeText(post.this,"Chemistry is selected ",Toast.LENGTH_SHORT).show();
            loadChemistry();
        }
        else
        {
            Toast.makeText(post.this,"Biology is selected ",Toast.LENGTH_SHORT).show();
            loadBiology();
        }
    }

    private void loadBiology() {

    }

    private void loadChemistry() {
    }

    private void loadPhysics() {
    }

    private void loadMath() {
    }

    private void loadEnglish() {
    }

    private void loadBangla() {
    }



    private BottomNavigationView.OnNavigationItemSelectedListener onNav=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selected=null;
            switch (item.getItemId())
            {
                case R.id.profile_bottom:
                {
                    selected=new profile_fragment();
                    break;
                }
                case R.id.question_bottom:
                {
                    selected=new question_fragment();
                    break;
                }
                case R.id.LogOut_bottom:
                {
                    selected=new logout_fragment();
                    /*FirebaseAuth.getInstance().signOut();
                    finish();
                    Toast.makeText(getApplicationContext(),"You are log out",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);*/
                    break;
                }
                case R.id.Home_bottom:
                {
                    selected=new home_fragment();
                    break;
                }



            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutId,selected).commit();
            return true;
        }
    };
}
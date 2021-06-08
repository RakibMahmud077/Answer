package com.example.answer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class subject_activity extends AppCompatActivity {
    int[] images={R.drawable.bangla,R.drawable.english,R.drawable.mathematics,R.drawable.physic1,R.drawable.chemistry,R.drawable.biology2};
    String[] title,description;
    MyAdapter myAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_activity);
        title=getResources().getStringArray(R.array.subjects);
        description=getResources().getStringArray(R.array.subjects_description) ;

        myAdapter=new MyAdapter(this,title,description,images);

        recyclerView=(RecyclerView)findViewById(R.id.RecyclerView1);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        myAdapter.setOnItemClickListener(new MyAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent=new Intent(subject_activity.this,post.class);
                intent.putExtra("type",position+"");
                startActivity(intent);
                //Toast.makeText(getApplicationContext(),"On item click "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
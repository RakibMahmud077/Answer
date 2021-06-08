package com.example.answer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    Context context;
    String[] title,description;
    int[] images;
    private static ClickListener clicklistener;

    public MyAdapter(Context context, String[] title, String[] description, int[] images) {
        this.context = context;
        this.title = title;
        this.description = description;
        this.images = images;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.sample_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleTextView.setText(title[position]);
        holder.DescriptionTextView.setText(description[position]);
        holder.flagImageView.setImageResource(images[position]);

    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titleTextView,DescriptionTextView;
        ImageView flagImageView;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            titleTextView=itemView.findViewById(R.id.textViewId1);
            DescriptionTextView=itemView.findViewById(R.id.textViewId2);
            flagImageView=itemView.findViewById(R.id.ImageViewId);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clicklistener.onItemClick(getAbsoluteAdapterPosition(),v);
        }
    }
    public interface ClickListener
    {
        void onItemClick(int position,View v);
    }
    public void setOnItemClickListener(ClickListener clickListener1 )
    {
        MyAdapter.clicklistener=clickListener1;
    }
}

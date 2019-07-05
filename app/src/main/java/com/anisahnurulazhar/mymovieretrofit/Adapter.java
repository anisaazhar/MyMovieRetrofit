package com.anisahnurulazhar.mymovieretrofit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anisahnurulazhar.mymovieretrofit.model.Movies;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

//jadi Adapter itu selain penghubung juga sebagai penampung.

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private Context context;
    private List<Movies>listMovies;

    public Adapter (Context context, List<Movies> listMovies) {
        this.context = context;
        this.listMovies = listMovies;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int view) {
        //set layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_film, null,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.WRAP_CONTENT);

        v.setLayoutParams(layoutParams);
        return new ViewHolder(v);// pake view holder karna kita inisialisasi nya di view holder
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        final Movies movies = listMovies.get(position);
        Glide.with(context).load("http://image.tmdb.org/t/p/w185/"+movies
                .getPosterPath())
                .apply(new RequestOptions()
                .placeholder(R.mipmap.ic_launcher))
                .into(holder.img);
        holder.jdl.setText(movies.getTitle());//set tulisan dari movie db
        holder.tgl.setText(movies.getReleaseDate());
        holder.desc.setText(movies.getOverview());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("title", movies.getTitle());
                i.putExtra("poster_path", movies.getReleaseDate());
                i.putExtra("overview", movies.getOverview());
                i.putExtra("release_date", movies.getReleaseDate());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //add flags = nambah tugas baru
                //agar segera di execute, biar data nya cepet keluar.
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        //deklarasi view
        private CardView cv;
        private ImageView img;
        private TextView jdl, tgl, desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //inisialisasi view
            cv = itemView.findViewById(R.id.cv);
            img = itemView.findViewById(R.id.imgPoster);
            jdl = itemView.findViewById(R.id.tv_name);
            tgl = itemView.findViewById(R.id.tv_date);
            desc = itemView.findViewById(R.id.tv_desc);


        }
    }
}


package com.anisahnurulazhar.mymovieretrofit;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailActivity extends AppCompatActivity {
    String txtPoster, txtJudul, txtDate, txtDesc;
    TextView tv_Judul, tv_Tgl, tv_Desc;
    ImageView imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgPoster = findViewById(R.id.img_poster);
        tv_Judul = findViewById(R.id.tv_judul);
        tv_Desc = findViewById(R.id.tv_desc);
        tv_Tgl = findViewById(R.id.tv_tgl);

        txtPoster = getIntent().getStringExtra("poster_path");
        txtJudul = getIntent().getStringExtra("titnile");
        txtDate = getIntent().getStringExtra("release_date");
        txtDesc = getIntent().getStringExtra("overview");

        Glide.with(getApplicationContext()).load("http://image.tmdb.org/t/p/w185/"+txtPoster)
                .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                .into(imgPoster);
        tv_Judul.setText(txtJudul);
        tv_Tgl.setText(txtDate);
        tv_Desc.setText(txtDesc);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}

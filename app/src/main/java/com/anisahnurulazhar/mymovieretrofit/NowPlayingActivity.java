
package com.anisahnurulazhar.mymovieretrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.anisahnurulazhar.mymovieretrofit.koneksi.ApiService;
import com.anisahnurulazhar.mymovieretrofit.koneksi.Server;
import com.anisahnurulazhar.mymovieretrofit.model.Movies;
import com.anisahnurulazhar.mymovieretrofit.model.ResponseMovies;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingActivity extends AppCompatActivity {

    RecyclerView rvPlay;
    private Adapter adapterPlay;
    List<Movies> listPlay = new ArrayList<>();

    ProgressDialog loadingPlay;
    ApiService apiPlay;

    private final String apiNow = BuildConfig.MOVIE_API_KEY;
    private final String language = "language";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);


        rvPlay = findViewById(R.id.rv_play);
        rvPlay.setHasFixedSize(true);
        rvPlay.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvPlay.setAdapter(adapterPlay);

        apiPlay = Server.getApiService();
        adapterPlay = new Adapter(getApplicationContext(), listPlay);
        nowplaying();
    }

    private void nowplaying() {
        loadingPlay = ProgressDialog.show(this, null, "loading...", true, false);
        apiPlay.getNowPlayingMovie(apiNow, language).enqueue(new Callback<ResponseMovies>() {
            @Override
            public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
                if (response.isSuccessful()) {
                    loadingPlay.dismiss();
                    listPlay = response.body().getMovies();
                    rvPlay.setAdapter(new Adapter(getApplicationContext(), listPlay));
                    adapterPlay.notifyDataSetChanged();
                } else {
                    loadingPlay.dismiss();
                    Toast.makeText(getApplicationContext(), "Data Gagal Ditampilkan", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseMovies> call, Throwable t) {
                loadingPlay.dismiss();
                Toast.makeText(getApplicationContext(), "Koneksi Jelek", Toast.LENGTH_SHORT).show();


            }
        });
    }
}

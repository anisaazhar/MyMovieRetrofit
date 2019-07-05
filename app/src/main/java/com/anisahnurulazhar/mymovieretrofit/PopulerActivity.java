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

public class PopulerActivity extends AppCompatActivity {
    RecyclerView rvPopuler;
    private Adapter adapterPop;
    List<Movies>listPop = new ArrayList<>();

    ProgressDialog loadingPop;
    ApiService apiPop;

    private final String api = BuildConfig.MOVIE_API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_populer);

        rvPopuler = findViewById(R.id.rv_populer);
        rvPopuler.setHasFixedSize(true);
        rvPopuler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvPopuler.setAdapter(adapterPop);

        apiPop = Server.getApiService();
        adapterPop = new Adapter(getApplicationContext(), listPop);
        populer();
    }

    private void populer() {
        loadingPop = ProgressDialog.show(this, null, "loading...", true, false);
        apiPop.getPopulerMovies(api).enqueue(new Callback<ResponseMovies>() {
            @Override
            public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
                if (response.isSuccessful()) {
                    loadingPop.dismiss();
                    listPop = response.body().getMovies();
                    rvPopuler.setAdapter(new Adapter(getApplicationContext(), listPop));
                    adapterPop.notifyDataSetChanged();
                } else {
                    loadingPop.dismiss();
                    Toast.makeText(getApplicationContext(), "Data Gagal Ditampilkan", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseMovies> call, Throwable t) {
                loadingPop.dismiss();
                Toast.makeText(getApplicationContext(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();


            }
        });
    }
}

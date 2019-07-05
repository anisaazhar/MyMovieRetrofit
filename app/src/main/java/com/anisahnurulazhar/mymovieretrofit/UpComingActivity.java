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

public class UpComingActivity extends AppCompatActivity {
    RecyclerView rvUp;
    private Adapter adapterUp;
    List<Movies> listUp = new ArrayList<>();

    ProgressDialog loadingUp;
    ApiService apiUp;

    private final String api = BuildConfig.MOVIE_API_KEY;
    private final String language = "language";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_coming);

        rvUp = findViewById(R.id.rv_up);
        rvUp.setHasFixedSize(true);
        rvUp.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvUp.setAdapter(adapterUp);

        apiUp = Server.getApiService();
        adapterUp = new Adapter(getApplicationContext(), listUp);
        upcoming();
    }

    private void upcoming() {
        loadingUp = ProgressDialog.show(this, null, "loading...", true, false);
        apiUp.getUpComingMovie(api, language).enqueue(new Callback<ResponseMovies>() {
            @Override
            public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
                if (response.isSuccessful()) {
                    loadingUp.dismiss();
                    listUp = response.body().getMovies();
                    rvUp.setAdapter(new Adapter(getApplicationContext(), listUp));
                    adapterUp.notifyDataSetChanged();
                } else {
                    loadingUp.dismiss();
                    Toast.makeText(getApplicationContext(), "Data Gagal Ditampilkan", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseMovies> call, Throwable t) {
                loadingUp.dismiss();
                Toast.makeText(getApplicationContext(), "Koneksi Jelek", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

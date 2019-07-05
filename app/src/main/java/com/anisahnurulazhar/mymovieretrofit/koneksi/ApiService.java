package com.anisahnurulazhar.mymovieretrofit.koneksi;

import com.anisahnurulazhar.mymovieretrofit.model.ResponseMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("3/discover/movie")
    Call<ResponseMovies>getAllMovies(@Query("api_key")String api_key,
                                     @Query("language")String language,
                                     @Query("sort_by")String sort_by,
                                     @Query("include_adult")String include_adult,
                                     @Query("include_video")String include_video);

    @GET("3/search/movie")
    Call<ResponseMovies>getSearchMovies(@Query("api_key")String api_key,
                                        @Query("language")String language,
                                        @Query("query")String query,
                                        @Query("page")String  page);

    @GET("/3/movie/popular")
    Call<ResponseMovies>getPopulerMovies(@Query("api_key") String api_key);

    @GET("/3/movie/now_playing")
    Call<ResponseMovies>getNowPlayingMovie(@Query("api_key") String api_key,
                                      @Query("language") String language) ;

    @GET("/3/movie/upcoming")
    Call<ResponseMovies>getUpComingMovie(@Query("api_key") String api_key,
                                         @Query("language") String language);
}


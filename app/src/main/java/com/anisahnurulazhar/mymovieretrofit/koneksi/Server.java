package com.anisahnurulazhar.mymovieretrofit.koneksi;

public class Server {
    //buat naruh base url (api dbnya)
    public static final String BASE_URL = "http://api.themoviedb.org";
    public static ApiService getApiService(){
        return RetrofitConfig.getclient(BASE_URL).create(ApiService.class);
    }

}

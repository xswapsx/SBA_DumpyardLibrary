package com.appynitty.swachbharatabhiyanlibrary.webservices;

import com.appynitty.swachbharatabhiyanlibrary.pojos.LocalityResultPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocalityWebService {
    @GET("/data/reverse-geocode-client")
    Call<LocalityResultPojo> getLocality(@Query("latitude") String lat, @Query("longitude") String longi);
}

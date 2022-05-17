package com.appynitty.swachbharatabhiyanlibrary.adapters.connection;

import android.content.Context;
import android.util.Log;

import com.appynitty.retrofitconnectionlibrary.connection.Connection;
import com.appynitty.swachbharatabhiyanlibrary.pojos.LocalityResultPojo;
import com.appynitty.swachbharatabhiyanlibrary.webservices.LocalityWebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocalityAdapterClass {
    private static final String TAG = "LocalityAdapterClass";
    private final Context mContext;
    String locality;

    private LocalityListener localityListener;

    public LocalityAdapterClass(Context context) {
        mContext = context;
    }

    public void setLocalityListener(LocalityListener localityListener) {
        this.localityListener = localityListener;
    }

    public void getCurrentLocality(String lat, String longi) {

        LocalityWebService service = Connection.createService(LocalityWebService.class, "https://api.bigdatacloud.net");
        service.getLocality(lat, longi).enqueue(new Callback<LocalityResultPojo>() {
            @Override
            public void onResponse(Call<LocalityResultPojo> call, Response<LocalityResultPojo> response) {
                if (response.code() == 200) {
                    locality = response.body().getLocality();
                    Log.e(TAG, "onResponse: locality:- " + locality);
                    localityListener.onSuccessCallback(locality);
                }
            }

            @Override
            public void onFailure(Call<LocalityResultPojo> call, Throwable t) {
                localityListener.onFailureCallback(t.getMessage());
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public interface LocalityListener {
        void onSuccessCallback(String message);

        void onFailureCallback(String message);

        void onErrorCallback();
    }
}

package com.balloonanimals.medgennetwork;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHolder {

    /* Singleton instance of ServiceGenerator */
    private static RetrofitHolder instance;

    private Retrofit mRetrofit;
    private Gson mGson;

    /**
     * Create instance of Retrofit using custom okhttp client and gson.
     */
    private RetrofitHolder() {
        mGson = new GsonBuilder().create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/api/v3")
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .client(getHttpClient())
                .build();
    }

    /**
     * Initializes the ServiceGenerator if null.
     *
     * @return singleton instance of ServiceGenerator for performing network operations.
     */
    public static RetrofitHolder get() {
        if (instance == null) {
            instance = new RetrofitHolder();
        }
        return instance;
    }

    /**
     * Create an implementation of the API endpoints defined by the API interface.
     *
     * @param serviceClass interface for which implementation is to be created.
     * @param <S>          class of the interface
     * @return implementation on API endpoint
     */
    public <S> S createService(Class<S> serviceClass) {
        return mRetrofit.create(serviceClass);
    }

    /**
     * Function to get {@link OkHttpClient} client for retrofit instance.
     * Method adds headerInterceptor and authenticatorInterceptor also sets logging on debug environment.
     *
     * @return {@link OkHttpClient} client for {@link Retrofit}.
     */
    private OkHttpClient getHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // Add Http logger interceptor
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE));
        return builder.build();
    }

}

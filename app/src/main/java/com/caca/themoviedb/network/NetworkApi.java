package com.caca.themoviedb.network;

import com.caca.themoviedb.BuildConfig;
import com.caca.themoviedb.util.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author caca rusmana
 */
public class NetworkApi {

    private static Retrofit retrofit = null;

    public static Retrofit getNetworkApi() {

        if (retrofit == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(90, TimeUnit.SECONDS)
                    .readTimeout(90, TimeUnit.SECONDS)
                    .writeTimeout(90, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build();

            Gson gson = new GsonBuilder().setDateFormat(Constant.RESPONSE_DATE_FORMAT).create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }
}

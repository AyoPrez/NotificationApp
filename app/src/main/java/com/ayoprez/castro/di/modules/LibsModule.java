package com.ayoprez.castro.di.modules;

import com.ayoprez.castro.BuildConfig;
import com.ayoprez.castro.common.Constants;
import com.ayoprez.castro.common.ErrorNotification;
import com.ayoprez.castro.common.ImageLib;
import com.ayoprez.castro.common.TimeUtils;
import com.ayoprez.castro.restful.RestfulService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ayo on 23.09.16.
 */
@Module
public class LibsModule {

    @Provides
    public OkHttpClient provideLoggingCapableHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return new OkHttpClient.Builder().addInterceptor(logging).build();
    }

    @Provides
    public Retrofit provideRetrofit(String baseURL) {
        return new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).client(provideLoggingCapableHttpClient()).build();
    }

    @Provides
    public RestfulService provideRestfulService(){
        return provideRetrofit(Constants.BASE_URL).create(RestfulService.class);
    }

    @Provides
    public ImageLib providePicassoLibrary(){
        return new ImageLib();
    }

    @Provides
    public ErrorNotification provideErrorNotification() {
        return new ErrorNotification();
    }

    @Provides
    public TimeUtils provideTimeUtils(){
        return new TimeUtils();
    }
}

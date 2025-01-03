package com.techcomputerworld.horoscapp.data.network

import com.techcomputerworld.horoscapp.BuildConfig.BASE_URL
import com.techcomputerworld.horoscapp.data.core.interceptors.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton //este patron de diseño es si se ha creado el objeto una vez devuelve el objeto una vez y listo y asi no hay mas creaciones de objetos iguales.
    fun provideRetrofit(okHttpClient:OkHttpClient):Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideHoroscopeApiService(retrofit: Retrofit):HoroscopeApiService {
        return retrofit.create(HoroscopeApiService::class.java)
    }
    @Provides
    fun provideRepository(apiService: HoroscopeApiService):Repository {
        //va a devolver la implementación del Repository
        return Repository(apiService)
    }
    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val interceptor:HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(authInterceptor)
            .build()
    }
}
package com.techcomputerworld.horoscapp.data.network

import com.techcomputerworld.horoscapp.data.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

//estamos creando esta interfaz para crear las peticiones que haremos a Internet, habra que hacer una clase para trabajar con esta interfaz
interface HoroscopeApiService {
    @GET("/{sign}")
    suspend fun getHoroscope(@Path("sign") sign:String): PredictionResponse


}
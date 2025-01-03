package com.techcomputerworld.horoscapp.data.network

import android.util.Log
import com.techcomputerworld.horoscapp.data.network.response.PredictionResponse
import com.techcomputerworld.horoscapp.domain.PredictionModel
import com.techcomputerworld.horoscapp.domain.model.IRepository
import javax.inject.Inject

//RepositoryImpl
class Repository @Inject constructor(private val apiService:HoroscopeApiService) : IRepository {
    override suspend fun getPrediction(sign: String): PredictionModel? {
        //llamar retrofit peticion retrofit
        runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("TCW","Ha ocurrido un error ${it.message}") }
        return null
        
    }
}
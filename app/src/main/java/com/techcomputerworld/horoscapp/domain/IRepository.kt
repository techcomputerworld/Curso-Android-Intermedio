package com.techcomputerworld.horoscapp.domain.model

import com.techcomputerworld.horoscapp.data.network.response.PredictionResponse
import com.techcomputerworld.horoscapp.domain.PredictionModel

// en el curso lo llama
// Repository
interface IRepository {
    suspend fun getPrediction(sign:String): PredictionModel?
}
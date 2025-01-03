package com.techcomputerworld.horoscapp.data.network.response

import com.google.gson.annotations.SerializedName
import com.techcomputerworld.horoscapp.domain.PredictionModel

//Esto es una data class porque es lo que vamos a usar para comunicarnos con la API
data class PredictionResponse (
    @SerializedName("date") val date:String,
    @SerializedName("horoscope") val horoscope:String,
    @SerializedName("sign") val sign:String
    ) {
    fun toDomain():PredictionModel {
        return PredictionModel(
            horoscope = horoscope,
            sign = sign
        )
    }
}

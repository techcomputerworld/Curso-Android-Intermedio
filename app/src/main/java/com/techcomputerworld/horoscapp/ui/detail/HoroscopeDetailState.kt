package com.techcomputerworld.horoscapp.ui.detail

import com.techcomputerworld.horoscapp.domain.model.HoroscopeModel

//esta es una clase para verificar el estado de nuestra vista HoroscopeDetailActivity en este caso que puede haber 3 estados:
// uno de funcionamiento, otro de cargando y otro de error.
sealed class HoroscopeDetailState {
    //estado cargando
    data object Loading: HoroscopeDetailState()
    //estado
    data class Error(val error:String):HoroscopeDetailState()
    data class Sucess(val data:String, val sign:String, val horoscopeModel: HoroscopeModel):HoroscopeDetailState()
}
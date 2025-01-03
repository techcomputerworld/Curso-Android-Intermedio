package com.techcomputerworld.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.techcomputerworld.horoscapp.data.providers.HoroscopeProvider
import com.techcomputerworld.horoscapp.domain.model.HoroscopeInfo
import com.techcomputerworld.horoscapp.domain.model.HoroscopeInfo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
//desde variables o el init no es necesario crearlo como variable el horoscopeProvider
class HoroscopeViewModel @Inject constructor( horoscopeProvider:HoroscopeProvider) : ViewModel() {

    //ahora usamos los FLOW para conectar entre el ViewModel y las vistas.
    //Haremois un FLOW para conectar la data con la vista
    //emptyList() lo que hace es crear la lista vacia
    /* Desde dentro de la clase HoroscopeViewModel puedo modificar _horoscope, la otra variable es para consultar si acaso desde fuera de esta clase la lista
    * de objetos horoscope pero no poder modificar ni hacer nada desde fuera de esta clase */
    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    val horoscope:StateFlow<List<HoroscopeInfo>> = _horoscope

    init {

        _horoscope.value = horoscopeProvider.getHoroscopes()
    }

}
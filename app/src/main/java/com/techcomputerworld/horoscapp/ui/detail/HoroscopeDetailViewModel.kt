package com.techcomputerworld.horoscapp.ui.detail



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcomputerworld.horoscapp.R
import com.techcomputerworld.horoscapp.domain.PredictionModel
import com.techcomputerworld.horoscapp.domain.model.HoroscopeModel
import com.techcomputerworld.horoscapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase) : ViewModel() {

    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state: StateFlow<HoroscopeDetailState> = _state

    private lateinit var horoscope: HoroscopeModel
    //La pregunnta que me hago es ¿como puedo directamente hacer lo que hace getPredictionUseCase sin tener qeu crear esa clase
    fun getHoroscope(sign:HoroscopeModel) {
        horoscope = sign
        viewModelScope.launch {
            _state.value = HoroscopeDetailState.Loading
            val result:PredictionModel? = withContext(Dispatchers.IO) { getPredictionUseCase(sign.name) }
            if (result!=null) {
                _state.value = HoroscopeDetailState.Sucess(result.horoscope, result.sign, horoscope)
            } else {
                _state.value = HoroscopeDetailState.Error("Ha ocurrido un error, intentelo más tarde")
            }
        }

    }
}
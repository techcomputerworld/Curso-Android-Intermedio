package com.techcomputerworld.horoscapp.data.providers

import com.techcomputerworld.horoscapp.domain.model.HoroscopeInfo
import com.techcomputerworld.horoscapp.domain.model.HoroscopeInfo.*
import javax.inject.Inject

//@Inject constructor() es preparar la clase para que se pueda utilizar por injeccion de dependencias en cualquier otra clase
class HoroscopeProvider @Inject constructor() {
    fun getHoroscopes(): List<HoroscopeInfo> {
        return listOf(
            Aries,
            Taurus,
            Gemini,
            Cancer,
            Leo,
            Virgo,
            Libra,
            Scorpio,
            Sagittarius,
            Capricorn,
            Aquarius,
            Pisces
        )
    }
}
package com.techcomputerworld.horoscapp.data.core.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.Request
import javax.inject.Inject

//@Inject constructor es para que sea inyectable en otras clases del proyecto
class AuthInterceptor @Inject constructor(): Interceptor {
    //Intercept(chain: Interceptor.Chain) va a ser la llamada que se va a hacer al retrofit y Response la respuesta que nos va a dar
    override fun intercept(chain: Interceptor.Chain): Response {
        //.build() consrtuye el objeto con la información
        val request: Request = chain.request().newBuilder().header("Autorization", "fjwfjwij").build()
        return chain.proceed(request)
    }

}

/* El token del header("Autorization", "cadena") lo suyo seria hacer otra clase
*
* */
class TokenManager @Inject constructor() {
    fun getToken():String = "suscribete"
}
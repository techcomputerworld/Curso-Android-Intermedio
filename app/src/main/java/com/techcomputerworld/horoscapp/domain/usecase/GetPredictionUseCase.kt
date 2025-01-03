package com.techcomputerworld.horoscapp.domain.usecase

import com.techcomputerworld.horoscapp.data.network.Repository
import javax.inject.Inject

/* ¿me puedo saltar este caso de uso? si, poniendo en el viewmodel esto sin crear esta clase y llamar a repository.getPrediction(sign)
*  Podria haber inyectado el repository en el viewmodel y listo.  Si se puede hacer perfectamente. No, no esta mal o puedo crear el caso de uso.
* Tampoco esta mal, realmente podemos hacerlo como queramos.
* Yo con mi forma de programar probablemente me salte el caso de uso y lo ponga directamente en el ViewModel. Hablo en general porque esto no deja
* de ser un ViewModel mas.
* */
class GetPredictionUseCase @Inject constructor(private val repository: Repository){
    suspend operator fun invoke(sign:String) = repository.getPrediction(sign)


}
package com.techcomputerworld.horoscapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.techcomputerworld.horoscapp.databinding.FragmentHoroscopeBinding
import com.techcomputerworld.horoscapp.domain.model.HoroscopeInfo
import com.techcomputerworld.horoscapp.domain.model.HoroscopeInfo.*
import com.techcomputerworld.horoscapp.domain.model.HoroscopeModel
import com.techcomputerworld.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    //Esta variable nos servira para traernos datos del viewmodel a la vista que este fragment
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()
    private lateinit var horoscopeAdapter: HoroscopeAdapter
    //el viewbinding es un poco diferente en los fragment con respecto a las activities
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initUIState()
        initList()
    }

    private fun initList() {
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = { horoscope ->
            val type :HoroscopeModel = when(horoscope) {
                Aquarius -> HoroscopeModel.Aquarius
                Aries -> HoroscopeModel.Aries
                Cancer -> HoroscopeModel.Cancer
                Capricorn -> HoroscopeModel.Capricorn
                Gemini -> HoroscopeModel.Gemini
                Leo -> HoroscopeModel.Leo
                Libra -> HoroscopeModel.Libra
                Pisces -> HoroscopeModel.Pisces
                Sagittarius -> HoroscopeModel.Sagittarius
                Scorpio -> HoroscopeModel.Scorpio
                Taurus -> HoroscopeModel.Taurus
                Virgo -> HoroscopeModel.Virgo
            }
            // aqui obtenemos en un enumado el tipo de dato que le ha dado click con el dedo a la pantalla

            /* Esto lo que hace directamente es usar el safeargs para dar con el horoscopo que hemso seleccionado aunque en las lineas de arriba lo hacemos */
            findNavController().navigate(
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
            )
        })
        binding.rvHoroscope.apply {
            //layoutManager = LinearLayoutManager(context)
            layoutManager = GridLayoutManager(context, 2)
            adapter = horoscopeAdapter
        }

    }

    private fun initUIState() {
        //enganchamos con el ViewModel
        //Las corutinas es una forma de gestión de hilos dentro de Android
        //podemos crear una corutina especial para los fragments y los activities
        //una corutina que se engancha al ciclo de vida del fragment y se puede utilizar tambien en un activity
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                //collect para suscribirse a horo
                horoscopeViewModel.horoscope.collect() { listHoroscope ->
                    //cuando entre aqui ha habido cambios en Horoscope
                    horoscopeAdapter.updateList(listHoroscope)

                }
            }
        }
    }

}
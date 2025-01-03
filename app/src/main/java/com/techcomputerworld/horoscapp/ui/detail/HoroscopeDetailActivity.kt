package com.techcomputerworld.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.techcomputerworld.horoscapp.R
import com.techcomputerworld.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.techcomputerworld.horoscapp.domain.model.HoroscopeModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()
    //esta variable nos sirve para recuperar el type y asi saber que horoscopo es con el que vamos a trabajar
    private val args:HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener((binding.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //aqui recuperamos el type que es el horosocopo que vamos a ver
        //args.type
        initUI()
        horoscopeDetailViewModel.getHoroscope(args.type)
    }
    private fun initUI() {
        initListeners()
        initUIState()
    }
    private fun initListeners() {
        binding.ivBack.setOnClickListener{ onBackPressedDispatcher.onBackPressed() }
    }
    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect{
                    when(it) {
                        is HoroscopeDetailState.Error -> errorState()
                        HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Sucess -> successState(it)
                    }
                }
            }
        }
    }
    private fun loadingState() {
        binding.pb.isVisible = true
    }
    private fun errorState() {
        binding.pb.isVisible = false
    }
    private fun successState(state: HoroscopeDetailState.Sucess) {
        binding.pb.isVisible = false
        binding.tvTitle.text = state.sign
        //en el curso esta como state.prediction porque lo ha puesto como prediction
        binding.tvBody.text = state.data

        val image:Int = when (state.horoscopeModel) {
            HoroscopeModel.Aries -> R.drawable.detail_aries
            HoroscopeModel.Taurus -> R.drawable.detail_taurus
            HoroscopeModel.Gemini -> R.drawable.detail_gemini
            HoroscopeModel.Cancer -> R.drawable.detail_cancer
            HoroscopeModel.Leo -> R.drawable.detail_leo
            HoroscopeModel.Virgo -> R.drawable.detail_virgo
            HoroscopeModel.Libra -> R.drawable.detail_libra
            HoroscopeModel.Scorpio -> R.drawable.detail_scorpio
            HoroscopeModel.Sagittarius -> R.drawable.detail_sagittarius
            HoroscopeModel.Capricorn -> R.drawable.detail_capricorn
            HoroscopeModel.Aquarius -> R.drawable.detail_aquarius
            HoroscopeModel.Pisces -> R.drawable.detail_pisces
        }
        binding.ivDetail.setImageResource(image)
    }
}
package com.techcomputerworld.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.techcomputerworld.horoscapp.databinding.ItemHoroscopeBinding
import com.techcomputerworld.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder (view: View) : RecyclerView.ViewHolder(view){
    private val binding = ItemHoroscopeBinding.bind(view)
    fun render(horoscopeInfo:HoroscopeInfo, onItemSelected:(HoroscopeInfo) -> Unit) {
        val context = binding.tvTitle.context
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvTitle.text = context.getString(horoscopeInfo.name)

        binding.parent.setOnClickListener {
            //con newLambda = {onItemSelected(horoscopeInfo)}) sabemos la informacion del horoscopo que queremos consultar su información
            startRotationAnimation(binding.ivHoroscope, newLambda = {onItemSelected(horoscopeInfo)})
            onItemSelected (horoscopeInfo)
        }
    }
    fun startRotationAnimation(view:View, newLambda:() -> Unit ) {
        view.animate().apply{
            duration = 500
            interpolator = LinearInterpolator()
            rotationBy(360f)
            withEndAction{ newLambda() }
            start()
        }
    }
}
package com.tom.bmi2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BmiViewModel :ViewModel() {
    val bmi = MutableLiveData<Float>()
    fun set(weight: Float, height: Float) {
        val _bmi = String.format("%.2f",weight/(height*height)).toFloat()
        bmi.value = _bmi
    }
}
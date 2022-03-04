package com.tom.bmi2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tom.bmi2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun bmi(view:View) {
        println("hahaha")
        var  weight = binding.edWeight.text.toString().toFloat()
        var  height = binding.edHeight.text.toString().toFloat()
        val  bmi: Float = weight/(height*height)
//        println("BMI = ${weight/(height*height)}")
        Toast.makeText(this,"Your BMI is ${bmi.toString()}",Toast.LENGTH_LONG).show()
    }
}
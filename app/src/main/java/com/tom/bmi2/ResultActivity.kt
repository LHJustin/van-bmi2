package com.tom.bmi2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tom.bmi2.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private val TAG = ResultActivity::class.java.simpleName
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}
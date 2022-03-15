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
        //執行接收showBmi的動作
        showBmi()
        binding.bDone.setOnClickListener{
            val name = binding.edName.text.toString()
            val data = Intent()
            data.putExtra(Extras.NAME,name)
            setResult(RESULT_OK, data)
            finish()
        }
    }
    //從MainActivity接收bmi的數值
    private fun showBmi() {
        val bmi = intent.getFloatExtra(Extras.BMI,0f)
        val d = Log.d(TAG, "BMI:$bmi")
        binding.bmiDisplay.text = bmi.toString()
    }

}
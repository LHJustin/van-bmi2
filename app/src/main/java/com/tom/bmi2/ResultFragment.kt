package com.tom.bmi2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tom.bmi2.databinding.FragmentResultBinding

class ResultFragment: Fragment() {
    private val TAG = MainActivity::class.java.simpleName
    lateinit var binding : FragmentResultBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //執行接收showBmi的動作
//        showBmi()
        binding.bDone.setOnClickListener{
            val name = binding.edName.text.toString()
            /*val data = Intent()
            data.putExtra(Extras.NAME,name)
            setResult(AppCompatActivity.RESULT_OK, data)*/
//            finish()
        }
    }

    /*//從MainActivity接收bmi的數值
    private fun showBmi() {
        val bmi = intent.getFloatExtra(Extras.BMI,0f)
        val d = Log.d(TAG, "BMI:$bmi")
        binding.bmiDisplay.text = bmi.toString()
    }*/
}
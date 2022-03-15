package com.tom.bmi2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.tom.bmi2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val REQUEST_DISPLAY_BMI = 16
    private val TAG = MainActivity::class.java.simpleName
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: BmiViewModel
    //在合約裡使用從ResultActivity接收到的字串
    var launcher = registerForActivityResult(NameContract()){ name ->
        Toast.makeText(this, name, Toast.LENGTH_LONG).show()
        binding.tvBmi.text = name
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: ")
        viewModel = ViewModelProvider(this).get(BmiViewModel::class.java)
        viewModel.bmi.observe(this) { bmi ->
            binding.tvBmi.setText(bmi.toString())
        }
        binding.bHelp.setOnClickListener{
            Log.d("MainActivity","Need help!")
        }

    }

    fun bmi(view:View) {
//        println("hahaha")

        var  weight = binding.edWeight.text.toString().toFloat()
        var  height = binding.edHeight.text.toString().toFloat()
        viewModel.set(weight, height)
    }

    //設一個NameContract的Class等等在上面丟進合約
    class NameContract : ActivityResultContract<Float,String>(){
        //預備傳送值給ResultActivity
        override fun createIntent(context: Context, input: Float?): Intent {
            val intent = Intent(context,ResultActivity::class.java).putExtra(Extras.BMI,input)
            return intent
        }
        //預備接收ResultActivity的字串，如果RESULT_OK判斷沒有內容會傳No name
        override fun parseResult(resultCode: Int, intent: Intent?): String {
            if (resultCode == RESULT_OK){
                val name = intent!!.getStringExtra(Extras.NAME)
                return name!!
            }else{
                return "No name"
            }
        }

    }



    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

}
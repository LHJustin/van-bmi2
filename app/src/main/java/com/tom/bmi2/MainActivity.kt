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
import com.tom.bmi2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val REQUEST_DISPLAY_BMI = 16
    private val TAG = MainActivity::class.java.simpleName
    lateinit var binding: ActivityMainBinding
    var launcher = registerForActivityResult(NameContract()){ name ->
        Toast.makeText(this, name, Toast.LENGTH_LONG).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: ")
        binding.bHelp.setOnClickListener{
            Log.d("MainActivity","Need help!")
        }

    }

    fun bmi(view:View) {
//        println("hahaha")

        var  weight = binding.edWeight.text.toString().toFloat()
        var  height = binding.edHeight.text.toString().toFloat()
        var  bmi: Float = weight/(height*height)
//        println("BMI = ${weight/(height*height)}")
        Log.d("MainActivity",bmi.toString())
        Toast.makeText(this,"Your BMI is ${bmi.toString()}",Toast.LENGTH_LONG).show()
        /*val builder = AlertDialog.Builder(this)
        builder.setTitle("Hello")
        builder.setMessage("Your BMI is $bmi")
        builder.setPositiveButton("OK",null)
        val dialog = builder.create()
        dialog.show()*/
        AlertDialog.Builder(this)
            .setTitle("Messange")
            .setMessage("Your BMI is $bmi")
            .setPositiveButton("Ok", ){ dialog,which ->
                binding.edWeight.setText("")
                binding.edHeight.setText("")
            }
            .show()
        binding.tvBmi.text = "Your BMI is $bmi"
//        val intent = Intent(this,ResultActivity::class.java)
//        intent.putExtra("BMI",bmi)
//        startActivity(intent)
//        startActivityForResult(intent,REQUEST_DISPLAY_BMI)
        launcher.launch(bmi)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult")
        if (requestCode == REQUEST_DISPLAY_BMI && resultCode == RESULT_OK){
            Log.d(TAG, "back from ResultActivity")
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

    class NameContract : ActivityResultContract<Float,String>(){
        override fun createIntent(context: Context, input: Float?): Intent {
            val intent = Intent(context,ResultActivity::class.java).putExtra(Extras.BMI,input)
            return intent
        }

        override fun parseResult(resultCode: Int, intent: Intent?): String {
            if (resultCode == RESULT_OK){
                val name = intent!!.getStringExtra(Extras.NAME)
                return name!!
            }else{
                return "No name"
            }
        }
    }
    
}
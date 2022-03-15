package com.tom.bmi2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tom.bmi2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val REQUEST_DISPLAY_BMI = 16
    lateinit var binding: ActivityMainBinding
    private val TAG = MainActivity::class.java.simpleName
    lateinit var viewModel: BmiViewModel
    val fragments = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: ")
        viewModel = ViewModelProvider(this).get(BmiViewModel::class.java)
        initFragments()
        binding.bottomNavBar.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.action_home->{
                    supportFragmentManager.beginTransaction().run {
                        replace(R.id.my_container, fragments[0]).commit()
                    }
                    true
                }
                R.id.action_camera->{
                    true
                }
                R.id.action_bmi->{
                    supportFragmentManager.beginTransaction().run {
                        replace(R.id.my_container, ResultFragment()).commit()
                    }
                    true
                }
                else -> true
            }
        }

    }
    private fun initFragments() {
        fragments.add(0,BlankFragment())
        /*val bmifragment = BlankFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.my_container, bmifragment).commit()*/

        //Kotlin way
        val t = supportFragmentManager.beginTransaction().run {
            add(R.id.my_container, fragments[0])
            commit()
        }
    }

//    fun bmi() {
//        println("hahaha")
//
//    }




    /*override fun onStart() {
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
    }*/

}
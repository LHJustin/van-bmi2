package com.tom.bmi2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tom.bmi2.databinding.FragmentBlankBinding

class BlankFragment: Fragment() {
    lateinit var binding: FragmentBlankBinding
    val viewModel by viewModels<BmiViewModel>()

    //在合約裡使用從ResultActivity接收到的字串
    var launcher = registerForActivityResult(BlankFragment.NameContract()) { name ->
        Toast.makeText(context, name, Toast.LENGTH_LONG).show()
        binding.tvBmi.text = name
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlankBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bBmi.setOnClickListener {
            val weight = binding.edWeight.text.toString().toFloat()
            val height = binding.edHeight.text.toString().toFloat()
            viewModel.set(weight, height)
            viewModel.bmi.observe(viewLifecycleOwner) { bmi ->
                binding.tvBmi.setText(bmi.toString())
                launcher.launch(bmi)
            }
        }

        binding.bHelp.setOnClickListener {
            Log.d("MainActivity", "Need help!")
        }
    }

    //設一個NameContract的Class等等在上面丟進合約
    class NameContract : ActivityResultContract<Float, String>() {
        //預備傳送值給ResultActivity
        override fun createIntent(context: Context, input: Float): Intent {
            val intent = Intent(context, ResultFragment::class.java).putExtra(Extras.BMI, input)
            return intent
        }
        //預備接收ResultActivity的字串，如果RESULT_OK判斷沒有內容會傳No name
        override fun parseResult(resultCode: Int, intent: Intent?): String {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                val name = intent!!.getStringExtra(Extras.NAME)
                return name!!
            } else {
                return "No name"
            }
        }
    }
}
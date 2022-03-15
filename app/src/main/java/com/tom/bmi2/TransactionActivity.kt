package com.tom.bmi2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.tom.bmi2.databinding.ActivityTransactionBinding
import com.tom.bmi2.databinding.RowRecyclerBinding
import org.json.JSONArray
import java.net.URL
import kotlin.concurrent.thread
import kotlin.math.tan

class TransactionActivity : AppCompatActivity() {
    lateinit var binding: ActivityTransactionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recycler.hasFixedSize()
        binding.recycler.layoutManager = LinearLayoutManager(this)
        thread {
            val json = URL("https://atm201605.appspot.com/h").readText()
            val gson = Gson()
            val transactions = gson.fromJson(json, Array<Transaction>::class.java).toList()
            transactions.forEach {
                println(it)
            }

            /*val transactions = mutableListOf<Transaction>()
            val array = JSONArray(json)
            for (i in 0 until array.length()){
                val obj = array.getJSONObject(i)
                val amount = obj.getInt("amount")
                val account = obj.getString("account")
                val date = obj.getString("date")
                val type = obj.getInt("type")
                val tran = Transaction(account, date, amount, type)
                transactions.add(tran)
            }*/
            runOnUiThread {
                binding.recycler.adapter = object : RecyclerView.Adapter<TranViewHolder>() {
                    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranViewHolder {
                        val binding = RowRecyclerBinding.inflate(layoutInflater, parent, false)
                        return TranViewHolder(binding)
                    }

                    override fun onBindViewHolder(holder: TranViewHolder, position: Int) {
                        val tran = transactions.get(position)
                        holder.amount.setText(tran.amount.toString())
                        holder.date.setText(tran.date)
                        holder.type.setText(tran.type.toString())
                    }

                    override fun getItemCount(): Int {
                        return transactions.size
                    }

                }
            }

        }
    }
    inner class TranViewHolder(val binding: RowRecyclerBinding):
            RecyclerView.ViewHolder(binding.root){
                val amount = binding.tranAmount
                val date = binding.tranDate
                val type = binding.tranType
            }
}
data class Transaction(val account: String, val date: String, val amount: Int, val type: Int){

}
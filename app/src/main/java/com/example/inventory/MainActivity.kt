package com.example.inventory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.inventory.databinding.ActivityMainBinding
import com.example.inventory.viewmodel.InventoryViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var myModels: InventoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appContainer = (application as MyApp).container.factory

        myModels = ViewModelProvider(this, appContainer).get(InventoryViewModel::class.java)

        binding.save.setOnClickListener {
            val name = binding.item.text.toString()
            val price = binding.price.text.toString()
            val stock = binding.stock.text.toString()
            myModels.addNewItem(name, price, stock)
            reset()
        }

        binding.inventory.setOnClickListener {
            startActivity(Intent(this, InventoryActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
        }
    }

    private fun reset(){
        binding.item.text.clear()
        binding.price.text.clear()
        binding.stock.text.clear()
    }
}
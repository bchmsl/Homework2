package com.bchmsl.homework2

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.bchmsl.homework2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var converter: NumbersConverter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        init()
    }


    private fun init() {
        converter = NumbersConverter()
        listeners()
    }

    private fun getValue(): Int? {
        val field = binding.etNumberInput
        val value: Int
        return try {
            value = field.text.toString().toInt()
            if (value in 1..10000) {
                value
            } else {
                throw Exception("InvalidNumberException")
            }
        } catch (e: Exception) {
            binding.tvInstructions.setTextColor(Color.RED)
            null
        }
    }

    private fun listeners() {
        binding.btnConvert.setOnClickListener {
            val value = getValue()
            if (value != null) {
                val result = converter.convertNumber(value)
                returnResult(result)
            }
            this.hideKeyboard(this.currentFocus)
        }
        binding.etNumberInput.addTextChangedListener {
            binding.tvInstructions.setTextColor(Color.WHITE)
            binding.tvResult.text = ""
        }
        binding.etNumberInput.setOnClickListener {
            binding.tvInstructions.setTextColor(Color.WHITE)
            binding.tvResult.text = ""
            binding.etNumberInput.setText("")
        }
    }

    private fun returnResult(result: String) {
        binding.tvResult.text = result
    }


}
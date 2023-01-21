package com.example.new_bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etheight)
        val calcButton = findViewById<Button>(R.id.button)

        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if(validate(weight,height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }
        }
    }

    private fun validate(weight:String?,height:String?):Boolean{
        return when
        {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Please Enter Weight", Toast.LENGTH_SHORT).show()
                return false;
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Please Enter Height", Toast.LENGTH_SHORT).show()
                return false;
            }
            else ->
                return true;
        }
    }

    private fun displayResult(bmi:Float)
    {
        val resultIndex = findViewById<TextView>(R.id.TvIndex)
        val description = findViewById<TextView>(R.id.TvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = "(Normal Range is 18.5 - 24.9)"

        var resultText = ""
        var color = 0

        when
        {
            bmi < 18.5->{
                resultText = "UnderWeight"
                color = R.color.underWeight
            }
            bmi in 18.5..24.99->{
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99->{
                resultText = "OverWeight"
                color = R.color.OverWeight
            }
            bmi > 30.00->{
                resultText = "Obese"
                color = R.color.Obesity
            }
        }
        description.setTextColor(ContextCompat.getColor(this,color))
        description.text = resultText
    }
}
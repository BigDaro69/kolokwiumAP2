package com.example.kolokwiumantonipol2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kolokwiumantonipol2.databinding.ActivityMainBinding
import android.widget.Toast
import kotlin.math.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val history = mutableListOf<Double>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.obliczbtn.setOnClickListener() {
            val wiekobl = binding.wiekText.text.toString()
            val wzrostobl = binding.wzrostText.text.toString()
            val wagaobl = binding.wagaText.text.toString()

            val wzrost = wzrostobl.toDouble() / 100
            val waga = wagaobl.toDouble()
            val wynikBMI = waga / (wzrost * wzrost)

            history.add(wynikBMI)

            Toast.makeText(
                applicationContext,
                "Twoje bmi wynosi: %.2f".format(wynikBMI),
                Toast.LENGTH_SHORT
            ).show()
            binding.histbtn.setOnClickListener()
            {
            }

            if (history.isNotEmpty()) {
                val historyString =
                    history.joinToString(separator = "\n") { "BMI: %.2f".format(it) }
                binding.histText.text = String.format("Historia BMI:\n$historyString")
            } else {
                binding.histText.text = String.format("Brak zapisanych wyników BMI")
            }
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }


    }
}
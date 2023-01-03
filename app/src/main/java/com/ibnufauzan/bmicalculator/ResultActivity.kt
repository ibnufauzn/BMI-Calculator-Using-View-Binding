package com.ibnufauzan.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ibnufauzan.bmicalculator.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var textResult: String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getResult()
        backBtn()
        binding.result.text = textResult
    }

    private fun backBtn() {
        binding.backBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun getResult() {
        val gender = intent.extras!!.getString("gender")
        val name = intent.extras!!.getString("name")
        val bb = intent.extras!!.getInt("bb", 1)
        val tb = intent.extras!!.getInt("tb", 1)

        val result: Double = bb / ((tb * 0.01) * (tb * 0.01))
        when {
            result < 18.5 -> {
                binding.imgResult.setImageResource(R.drawable.deficit)
                textResult = """Hai $name, 
                    |Jenis kelamin Anda $gender. 
                    |berdasarkan nilai BMI, 
                    |Anda mengalami kekurangan berat badan.
                    |Silahkan konsumsi makanan bernutrisi.
                """.trimMargin()
            }
            result in 18.5..24.5 -> {
                binding.imgResult.setImageResource(R.drawable.normal)
                textResult = """Hai $name, 
                    |Jenis kelamin Anda $gender. 
                    |berdasarkan nilai BMI,
                    |Anda memiliki berat badan yang ideal.
                    |Tetap jaga pola makan dan gaya hidup Anda.
                """.trimMargin()
            }
            result in 24.5..29.9 -> {
                binding.imgResult.setImageResource(R.drawable.overweight)
                textResult = """Hai $name, 
                    |Jenis kelamin Anda $gender. 
                    |berdasarkan nilai BMI,
                    |Anda memiliki berat badan yang berlebih.
                    |Atur pola makan dan gaya hidup Anda agar tetap sehat.
                """.trimMargin()
            }
            else -> {
                binding.imgResult.setImageResource(R.drawable.obesitas)
                textResult = """Hai $name, 
                    |Jenis kelamin Anda $gender. 
                    |berdasarkan nilai BMI,
                    |Anda mengalami obesitas.
                    |Atur pola makan dan gaya hidup Anda agar tetap sehat.
                """.trimMargin()
            }
        }
    }
}
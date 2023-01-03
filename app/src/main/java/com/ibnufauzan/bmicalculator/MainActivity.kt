package com.ibnufauzan.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ibnufauzan.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var choose: String
    private var idBtn: Int = -1
    private val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        btnSubmitIsClicked()
    }

    private fun btnSubmitIsClicked(){
        binding.gender.setOnCheckedChangeListener { radioGroup, _ ->
            idBtn = radioGroup.checkedRadioButtonId
            choose = when (idBtn) {
                binding.genderMale.id -> binding.genderMale.text.toString()
                else -> binding.genderFemale.text.toString()
            }
        }

        binding.btnSubmit.setOnClickListener {
            try {
                inputValidation()
            }
            catch (e: InputValidation) {
                Toast.makeText(applicationContext, "${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inputValidation() {
        when {
            binding.gender.checkedRadioButtonId == -1 -> throw InputValidation("Pilih Jenis Kelamin")
            binding.name.text.isNullOrEmpty() -> throw InputValidation("Masukkan Nama Lengkap Terlebih Dahulu")
            binding.tb.text.isNullOrEmpty() -> throw InputValidation("Masukkan Tinggi Badan Terlebih Dahulu")
            binding.bb.text.isNullOrEmpty() -> throw InputValidation("Masukkan Berat Badan Terlebih Dahulu")
            else ->
            {
                bundle.putString("gender", choose)
                bundle.putString("name", binding.name.text.toString())
                bundle.putInt("tb", binding.tb.text.toString().toInt())
                bundle.putInt("bb", binding.bb.text.toString().toInt())

                Intent(this, ResultActivity::class.java).apply {
                    putExtras(bundle)
                    startActivity(this)
                }
            }
        }
    }
}
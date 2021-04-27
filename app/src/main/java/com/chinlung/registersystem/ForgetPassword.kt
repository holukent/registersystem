package com.chinlung.registersystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chinlung.registersystem.databinding.ActivityForgetPasswordBinding

class ForgetPassword : AppCompatActivity() {

    lateinit var binding: ActivityForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnForgetOk.setOnClickListener {
            val inputAccount = binding.editForgetInput.text.toString()
            val preference =
                getSharedPreferences(inputAccount, MODE_PRIVATE).getString("userPassword", "")
            when {
                inputAccount == "" -> {
                    Toast.makeText(this, "請輸入帳號", Toast.LENGTH_SHORT).show()
                    binding.textForgetPassword.text = "密碼:"

                }
                preference == "" -> {
                    Toast.makeText(this, "尚無此帳號", Toast.LENGTH_SHORT).show()
                    binding.textForgetPassword.text = "密碼:"
                }
                else -> binding.textForgetPassword.text = "密碼: $preference"
            }
            binding.editForgetInput.setText("")

        }

        binding.btnForgetBackMain.setOnClickListener { finish() }

    }
}
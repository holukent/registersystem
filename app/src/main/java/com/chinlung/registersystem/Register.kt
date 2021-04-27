package com.chinlung.registersystem

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chinlung.registersystem.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var preference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imglist = listOf(R.drawable.baseball,
            R.drawable.basketball,
            R.drawable.football,
            R.drawable.other)

        binding.Phone.setText(randomphone())
        binding.Birthday.setText(randombirthday())
        binding.Name.setText(randomname())



        binding.btnRegisterOk.setOnClickListener {
            preference = getSharedPreferences(binding.UserAccount.text.toString(), MODE_PRIVATE)

            if (binding.Name.text.toString() == "" ||
                binding.UserAccount.text.toString() == "" ||
                binding.UserPassword.text.toString() == "" ||
                binding.Phone.text.toString() == "" ||
                binding.Birthday.text.toString() == ""
            ) {
                Toast.makeText(this, "請輸入正確", Toast.LENGTH_SHORT).show()
            } else {

                preference.edit().putString("userAccount", binding.UserAccount.text.toString())
                    .apply()
                preference.edit().putString("userPassword", binding.UserPassword.text.toString())
                    .apply()
                preference.edit().putString("name", binding.Name.text.toString()).apply()
                preference.edit().putString("phone", binding.Phone.text.toString()).apply()
                preference.edit().putString("birthday", binding.Birthday.text.toString()).apply()
                preference.edit().putInt("imgId", imglist.random()).apply()
                finish()
            }
        }

        binding.btnBackMain.setOnClickListener { finish() }
    }

    fun randomphone(): String {
        var result = "09"
        repeat(8) {
            result += ('0'..'9').random()
        }
        return result
    }

    fun randombirthday(): String {
        val year = (1950..2021).random().toString()
        val month = (1..12).random().toString()
        val day = (1..31).random().toString()
        return "$year/$month/$day"
    }

    fun randomname(): String {
        var result = ""
        repeat((5..15).random()) {
            result += ('a'..'z').random()
        }
        return result
    }
}
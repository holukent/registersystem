package com.chinlung.registersystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chinlung.registersystem.databinding.ActivityDetailBinding

class Detail : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preference = getSharedPreferences(intent.getStringExtra("account"), MODE_PRIVATE)

        binding.textDetailUser.text = "Account: ${preference.getString("userAccount", null)}"
        binding.textDetailName.text = "Name: \n${preference.getString("name", null)}"
        binding.textDetailPhone.text = "Phone: ${preference.getString("phone", null)}"
        binding.textDetailBirthday.text = "Birthday: ${preference.getString("birthday", null)}"
        binding.detailImageView.setImageResource(preference.getInt("imgId",R.drawable.other))

        binding.btnDetailBackMain.setOnClickListener { finish() }
    }

}
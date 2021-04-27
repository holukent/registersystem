package com.chinlung.registersystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.chinlung.registersystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerForContextMenu(binding.textContact)


        binding.btnLogin.setOnClickListener {
            val account = getSharedPreferences(binding.editAccount.text.toString(),
                MODE_PRIVATE).getString("userAccount", null)
            val password = getSharedPreferences(binding.editPassword.text.toString(),
                MODE_PRIVATE).getString("userPassword", null)

            when {
                binding.editAccount.text.toString() == "" ->
                    Toast.makeText(this, "請輸入帳號", Toast.LENGTH_SHORT).show()
                binding.editPassword.text.toString() == "" ->
                    Toast.makeText(this, "請輸入密碼", Toast.LENGTH_SHORT).show()
                account == null || password == null ->
                    Toast.makeText(this, "帳號密碼錯誤", Toast.LENGTH_SHORT).show()
                else -> {
                    val intent = Intent(this, Detail::class.java).also {
                        it.putExtra("account", binding.editAccount.text.toString())
                    }
                    startActivity(intent)
                }
            }
        }
        binding.btnFinish.setOnClickListener {
            finish()
        }
    }

    private fun MENU_First() = Menu.FIRST
    private fun MENU_Second() = Menu.FIRST + 1

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?,
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        when (v) {
            binding.textContact -> {
                menu?.add(0, MENU_First(), 1, "註冊帳號")
                menu?.add(0, MENU_Second(), 1, "忘記密碼")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENU_First() ->
                startActivity(Intent(this, Register::class.java))

            MENU_Second() ->
                startActivity(Intent(this, ForgetPassword::class.java))

        }
        return super.onContextItemSelected(item)
    }
}
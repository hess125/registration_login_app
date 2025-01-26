package com.example.formapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.formapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = applicationContext.getSharedPreferences("user_details", Context.MODE_PRIVATE)
    }

    fun btnClick(view: View) {
        when (view.id){
            binding.btnLog.id -> {
                val user = binding.usern1.text.toString()                  // input values
                val pwd = binding.pwd1.text.toString()

                val existUName = pref.getString("username", null)          //existing values
                val existPwd = pref.getString("pwd", null)

                // Entered details match existing ones
                if (user == existUName && pwd == existPwd) {
                    Toast.makeText(this, getString(R.string.valid_log), Toast.LENGTH_SHORT).show()
                    val login = Intent(this, UserDetails::class.java)
                    startActivity(login) }

                else { // Invalid details
                    Toast.makeText(this, getString(R.string.invalid_log), Toast.LENGTH_SHORT).show()
                }
            }
            binding.regLnk.id -> {          //Register new acc
                val register = Intent(this, Register::class.java)
                startActivity(register)
            }
        }
    }
}
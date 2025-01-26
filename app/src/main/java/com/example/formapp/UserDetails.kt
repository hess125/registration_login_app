package com.example.formapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.formapp.databinding.ActivityUserDetailsBinding
import java.text.SimpleDateFormat
import java.time.Year
import java.util.Calendar
import java.util.Date

class UserDetails : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailsBinding
    private lateinit var pref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = applicationContext.getSharedPreferences("user_details", Context.MODE_PRIVATE)

        //Getting details from Shared Pref
        val fname = pref.getString("fname", null)
        val username = pref.getString("username", null)
        val gender = pref.getString("gender", null)
        val yob = pref.getString("yob", null)
        val pwd = pref.getString("pwd", null)

        //Calculating Age
        val yobConvert = yob?.toIntOrNull()
        val yearsOld =  Calendar.getInstance().get(Calendar.YEAR) - yobConvert!!
//        (SimpleDateFormat("yyyy")).format(Date()).toInt()

        // Displaying the details
        binding.namedit.append(" $fname!")
        binding.usern2.append(": $fname")
        binding.gender2.append(" $gender")
        binding.yob.append(" $yob")
        binding.ageYear.append(" $yearsOld")
        binding.userntxt.append(" $username")
        binding.pwdtxt.append(" $pwd")

        //Log Out
        binding.logout.setOnClickListener{
            Toast.makeText(this, getString(R.string.logout), Toast.LENGTH_SHORT).show()
            val int = Intent (this, MainActivity::class.java)
            startActivity(int)
            finish()
        }
    }
}
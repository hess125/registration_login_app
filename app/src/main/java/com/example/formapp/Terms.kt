package com.example.formapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.formapp.databinding.ActivityTermsBinding

class Terms : AppCompatActivity() {

    private lateinit var binding: ActivityTermsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTermsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goReg.setOnClickListener {
            finish()
        }

    }
}
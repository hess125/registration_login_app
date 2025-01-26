package com.example.formapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.formapp.databinding.ActivityRegisterBinding
import com.google.android.material.textfield.TextInputLayout

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = applicationContext.getSharedPreferences("user_details", Context.MODE_PRIVATE)

        floatingErrorCheck(binding.textlayout2)
        floatingErrorCheck(binding.textlayout3)
        floatingErrorCheck(binding.textlayout4)

        val years = (1930..2024).toList()       //for spinner values
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years.map { it.toString() })
        binding.yobSpin.adapter = adapter
    }

    fun onClick(view: View){
        when (view.id){
            binding.cl.id ->{                   //  Clear Form Button
                binding.nametxt.text = null
                binding.gender.clearCheck()
                binding.yobSpin.setSelection(0)
                binding.usern.text = null
                binding.pwd.text = null
                binding.repwd.text = null
            }
            binding.reg.id ->{                  // Register
                val username = binding.usern.text.toString()
                if (isTaken(username)) {
                    //Confirm username taken or not
                    Toast.makeText(this, getString(R.string.istaken), Toast.LENGTH_LONG).show()
                    binding.textlayout2.error = getString(R.string.istaken)
                    binding.textlayout2.isErrorEnabled = true}

                    //Confirm Radiobutton Checked
                else if (binding.gender.checkedRadioButtonId == -1) {
                    Toast.makeText(this, getString(R.string.chooseGend), Toast.LENGTH_LONG).show()
                    binding.textlayout3.isErrorEnabled = true }

                    //Confirm Spinner Checked
                else if (binding.yobSpin.selectedItemPosition == 0) {
                    Toast.makeText(this, getString(R.string.chooseYOB), Toast.LENGTH_LONG).show()}

                    //Password Check
                else if (binding.pwd.text.toString() != binding.repwd.text.toString()) {
                    Toast.makeText(this, getString(R.string.pwdne), Toast.LENGTH_LONG).show()
                    binding.textlayout4.error = getString(R.string.pwdne)
                    binding.textlayout4.isErrorEnabled = true
                }
                else if (!binding.checktac.isChecked){
                    Toast.makeText(this, getString(R.string.agree), Toast.LENGTH_SHORT).show()
                }
                else {  //Save Details
                    Log.d("saving",username)
                    saveDetails(username)
                    Toast.makeText(this, getString(R.string.reg_S), Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
            binding.gbck.id ->{         // Go to Login
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            binding.click.id ->{
                val intent = Intent(this, Terms::class.java)
                startActivity(intent)
            }
        }
    }

    //Confirm Username
    private fun isTaken(username: String): Boolean {
        val usedUName = pref.getString("username", null)
        if (usedUName == username)
            return true
        else{
            return false
        }
    }

    //Password Error Check
    private fun floatingErrorCheck(tlayout: TextInputLayout) {
        tlayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if ((s?.length ?: 0) <= 5) {
                    tlayout.error =
                        getString(R.string.error1)
                    tlayout.isErrorEnabled = true }
                else {
                    tlayout.isErrorEnabled = false
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    //Saving to Shared Pref
    private fun saveDetails(username: String) {
        val save =  pref.edit()
        save.putString("fname", binding.nametxt.text.toString())
        save.putString("username", username)
        save.putString("pwd", binding.pwd.text.toString())
        save.putString("gender", if (binding.gender.checkedRadioButtonId == R.id.m) "Male" else "Female")
        save.putString("yob", binding.yobSpin.selectedItem.toString())
        save.commit()
    }
}
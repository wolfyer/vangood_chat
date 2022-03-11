package com.vangood.chat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import com.vangood.chat.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    var remember = false
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val pref = getSharedPreferences("chat",Context.MODE_PRIVATE)
        val checked = pref.getBoolean("remember_me",false)


        binding.bChecklogin.isChecked = checked
        binding.bChecklogin.setOnCheckedChangeListener { compoundButton, checked ->
            remember = checked
            pref.edit()
                .putBoolean("remember_me",remember)
                .apply()
            if (!checked){
                pref.edit()
                    .putString(Extras.USERNAME,"")
                    .putString(Extras.PASSWORD,"")
                    .apply()

            }

        }
        val prefUser = pref.getString(Extras.USERNAME,"")
        val prefPass = pref.getString(Extras.PASSWORD,"")
        if (prefUser != ""){
            binding.tnName.setText(prefUser)
            binding.tnPassword.setText(prefPass)
        }
        binding.bLogin.setOnClickListener {
            val username = binding.tnName.text.toString()
            val password = binding.tnPassword.text.toString()

            if (username == "jack" && password == "1234"){
                if (remember){
                    pref.edit()
                        .putString(Extras.USERNAME,username)
                        .putString(Extras.PASSWORD,password)
                        .apply()
                }
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"login OK",Toast.LENGTH_LONG).show()
            }else{
                AlertDialog.Builder(this)
                    .setTitle("Login Fail")
                    .setMessage("would you like to try again?")
                    .setPositiveButton("OK"){d,w ->
                        binding.tnName.setText("")
                        binding.tnPassword.setText("")
                    }
                    .show()
                }
        }
        binding.bSign.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

    }
}
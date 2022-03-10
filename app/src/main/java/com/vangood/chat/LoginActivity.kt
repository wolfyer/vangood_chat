package com.vangood.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.vangood.chat.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    val remember = false
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val username = binding.tnName.text.toString()
        val password = binding.tnPassword.text.toString()
        binding.bLogin.setOnClickListener {
            if (username == "jack" && password == "1234"){
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
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
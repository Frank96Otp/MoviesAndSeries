package com.example.moviesandseries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.core.widget.addTextChangedListener
import com.example.moviesandseries.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {


    private lateinit var binding:ActivityLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
    }

    private fun setView() {

        binding.tilLogin.editText?.addTextChangedListener {
            binding.btnLogin.isEnabled =  validateEmailPassword(it.toString() , binding.tilPassword.editText?.text.toString())
        }

        binding.tilPassword.editText?.addTextChangedListener {
            binding.btnLogin.isEnabled = validateEmailPassword(binding.tilLogin.editText?.text.toString(), it.toString())
        }
    }


    private fun validateEmailPassword(email: String, password: String):Boolean {
        val validateEmail: Boolean = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val validatePassowod = password.length >= 8
        return validateEmail && validatePassowod
    }
    fun  goToMainActivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun goToSingUp(){
        val intent = Intent(this,SingUpActivity::class.java)
        startActivity(intent)
    }
}
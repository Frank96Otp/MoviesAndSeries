package com.example.moviesandseries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.example.moviesandseries.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {


    private lateinit var binding:ActivityLoginBinding
    private lateinit var fireAuth: FirebaseAuth
    private lateinit var googleLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()

        fireAuth =  Firebase.auth
        googleLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

        }
    }


    private fun setView() {

        binding.tilLogin.editText?.addTextChangedListener {
            binding.btnLogin.isEnabled =  validateEmailPassword(it.toString() , binding.tilPassword.editText?.text.toString())
        }

        binding.tilPassword.editText?.addTextChangedListener {
            binding.btnLogin.isEnabled = validateEmailPassword(binding.tilLogin.editText?.text.toString(), it.toString())
        }

        binding.btnLogin.setOnClickListener {   }

        binding.btnLoginWithGoogle.setOnClickListener {
                signinWithGoogle()
        }

        binding.btnSingUp.setOnClickListener {
                singUpWithFirebase()
        }

    }

    private fun signinWithGoogle() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        val googleClient =  GoogleSignIn.getClient(this, googleSignInOptions)

        val intent = googleClient.signInIntent

    }

    private fun singUpWithFirebase() {
        TODO("Not yet implemented")
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
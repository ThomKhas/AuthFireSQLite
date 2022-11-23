package com.example.authfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.authfirebase.databinding.ActivityMainBinding
import com.example.authfirebase.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        auth = Firebase.auth
        binding.btnBackSI.setOnClickListener {
            var intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            if (checkAllField()) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if(checkAllField()){
                        if(it.isSuccessful){
                            auth.signOut()
                            Toast.makeText(this,"Registrado exitosamente", Toast.LENGTH_SHORT).show()

                        }else{
                            Log.e("error", it.exception.toString())
                        }
                    }
                }
            }
        }
    }


    private fun checkAllField(): Boolean {
        val email = binding.etEmail.text.toString()
        if(binding.etEmail.text.toString() == "") {
            binding.emailInput.error = "Campo requerido"
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailInput.error = "Formato de correo incorrecto"
            return false
        }
        if(binding.etPassword.text.toString() == ""){
            binding.passInput.error = "Campo requerido"
            binding.passInput.errorIconDrawable = null
            return false
        }
        if(binding.etPassword.length() <= 7){
            binding.passInput.error = "La contraseña debe tener mínimo 8 caracteres"
            binding.passInput.errorIconDrawable = null
            return false
        }
        if(binding.etConPass.text.toString() == ""){
            binding.confirmPassInput.error = "Campo requerido"
            binding.confirmPassInput.errorIconDrawable = null
            return false
        }
        if(binding.etPassword.text.toString() != binding.etConPass.text.toString()){
            binding.passInput.error = "Contraseñas no coinciden"
            return false
        }
        return true
    }

}
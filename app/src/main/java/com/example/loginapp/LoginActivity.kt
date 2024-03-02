package com.example.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    //Variable de enlace con componentes de vista
    private lateinit var binding: ActivityLoginBinding

    //Realizado 01-03-2024
        private lateinit var db: DatabaseHelper
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Realizado 01-03-2024
            db = DatabaseHelper(this)
        //

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    // Realizado 01-03-2024
    private fun loginUser() {
        val email = binding.textInputEmail.text.toString().trim()
        val password = binding.textInputPassword.text.toString()

        if(ValidationUtils.isTextNotEmpty(email) && ValidationUtils.isTextNotEmpty(password)){
            if(ValidationUtils.isValidEmail(email)){
                val isSuccess = db.accesoUsuario(email, password)
                if (isSuccess){
                    Toast.makeText(this, "Acceso correcto", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()

                }
            }else{
                Toast.makeText(this, "Por favor ingrese correo valido", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Por favor ingrese datos", Toast.LENGTH_SHORT).show()
        }
    }


}
package com.example.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private lateinit var db:DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = DatabaseHelper(this)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            registraUsuario()
        }
    }

    private fun registraUsuario() {
        val username = binding.textInputUsername.text.toString()
        val email = binding.textInputEmail.text.toString()
        val password = binding.textInputPassword.text.toString()

        if(ValidationUtils.isTextNotEmpty(username) && ValidationUtils.isTextNotEmpty(email) && ValidationUtils.isTextNotEmpty(password)) {

            if(ValidationUtils.isValidEmail(email)){
                val user = User(username = username, email = email.trim(), password = password)
                db.registraUsuario(user)
                Toast.makeText(this, "Usuario registrado...!", Toast.LENGTH_SHORT).show()


            }else{
                Toast.makeText(this, "Por favor ingrese correo valido", Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, "Por favor ingrese datos", Toast.LENGTH_SHORT).show()
        }

    }
}
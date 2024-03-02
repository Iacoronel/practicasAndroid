package com.example.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var db:DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        val list = db.regresaCuentas()
        Log.e("men", "onCreate: ${list.size}")

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = UserAdapter(list)
        }


    }
}
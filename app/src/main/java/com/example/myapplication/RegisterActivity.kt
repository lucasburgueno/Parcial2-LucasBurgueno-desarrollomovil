package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        val editTextUsername: EditText = findViewById(R.id.editTextUsername)
        val editTextPassword: EditText = findViewById(R.id.editTextPassword)
        val buttonRegister: Button = findViewById(R.id.buttonRegister)


        buttonRegister.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()


            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa un nombre de usuario y una contraseña", Toast.LENGTH_SHORT).show()
            } else {

                val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                editor.putString("username", username)
                editor.putString("password", password)
                editor.apply()


                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()


                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}

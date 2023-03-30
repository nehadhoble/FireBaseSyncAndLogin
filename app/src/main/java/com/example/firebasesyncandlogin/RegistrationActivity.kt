package com.example.firebasesyncandlogin

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {

    private lateinit var etRegEmail: EditText
    private lateinit var etRegPassword: EditText
    private lateinit var tvLoginHere: TextView
    private lateinit var btnRegister: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

            etRegEmail = findViewById(R.id.etRegEmail)
            etRegPassword = findViewById(R.id.etRegPass)
            tvLoginHere = findViewById(R.id.tvLoginHere)
            btnRegister = findViewById(R.id.btnRegister)

            mAuth = FirebaseAuth.getInstance()

            btnRegister.setOnClickListener{
                createUser()
            }

            tvLoginHere.setOnClickListener{
                startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
            }
        }

         fun createUser(){
            val email = etRegEmail.text.toString()
            val password = etRegPassword.text.toString()

            if (TextUtils.isEmpty(email)){
                etRegEmail.error = "Email cannot be empty"
                etRegEmail.requestFocus()
            }else if (TextUtils.isEmpty(password)){
                etRegPassword.error = "Password cannot be empty"
                etRegPassword.requestFocus()
            }else{
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this@RegistrationActivity, "User registered successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
                    }else{
                        Toast.makeText(this@RegistrationActivity, "Registration Error: " + task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
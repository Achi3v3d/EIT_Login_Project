package com.example.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginapp.databinding.ActivityMain2Binding
import com.example.loginapp.databinding.ActivityMainBinding
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
    super.onResume()
    }
    fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }
}